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

import com.google.common.collect.Maps;
import games.negative.knockbackffa.api.ProfileDataManager;
import games.negative.knockbackffa.api.ProfileManager;
import games.negative.knockbackffa.api.exception.ProfileExistsException;
import games.negative.knockbackffa.api.model.Profile;
import games.negative.knockbackffa.core.structure.KnockBackFFAProfile;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

public class ProfileManagerProvider implements ProfileManager {

    private final Map<UUID, Profile> profiles;
    private final ProfileDataManager data;

    public ProfileManagerProvider(JavaPlugin plugin) {
        this.profiles = Maps.newHashMap();
        this.data = new ProfileDataManagerProvider(plugin);
    }


    @Override
    public Profile createProfile(@NotNull UUID uuid) throws ProfileExistsException {
        if (profiles.containsKey(uuid))
            throw new ProfileExistsException("Profile already exists for UUID: " + uuid.toString());

        Profile profile = new KnockBackFFAProfile(uuid);
        return null;
    }

    @Override
    public @Nullable Profile getProfile(@NotNull UUID uuid) {
        return profiles.getOrDefault(uuid, null);
    }

    @Override
    public void saveProfile(@NotNull Profile profile) {

    }

    @Override
    public Collection<Profile> getProfiles() {
        return null;
    }
}
