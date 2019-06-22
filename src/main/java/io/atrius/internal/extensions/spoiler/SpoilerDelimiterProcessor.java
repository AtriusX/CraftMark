package io.atrius.internal.extensions.spoiler;

import org.commonmark.node.Node;
import org.commonmark.node.Text;
import org.commonmark.parser.delimiter.DelimiterProcessor;
import org.commonmark.parser.delimiter.DelimiterRun;

public class SpoilerDelimiterProcessor implements DelimiterProcessor {

    @Override
    public char getOpeningCharacter() {
        return '|';
    }

    @Override
    public char getClosingCharacter() {
        return '|';
    }

    @Override
    public int getMinLength() {
        return 2;
    }

    @Override
    public int getDelimiterUse(DelimiterRun opener, DelimiterRun closer) {
        return opener.length() >= 2 && closer.length() >= 2 ? 2 : 0;
    }

    @Override
    public void process(Text opener, Text closer, int delimiterUse) {
        Node spoiler = new Spoiler();

        Node open = opener.getNext();
        while (open != null && open != closer) {
            Node next = open.getNext();
            spoiler.appendChild(next);
            open = next;
        }

        opener.insertAfter(spoiler);
    }
}
