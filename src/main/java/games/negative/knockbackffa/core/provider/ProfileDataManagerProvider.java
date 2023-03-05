/*
 * MIT License
 *
 * Copyright (c) 2023 Negative Games
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package games.negative.knockbackffa.core.provider;

import com.google.common.collect.Lists;
import games.negative.framework.db.SQLDatabase;
import games.negative.framework.db.builder.DatabaseBuilder;
import games.negative.framework.db.builder.sqlite.SQLiteDatabaseBuilder;
import games.negative.framework.db.builder.sqlite.SQLiteTableBuilder;
import games.negative.framework.db.model.SQLColumnType;
import games.negative.knockbackffa.api.ProfileDataManager;
import games.negative.knockbackffa.api.model.Profile;
import games.negative.knockbackffa.core.structure.KnockBackFFAProfile;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class ProfileDataManagerProvider implements ProfileDataManager {

    private final SQLDatabase db;

    public ProfileDataManagerProvider(JavaPlugin plugin) {
        File file = new File(plugin.getDataFolder(), "profiles.db");
        SQLiteDatabaseBuilder builder = DatabaseBuilder.sqlite(file);
        SQLiteTableBuilder profiles = builder.withTable("profiles");
        profiles.withColumn("uuid", SQLColumnType.VARCHAR, 32).setPrimary(true).build();
        profiles.withColumn("kills", SQLColumnType.INT).build();
        profiles.withColumn("deaths", SQLColumnType.INT).build();
        profiles.withColumn("killstreak", SQLColumnType.INT).build();
        profiles.withColumn("highest_killstreak", SQLColumnType.INT).build();

        try {
            this.db = builder.complete();
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void save(Profile profile) {
        boolean exists;
        try (PreparedStatement statement = db.statement("SELECT * FROM profiles WHERE uuid = ?")) {
            statement.setString(1, profile.getKey().toString());
            exists = statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        if (exists) {
            // Update in database
            try(PreparedStatement statement = db.statement("UPDATE profiles SET kills = ?, deaths = ?, killstreak = ?, highest_killstreak = ? WHERE uuid = ?")) {
                statement.setInt(1, profile.getKills());
                statement.setInt(2, profile.getDeaths());
                statement.setInt(3, profile.getKillStreak());
                statement.setInt(4, profile.getBestKillStreak());
                statement.setString(5, profile.getKey().toString());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                return;
            }
        } else {
            // Insert into database
            try(PreparedStatement statement = db.statement("INSERT INTO profiles (uuid, kills, deaths, killstreak, highest_killstreak) VALUES (?, ?, ?, ?, ?)")) {
                statement.setString(1, profile.getKey().toString());
                statement.setInt(2, profile.getKills());
                statement.setInt(3, profile.getDeaths());
                statement.setInt(4, profile.getKillStreak());
                statement.setInt(5, profile.getBestKillStreak());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                return;
            }
        }
    }

    @Override
    public void delete(Profile profile) {
        try (PreparedStatement statement = db.statement("DELETE FROM profiles WHERE uuid = ?")) {
            statement.setString(1, profile.getKey().toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Profile get(UUID uuid) {
        try (PreparedStatement statement = db.statement("SELECT * FROM profiles WHERE uuid = ?")) {
            statement.setString(1, uuid.toString());

            try (ResultSet result = statement.executeQuery()) {
                int kills = result.getInt("kills");
                int deaths = result.getInt("deaths");
                int killstreak = result.getInt("killstreak");
                int highest_killstreak = result.getInt("highest_killstreak");

                return new KnockBackFFAProfile(
                        uuid,
                        kills,
                        deaths,
                        killstreak,
                        highest_killstreak
                );
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Collection<Profile> load() {
        List<UUID> toLoad = Lists.newArrayList();
        try (PreparedStatement statement = db.statement("SELECT * FROM profiles")) {
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    toLoad.add(UUID.fromString(result.getString("uuid")));
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        List<Profile> profiles = Lists.newArrayList();
        for (UUID uuid : toLoad) {
            profiles.add(get(uuid));
        }
        return profiles;
    }
}
