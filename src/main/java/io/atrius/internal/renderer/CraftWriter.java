package io.atrius.internal.renderer;

import org.bukkit.ChatColor;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CraftWriter {

    private final Appendable buffer;
    private char lastChar;
    private String currentFormat = "";

    public CraftWriter(Appendable out) {
        buffer = out;
    }

    public void format(ChatColor color) {
        append(color.toString());
    }

    public void write(String s) {
        append(s);
    }

    public void escape() {
        format(ChatColor.RESET);
        write(currentFormat);
    }

    private void append(String s) {
        try {
            Pattern pattern = Pattern.compile("&[0-9a-fA-F]");
            Matcher matcher = pattern.matcher(s);
            while (matcher.find()) {
                currentFormat = matcher.group();
            }
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
