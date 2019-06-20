package io.atrius.render.extensions.spoiler;

import org.commonmark.node.CustomNode;
import org.commonmark.node.Delimited;

public class Spoiler extends CustomNode implements Delimited {

    private static final String DELIMITER = "||";

    @Override
    public String getOpeningDelimiter() {
        return DELIMITER;
    }

    @Override
    public String getClosingDelimiter() {
        return DELIMITER;
    }
}
