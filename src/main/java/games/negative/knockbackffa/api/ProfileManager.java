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

package games.negative.knockbackffa.api;

import games.negative.knockbackffa.api.exception.ProfileExistsException;
import games.negative.knockbackffa.api.model.Profile;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.UUID;

public interface ProfileManager {

    Profile createProfile(@NotNull UUID uuid) throws ProfileExistsException;

    @Nullable
    Profile getProfile(@NotNull UUID uuid);

    @Nullable
    default Profile getProfile(@NotNull Player player) {
        return getProfile(player.getUniqueId());
    }

    @Nullable
    default Profile getProfile(@NotNull OfflinePlayer player) {
        return getProfile(player.getUniqueId());
    }

    void saveProfile(@NotNull Profile profile);

    default void saveProfiles() {
        getProfiles().forEach(this::saveProfile);
    }

    Collection<Profile> getProfiles();
}
