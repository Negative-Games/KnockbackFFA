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

package games.negative.knockbackffa.core.structure;

import games.negative.knockbackffa.api.model.Profile;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class KnockBackFFAProfile implements Profile {

    private final UUID uuid;
    private int kills;
    private int deaths;
    private int killStreak;
    private int bestKillStreak;

    public KnockBackFFAProfile(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public int getKills() {
        return kills;
    }

    @Override
    public void setKills(int amount) {
        this.kills = amount;
    }

    @Override
    public int getDeaths() {
        return deaths;
    }

    @Override
    public void setDeaths(int amount) {
        this.deaths = amount;
    }

    @Override
    public int getKillStreak() {
        return killStreak;
    }

    @Override
    public void setKillStreak(int amount) {
        this.killStreak = amount;
    }

    @Override
    public int getBestKillStreak() {
        return bestKillStreak;
    }

    @Override
    public void setBestKillStreak(int amount) {
        this.bestKillStreak = amount;
    }

    @Override
    public @NotNull UUID getKey() {
        return uuid;
    }

    @Override
    public void setKey(@NotNull UUID uuid) {
        throw new UnsupportedOperationException("Cannot set the key of a profile");
    }
}
