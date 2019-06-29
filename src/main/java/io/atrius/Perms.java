package io.atrius;

import java.util.Arrays;
import java.util.List;

/**
 * Global permissions file used for tracking CraftMark's supported permission nodes.
 */
public class Perms {
    private static final String PLUGIN_NAME            = "craftmark";
    public  static final String TRANSLATE_FORMAT_CODES = createPermission("translateformatcodes");
    public  static final String ALLOW_MARKDOWN         = createPermission("allowmarkdown");

    /**
     * Creates a permission node from the given text nodes.
     *
     * @param nodes The nodes to build a permission out of.
     * @return      The permission node in the form "craftmark.string1.string2..."
     */
    private static String createPermission(String... nodes) {
        List<String> n = Arrays.asList(PLUGIN_NAME);
        n.addAll(Arrays.asList(nodes));
        return String.join(".", n);
    }
}
