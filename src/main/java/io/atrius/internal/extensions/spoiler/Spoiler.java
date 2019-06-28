package io.atrius.internal.extensions.spoiler;

import org.commonmark.node.CustomNode;
import org.commonmark.node.Delimited;

/**
 * Custom node delimiter used for denoting spoiler blocks.
 */
public class Spoiler extends CustomNode implements Delimited {

    private static final String DELIMITER = "||";

    /**
     * @return The opening delimiter.
     */
    @Override
    public String getOpeningDelimiter() {
        return DELIMITER;
    }

    /**
     * @return The closing delimiter.
     */
    @Override
    public String getClosingDelimiter() {
        return DELIMITER;
    }
}
