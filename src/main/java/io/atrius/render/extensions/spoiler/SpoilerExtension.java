package io.atrius.render.extensions.spoiler;

import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

public class SpoilerExtension implements Parser.ParserExtension, HtmlRenderer.HtmlRendererExtension {

    private SpoilerExtension() {}

    public static SpoilerExtension create() {
        return new SpoilerExtension();
    }

    @Override
    public void extend(Parser.Builder parserBuilder) {
        parserBuilder.customDelimiterProcessor(new SpoilerDelimiterProcessor());
    }

    @Override
    public void extend(HtmlRenderer.Builder rendererBuilder) {
        rendererBuilder.nodeRendererFactory(SpoilerNodeRenderer::new);
    }
}
