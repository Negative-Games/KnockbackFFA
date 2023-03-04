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

package games.negative.knockbackffa.api.model;

import com.google.common.base.Preconditions;
import games.negative.framework.key.Keyd;

import java.util.UUID;

public interface Profile extends Keyd<UUID> {

    int getKills();

    void setKills(int amount);

    default void addKills(int amount) {
        Preconditions.checkArgument(amount > 0, "Amount must be greater than 0");
        setKills(getKills() + amount);
    }

    int getDeaths();

    void setDeaths(int amount);

    default void addDeaths(int amount) {
        Preconditions.checkArgument(amount > 0, "Amount must be greater than 0");
        setDeaths(getDeaths() + amount);
    }

    int getKillStreak();

    void setKillStreak(int amount);

    default void addKillStreak(int amount) {
        Preconditions.checkArgument(amount > 0, "Amount must be greater than 0");
        setKillStreak(getKillStreak() + amount);
    }

    int getBestKillStreak();

    void setBestKillStreak(int amount);

    default void addBestKillStreak(int amount) {
        Preconditions.checkArgument(amount > 0, "Amount must be greater than 0");
        setBestKillStreak(getBestKillStreak() + amount);
    }
}
