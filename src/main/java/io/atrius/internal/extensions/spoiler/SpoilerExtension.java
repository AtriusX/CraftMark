package io.atrius.internal.extensions.spoiler;

import org.commonmark.parser.Parser;

public class SpoilerExtension implements Parser.ParserExtension {

    private SpoilerExtension() {}

    public static SpoilerExtension create() {
        return new SpoilerExtension();
    }

    @Override
    public void extend(Parser.Builder parserBuilder) {
        parserBuilder.customDelimiterProcessor(new SpoilerDelimiterProcessor());
    }
}
