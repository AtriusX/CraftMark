package io.atrius;

import io.atrius.render.CraftChatRenderer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;

public class ChatListener implements Listener {

    private CraftChatRenderer renderer = new CraftChatRenderer();

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        // TODO defer chat messages through commonmark for processing.
        Parser              parser   = Parser.builder().build();
        Node                document = parser.parse(event.getMessage());

        event.setMessage(renderer.render(document).trim());
    }
}