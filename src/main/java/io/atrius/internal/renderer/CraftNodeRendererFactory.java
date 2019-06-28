package io.atrius.internal.renderer;

import org.commonmark.renderer.NodeRenderer;

/**
 * Factory interface used to build renderer classes.
 */
public interface CraftNodeRendererFactory {

    /**
     * Creates a <code>{@link NodeRenderer}</code> instance from a
     * <code>{@link CraftNodeRendererContext}</code> instance.
     *
     * @param context The context to create the renderer from.
     * @return        The created <code>{@link NodeRenderer}</code> instance.
     */
    NodeRenderer create(CraftNodeRendererContext context);
}
