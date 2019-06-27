package io.atrius.internal.renderer;

import org.bukkit.ChatColor;

import java.io.IOException;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CraftWriter {

    private final Appendable      buffer;
    private final HashSet<String> currentNodes  = new HashSet<>();
    private       String          currentFormat = "";

    public CraftWriter(Appendable out) {
        buffer = out;
    }

    public void format(ChatColor color) {
        append(color.toString());
    }

    public void write(String s) {
        append(s);
    }

    public void includeFormat(ChatColor color) {
        if (isFormat(color)) currentNodes.add(color.toString());
    }

    public void removeFormat(ChatColor color) {
        if (isFormat(color)) currentNodes.remove(color.toString());
    }

    public void escape() {
        format(ChatColor.RESET);
        write(currentFormat);
    }

    private void append(String s) {
        try {
            Pattern pattern = Pattern.compile("(&[0-9a-fA-F])+");
            Matcher matcher = pattern.matcher(s);
            while (matcher.find()) {
                currentFormat = matcher.group();
                StringBuilder buffer = new StringBuilder(s);
                for (String code : currentNodes) {
                    buffer.insert(matcher.end(), code);
                }
                s = buffer.toString();
            }
            buffer.append(s);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isFormat(ChatColor color) {
        switch (color) {
            case BOLD: case ITALIC: case MAGIC:
            case UNDERLINE: case STRIKETHROUGH:
                return true;
            default:
                return false;
        }
    }
}
