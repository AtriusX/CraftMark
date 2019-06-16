package io.atrius.render;

import org.commonmark.node.Node;
import org.commonmark.renderer.Renderer;
import org.commonmark.renderer.html.HtmlRenderer;

public class CraftChatRenderer implements Renderer {

    private HtmlRenderer renderer = HtmlRenderer.builder().build();

    @Override
    public void render(Node node, Appendable output) {}

    @Override
    public String render(Node node) {
        return renderer.render(node)
                .replace("<b>", "&o").replace("</b>", "&r")
                .replace("<i>", "&l").replace("</i>", "&r")
                .replace("<u>", "&u").replace("</u>", "&r")
                .replaceAll("<\\*p>", "");
    }
}
