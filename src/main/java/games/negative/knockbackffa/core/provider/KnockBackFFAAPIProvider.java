package games.negative.knockbackffa.core.provider;

import games.negative.knockbackffa.api.KnockBackFFAAPI;
import games.negative.knockbackffa.api.ProfileManager;
import org.bukkit.plugin.java.JavaPlugin;

public class KnockBackFFAAPIProvider extends KnockBackFFAAPI {

    private final ProfileManager profileManager;
    public KnockBackFFAAPIProvider(JavaPlugin plugin) {
        setInstance(this);

        this.profileManager = new ProfileManagerProvider(plugin);
    }

    @Override
    public ProfileManager getProfileManager() {
        return profileManager;
    }
}
