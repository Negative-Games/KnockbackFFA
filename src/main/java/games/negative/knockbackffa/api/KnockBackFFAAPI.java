package games.negative.knockbackffa.api;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

public abstract class KnockBackFFAAPI {

    @Getter
    @Setter(AccessLevel.PROTECTED)
    private static KnockBackFFAAPI instance;
}
