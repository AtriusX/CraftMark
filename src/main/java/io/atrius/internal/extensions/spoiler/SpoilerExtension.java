package io.atrius.internal.extensions.spoiler;

import org.commonmark.parser.Parser;

/**
 * The extension used for detailing spoiler blocks.
 */
public class SpoilerExtension implements Parser.ParserExtension {

    /**
     * Internal use only.
     */
    private SpoilerExtension() {}

    /**
     * Creates a new <code>{@link SpoilerExtension}</code> instance.
     *
     * @return The newly created <code>{@link SpoilerExtension}</code> instance.
     */
    public static SpoilerExtension create() {
        return new SpoilerExtension();
    }

    /**
     * Extends the parser with an extension by supplying a custom processor.
     *
     * @param parserBuilder The <code>{@link org.commonmark.parser.Parser.Builder}</code> used
     *                      to create the plugin's markdown <code>{@link Parser}</code> instance.
     */
    @Override
    public void extend(Parser.Builder parserBuilder) {
        parserBuilder.customDelimiterProcessor(new SpoilerDelimiterProcessor());
    }
}
