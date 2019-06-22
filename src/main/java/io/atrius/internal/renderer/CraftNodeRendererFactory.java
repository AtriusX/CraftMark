package io.atrius.internal.renderer;

import org.commonmark.renderer.NodeRenderer;

public interface CraftNodeRendererFactory {

    NodeRenderer create(CraftNodeRendererContext context);
}
