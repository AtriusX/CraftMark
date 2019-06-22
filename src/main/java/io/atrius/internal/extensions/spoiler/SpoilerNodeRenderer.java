package io.atrius.internal.extensions.spoiler;

import io.atrius.internal.renderer.CraftNodeRendererContext;
import io.atrius.internal.renderer.CraftWriter;
import org.bukkit.ChatColor;
import org.commonmark.node.Node;
import org.commonmark.renderer.NodeRenderer;

import java.util.Collections;
import java.util.Set;

public class SpoilerNodeRenderer implements NodeRenderer {

    private final CraftNodeRendererContext context;
    private final CraftWriter writer;

    SpoilerNodeRenderer(CraftNodeRendererContext context) {
        this.context = context;
        writer = context.getWriter();
    }

    @Override
    public Set<Class<? extends Node>> getNodeTypes() {
        return Collections.singleton(Spoiler.class);
    }

    @Override
    public void render(Node node) {
        writer.format(ChatColor.MAGIC);
        Node child = node.getFirstChild();
        while (child != null) {
            Node next = child.getNext();
            context.render(child);
            child = next;
        }
        writer.format(ChatColor.RESET);
    }
}
