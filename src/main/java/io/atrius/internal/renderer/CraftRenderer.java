package io.atrius.internal.renderer;

import org.commonmark.internal.renderer.NodeRendererMap;
import org.commonmark.node.Node;
import org.commonmark.renderer.NodeRenderer;
import org.commonmark.renderer.Renderer;

import java.util.ArrayList;
import java.util.List;

public class CraftRenderer implements Renderer {

    private List<CraftNodeRendererFactory> rendererFactories = new ArrayList<>();

    private CraftRenderer() {
        rendererFactories.add(CraftNodeRenderer::new);
    }

    @Override
    public void render(Node node, Appendable output) {
        RendererContext context = new RendererContext(new CraftWriter(output));
        context.render(node);
    }

    @Override
    public String render(Node node) {
        StringBuilder sb = new StringBuilder();
        render(node, sb);
        // TODO color corrections
        String render = sb.toString();
        // TODO post-processing on the rendered text
        return render;
    }


    public static CraftRenderer create() {
            return new CraftRenderer();
        }

    private class RendererContext implements CraftNodeRendererContext {

        private final CraftWriter writer;
        private final NodeRendererMap nodeRendererMap = new NodeRendererMap();

        private RendererContext(CraftWriter writer) {
            this.writer = writer;

            for (int i = rendererFactories.size() - 1; i >= 0; i--) {
                CraftNodeRendererFactory rendererFactory = rendererFactories.get(i);
                NodeRenderer nodeRenderer = rendererFactory.create(this);
                nodeRendererMap.add(nodeRenderer);
            }
        }

        @Override
        public CraftWriter getWriter() {
            return writer;
        }

        @Override
        public void render(Node node) {
            nodeRendererMap.render(node);
        }
    }
}
