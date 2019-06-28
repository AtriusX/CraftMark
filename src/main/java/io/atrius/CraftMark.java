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

/**
 * CraftMark is a spigot plugin that is a markdown system for servers.
 * Using this allows users a much more intuitive level of control over
 * chat formatting and coloring.
 *
 * @author Atrius
 */
public class CraftMark extends JavaPlugin {

    private Logger logger = Logger.getLogger("CraftMark");
    // A list of node extensions used by CraftMark
    private static List<Extension> extensions = Arrays.asList(
            StrikethroughExtension.create(),
            InsExtension.create(),
            SpoilerExtension.create()
    );

    /**
     * Plugin startup preparations.
     */
    @Override
    public void onEnable() {
        // Register events
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new ChatListener(), this);
        // Plugin is now fully enabled
        logger.info("Chat manager enabled!");
    }

    /**
     * Gets the extension list created by this class.
     *
     * @return The plugin extension list.
     */
    public static List<Extension> getExtensions() {
        return extensions;
    }
}
