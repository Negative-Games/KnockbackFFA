package games.negative.knockbackffa.core.provider;

import games.negative.knockbackffa.api.KnockBackFFAAPI;

public class KnockBackFFAAPIProvider extends KnockBackFFAAPI {

    public KnockBackFFAAPIProvider() {
        setInstance(this);
    }
}
