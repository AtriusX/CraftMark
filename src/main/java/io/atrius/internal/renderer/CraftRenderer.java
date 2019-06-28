package io.atrius.internal.renderer;

import org.commonmark.internal.renderer.NodeRendererMap;
import org.commonmark.node.Node;
import org.commonmark.renderer.NodeRenderer;
import org.commonmark.renderer.Renderer;

import java.util.ArrayList;
import java.util.List;

/**
 * Custom commonmark renderer implementation for specific use on minecraft servers.
 */
public class CraftRenderer implements Renderer {

    private List<CraftNodeRendererFactory> rendererFactories = new ArrayList<>();

    /**
     * Internal use only.
     */
    private CraftRenderer() {
        rendererFactories.add(CraftNodeRenderer::new);
    }

    /**
     * Renders a node into a specified <<code>{@link Appendable}</code> output.
     *
     * @param node   The node to render.
     * @param output The appendable output to append the node into.
     */
    @Override
    public void render(Node node, Appendable output) {
        RendererContext context = new RendererContext(new CraftWriter(output));
        context.render(node);
    }

    /**
     * Renders a node into a string output.
     *
     * @param node The node to render.
     * @return     The string output of the rendered node.
     */
    @Override
    public String render(Node node) {
        StringBuilder sb = new StringBuilder();
        render(node, sb);
        // TODO color corrections
        String render = sb.toString();
        // TODO post-processing on the rendered text
        return render;
    }

    /**
     *  Creates a new instance of this renderer.
     *
     * @return The newly created instance.
     */
    public static CraftRenderer create() {
            return new CraftRenderer();
        }

    /**
     *  Context manager for CraftMark.
     */
    private class RendererContext implements CraftNodeRendererContext {

        private final CraftWriter writer;
        private final NodeRendererMap nodeRendererMap = new NodeRendererMap();

        /**
         * Creates a new <code>{@link RendererContext}</code> instance.
         *
         * @param writer The <code>{@link CraftWriter}</code> instance used in this context.
         */
        private RendererContext(CraftWriter writer) {
            this.writer = writer;
            // Parse factories into renderers
            for (int i = rendererFactories.size() - 1; i >= 0; i--) {
                CraftNodeRendererFactory rendererFactory = rendererFactories.get(i);
                NodeRenderer nodeRenderer = rendererFactory.create(this);
                nodeRendererMap.add(nodeRenderer);
            }
        }

        /**
         * Gets the <code>{@link CraftWriter}</code> instance used in this context.
         *
         * @return The <code>{@link CraftWriter}</code> instance.
         */
        @Override
        public CraftWriter getWriter() {
            return writer;
        }

        /**
         * Renders a node against this context's <code>{@link NodeRendererMap}</code>.
         *
         * @param node The node to render.
         */
        @Override
        public void render(Node node) {
            nodeRendererMap.render(node);
        }
    }
}
