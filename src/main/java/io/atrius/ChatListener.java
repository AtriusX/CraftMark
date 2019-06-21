package io.atrius;

import io.atrius.render.CraftRenderer;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;

public class ChatListener implements Listener {

    private Parser        parser   = Parser.builder().extensions(Main.getExtensions()).build();
    private CraftRenderer renderer = CraftRenderer.create();

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        // Parse the input into a valid Minecraft chat message
        String message = ChatColor.translateAlternateColorCodes('&', event.getMessage());
        Node   document = parser.parse(message);
        // Replace message
        event.setMessage(renderer.render(document));
    }
}