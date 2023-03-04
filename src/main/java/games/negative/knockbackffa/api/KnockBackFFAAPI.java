package games.negative.knockbackffa.api;

public abstract class KnockBackFFAAPI {

    private static KnockBackFFAAPI instance;


    public static KnockBackFFAAPI getInstance() {
        return instance;
    }


    protected static void setInstance(KnockBackFFAAPI instance) {
        KnockBackFFAAPI.instance = instance;
    }
}
