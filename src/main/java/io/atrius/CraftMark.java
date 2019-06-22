package io.atrius;

import io.atrius.internal.extensions.spoiler.SpoilerExtension;
import io.atrius.listener.ChatListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.commonmark.Extension;
import org.commonmark.ext.gfm.strikethrough.StrikethroughExtension;
import org.commonmark.ext.ins.InsExtension;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@SuppressWarnings("unused")
public class CraftMark extends JavaPlugin {

    private Logger logger = Logger.getLogger("CraftMark");

    private static List<Extension> extensions = Arrays.asList(
            StrikethroughExtension.create(),
            InsExtension.create(),
            SpoilerExtension.create()
    );

    @Override
    public void onEnable() {
        // Register events
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new ChatListener(), this);
        // Plugin is now fully enabled
        logger.info("Chat manager enabled!");
    }

    public static List<Extension> getExtensions() {
        return extensions;
    }
}
