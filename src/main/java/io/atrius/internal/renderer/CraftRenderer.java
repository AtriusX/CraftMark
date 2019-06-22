package io.atrius.internal.renderer;

import org.commonmark.Extension;
import org.commonmark.internal.renderer.NodeRendererMap;
import org.commonmark.node.Node;
import org.commonmark.renderer.NodeRenderer;
import org.commonmark.renderer.Renderer;

import java.util.ArrayList;
import java.util.List;

public class CraftRenderer implements Renderer {

    private List<CraftNodeRendererFactory> rendererFactories;

    private CraftRenderer(Builder builder) {
        this.rendererFactories = new ArrayList<>(builder.rendererFactories.size() + 1);
        this.rendererFactories.addAll(builder.rendererFactories);
        rendererFactories.add(CraftNodeRenderer::new);
    }

    public static Builder builder() {
        return new Builder();
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
        return sb.toString();
    }

    public static class Builder {

        private List<CraftNodeRendererFactory> rendererFactories = new ArrayList<>();

        public CraftRenderer build() {
            return new CraftRenderer(this);
        }

        public Builder extensions(Iterable<? extends Extension> extensions) {
            for (Extension e : extensions) {
                if (e instanceof CraftRendererExtension) {
                    CraftRendererExtension extension = (CraftRendererExtension) e;
                    extension.extend(this);
                }
            }
            return this;
        }

        public Builder nodeRendererFactory(CraftNodeRendererFactory craftNodeRendererFactory) {
            this.rendererFactories.add(craftNodeRendererFactory);
            return this;
        }
    }

    public interface CraftRendererExtension {
        void extend(CraftRenderer.Builder builder);
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

//    private HtmlRenderer renderer = HtmlRenderer.builder()
//            .extensions(CraftMark.getExtensions()).build();
//
//    @Override
//    public void render(Node node, Appendable output) {}
//
//    @Override
//    public String render(Node node) {
//        // TODO Handle chat colors
//        String render = renderer.render(node);
//        // Define the color matcher
//        Pattern pat = Pattern.compile("&[0-9a-fA-f]");
//        Matcher mat = pat.matcher(render);
//        String currentColor;
//        // Replace all closing tags with a reset and current color tag
//        while (mat.find()) {
//            currentColor = mat.group();
//            render = render.replaceAll("</(strong|em|ins|del|sp)>", "&r" + currentColor);
//        }
//
//        return render
//                .replace("<strong>", "&l").replace("<em>", "&o")
//                .replace("<ins>", "&n").replace("<del>", "&m")
//                .replace("<sp>", "&k").replaceAll("</?p>", "");
//    }
