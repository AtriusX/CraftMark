package io.atrius.render;

import org.commonmark.renderer.NodeRenderer;

public interface CraftNodeRendererFactory {

    NodeRenderer create(CraftNodeRendererContext context);
}
