package io.atrius.listener;

import io.atrius.CraftMark;
import io.atrius.internal.renderer.CraftRenderer;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;

import java.util.Collections;

/**
 * Handles chat interactions for the server.
 */
public class ChatListener implements Listener {

    private Parser parser = Parser.builder()
            .enabledBlockTypes(Collections.emptySet()) // Disable all the unused node types
            .extensions(CraftMark.getExtensions())
            .build();

    private CraftRenderer renderer = CraftRenderer.create();

    /**
     * Renders each message out into the appropriate formats.
     */
    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        // Parse the input into a valid Minecraft chat message
        Node   document = parser.parse(event.getMessage());
        // Replace message
        event.setMessage(
                ChatColor.translateAlternateColorCodes('&', renderer.render(document))
        );
    }
}
