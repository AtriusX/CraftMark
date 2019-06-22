package io.atrius.internal.extensions.spoiler;

import io.atrius.internal.renderer.CraftRenderer;
import org.commonmark.parser.Parser;

public class SpoilerExtension implements Parser.ParserExtension, CraftRenderer.CraftRendererExtension {

    private SpoilerExtension() {}

    public static SpoilerExtension create() {
        return new SpoilerExtension();
    }

    @Override
    public void extend(Parser.Builder parserBuilder) {
        parserBuilder.customDelimiterProcessor(new SpoilerDelimiterProcessor());
    }

    @Override
    public void extend(CraftRenderer.Builder rendererBuilder) {
        rendererBuilder.nodeRendererFactory(SpoilerNodeRenderer::new);
    }
}
