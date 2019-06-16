package io.atrius;

import io.atrius.render.CraftChatRenderer;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;

public class ChatListener implements Listener {

    private Parser            parser   = Parser.builder().build();
    private CraftChatRenderer renderer = new CraftChatRenderer();

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        // Parse the input into a valid Minecraft chat message
        Node   document = parser.parse(event.getMessage());
        String message  = renderer.render(document);
        // Replace message
        event.setMessage(ChatColor.translateAlternateColorCodes('&', message));
    }
}