package io.atrius.render.extensions.spoiler;

import org.commonmark.node.Node;
import org.commonmark.renderer.NodeRenderer;
import org.commonmark.renderer.html.HtmlNodeRendererContext;
import org.commonmark.renderer.html.HtmlWriter;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class SpoilerNodeRenderer implements NodeRenderer {

    private final HtmlNodeRendererContext context;
    private final HtmlWriter writer;

    SpoilerNodeRenderer(HtmlNodeRendererContext context) {
        this.context = context;
        writer = context.getWriter();
    }

    @Override
    public Set<Class<? extends Node>> getNodeTypes() {
        return Collections.singleton(Spoiler.class);
    }

    @Override
    public void render(Node node) {
        Map<String, String> attributes = context.extendAttributes(node, "sp", Collections.emptyMap());
        writer.tag("sp", attributes);
        // Render children
        Node child = node.getFirstChild();
        while (child != null) {
            Node next = child.getNext();
            context.render(child);
            child = next;
        }
        writer.tag("/sp", attributes);
    }
}
