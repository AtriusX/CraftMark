package io.atrius.internal.extensions.spoiler;

import org.commonmark.node.Node;
import org.commonmark.node.Text;
import org.commonmark.parser.delimiter.DelimiterProcessor;
import org.commonmark.parser.delimiter.DelimiterRun;

/**
 * The processor used in handling spoiler blocks.
 */
public class SpoilerDelimiterProcessor implements DelimiterProcessor {

    /**
     * @return The opening spoiler char.
     */
    @Override
    public char getOpeningCharacter() {
        return '|';
    }

    /**
     * @return The closing spoiler char.
     */
    @Override
    public char getClosingCharacter() {
        return '|';
    }

    /**
     * Specifies the number of characters used for this delimiter type.
     *
     * @return The length of the delimiter.
     */
    @Override
    public int getMinLength() {
        return 2;
    }

    /**
     * Attempts to use a set of delimiters surrounding a node.
     *
     * @param opener The opening delimiter.
     * @param closer The closing delimiter.
     * @return       The number of chars to use out of the input.
     */
    @Override
    public int getDelimiterUse(DelimiterRun opener, DelimiterRun closer) {
        return opener.length() >= 2 && closer.length() >= 2 ? 2 : 0;
    }

    /**
     * Processes the input into a spoiler node.
     *
     * @param opener       The opening delimiter.
     * @param closer       The closing delimiter.
     * @param delimiterUse The number of characters to use.
     */
    @Override
    public void process(Text opener, Text closer, int delimiterUse) {
        Node spoiler = new Spoiler();
        // Appends all children between the opener and closer to the spoiler
        Node open = opener.getNext();
        while (open != null && open != closer) {
            Node next = open.getNext();
            spoiler.appendChild(open);
            open = next;
        }
        // Puts the spoiler node right after the opener in the message.
        opener.insertAfter(spoiler);
    }
}
