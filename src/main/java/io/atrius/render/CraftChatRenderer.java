package io.atrius.render;

import org.commonmark.node.Node;
import org.commonmark.renderer.Renderer;

public class CraftChatRenderer implements Renderer {

    @Override
    public void render(Node node, Appendable output) {

    }

    @Override
    public String render(Node node) {
        if (node == null) {
            System.out.println("Node must not be null!");
            return "";
        }

        System.out.println(node.toString());
        return null;
    }
}
