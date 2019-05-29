package io.atrius;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

@SuppressWarnings("unused")
public class Main extends JavaPlugin {

    private Logger logger = Logger.getLogger("Craftdown");

    @Override
    public void onEnable() {
        // Register events
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new ChatListener(), this);
        // Plugin is now fully enabled
        logger.info("Chat manager enabled!");
    }
}
