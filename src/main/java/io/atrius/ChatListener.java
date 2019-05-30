package io.atrius;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

public class ChatListener implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        // TODO defer chat messages through commonmark for processing.
        Parser            parser   = Parser.builder().build();
        Node              document = parser.parse(event.getMessage());
        HtmlRenderer      renderer = HtmlRenderer.builder().build();

        event.setMessage(renderer.render(document).trim());
    }
}
