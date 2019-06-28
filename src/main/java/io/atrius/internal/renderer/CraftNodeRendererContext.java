package io.atrius.internal.renderer;

import org.commonmark.node.Node;

/**
 * Outline for CraftMark's <code>{@link io.atrius.internal.renderer.CraftRenderer.RendererContext}</code>
 */
public interface CraftNodeRendererContext {

    /**
     * Gets the <code>{@link CraftWriter}</code> instance used in this context.
     *
     * @return The <code>{@link CraftWriter}</code> instance.
     */
    CraftWriter getWriter();

    /**
     * Renders a node against this context's <code>{@link NodeRendererMap}</code>.
     *
     * @param node The node to render.
     */
    void render(Node node);
}
