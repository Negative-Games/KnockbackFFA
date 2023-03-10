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

package games.negative.knockbackffa;

import games.negative.framework.BasePlugin;
import games.negative.knockbackffa.api.KnockBackFFAAPI;
import games.negative.knockbackffa.api.ProfileManager;
import games.negative.knockbackffa.core.provider.KnockBackFFAAPIProvider;
import games.negative.knockbackffa.listener.PlayerCombatListener;
import games.negative.knockbackffa.listener.ProfileListener;

public final class KnockBackFFA extends BasePlugin {

    private KnockBackFFAAPI api;

    @Override
    public void onEnable() {
        super.onEnable();
        // Plugin startup logic
        this.api = new KnockBackFFAAPIProvider(this);

        registerListeners(
                new ProfileListener(this, api.getProfileManager()),
                new PlayerCombatListener()
        );
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        ProfileManager profiles = api.getProfileManager();
        profiles.onDisable();
    }

}
