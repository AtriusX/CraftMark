package io.atrius.render;

import org.commonmark.node.Node;

public interface CraftNodeRendererContext {

    CraftWriter getWriter();

    void render(Node node);
}
