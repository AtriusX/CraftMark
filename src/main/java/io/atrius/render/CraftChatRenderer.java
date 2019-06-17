package io.atrius.render;

import io.atrius.Main;
import org.commonmark.node.Node;
import org.commonmark.renderer.Renderer;
import org.commonmark.renderer.html.HtmlRenderer;

public class CraftChatRenderer implements Renderer {

    private HtmlRenderer renderer = HtmlRenderer.builder().extensions(Main.getExtensions()).build();

    @Override
    public void render(Node node, Appendable output) {}

    @Override
    public String render(Node node) {
        // TODO Handle chat colors
        String render = renderer.render(node)
                .replace("<strong>", "&l").replace("</strong>", "&r")
                .replace("<em>", "&o").replace("</em>", "&r")
                .replace("<ins>", "&n").replace("</ins>", "&r")
                .replace("<del>", "&m").replace("</del>", "&r")
                .replaceAll("</*p>", "");

        return render;
    }
}
