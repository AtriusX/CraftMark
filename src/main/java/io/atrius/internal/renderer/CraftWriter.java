package io.atrius.internal.renderer;

import org.bukkit.ChatColor;

import java.io.IOException;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The writer system used in CraftMark for rendering markdown to formatting codes.
 */
public class CraftWriter {

    private final Appendable      buffer;
    private final HashSet<String> styles = new HashSet<>();
    private       String          currentFormat = "";

    /**
     * Creates a new writer instance.
     *
     * @param out The output the buffer will be appended to.
     */
    public CraftWriter(Appendable out) {
        buffer = out;
    }

    /**
     * Inserts a formatting code into the buffer.
     *
     * @param format The formatting code to insert.
     */
    public void format(ChatColor format) {
        append(format.toString());
    }

    /**
     * Writers a raw string into the buffer.
     *
     * @param s The string to append.
     */
    public void write(String s) {
        append(s);
    }

    /**
     * Adds a style tag to the tracked styles list. This is used to help preserve
     * text styling should a color code be introduced midway through a node, thereby
     * resetting all text styling.
     *
     * @param style The style code to track.
     */
    public void includeStyle(ChatColor style) {
        if (isStyle(style)) styles.add(style.toString());
    }

    /**
     * Removes a style code from writer tracking.
     *
     * @param style The style code to remove.
     */
    public void removeStyle(ChatColor style) {
        if (isStyle(style)) styles.remove(style.toString());
    }

    /**
     * Escapes the current node block. This is used to help preserve chat colors once
     * the buffer inserts a reset code to remove styling.
     */
    public void escape() {
        format(ChatColor.RESET);
        write(currentFormat);
    }

    /**
     * Appends a string input to the writer's buffer. This also keeps track of the current
     * color code, as well as inserts style codes as needed.
     *
     * @param s The string to append.
     */
    private void append(String s) {
        try {
            Pattern pattern = Pattern.compile("(&[0-9a-fA-F])+");
            Matcher matcher = pattern.matcher(s);
            while (matcher.find()) {
                currentFormat = matcher.group();
                StringBuilder buffer = new StringBuilder(s);
                for (String code : styles) {
                    buffer.insert(matcher.end(), code);
                }
                s = buffer.toString();
            }
            buffer.append(s);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Checks if the given format is a style code or not.
     *
     * @param format The format code to check.
     * @return       True if the format code is a style code.
     */
    private boolean isStyle(ChatColor format) {
        switch (format) {
            case BOLD: case ITALIC: case MAGIC:
            case UNDERLINE: case STRIKETHROUGH:
                return true;
            default:
                return false;
        }
    }
}
