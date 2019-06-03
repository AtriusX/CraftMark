package io.atrius.render;

import org.commonmark.node.Node;
import org.commonmark.renderer.Renderer;

import java.io.IOException;

public class CraftChatRenderer implements Renderer {

    @Override
    public void render(Node node, Appendable output) {
        try {
            output.append(render(node));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
