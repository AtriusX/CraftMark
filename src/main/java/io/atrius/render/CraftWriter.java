package io.atrius.render;

import org.bukkit.ChatColor;

import java.io.IOException;

public class CraftWriter {

    private final Appendable buffer;
    private char lastChar;

    public CraftWriter(Appendable out) {
        buffer = out;
    }

    public void format(ChatColor color) {
        append(color.toString());
    }

    public void write(String s) {
        append(s);
    }

    private void append(String s) {
        try {
            buffer.append(s);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int length = s.length();
        if (length != 0) {
            lastChar = s.charAt(length - 1);
        }
    }
}
