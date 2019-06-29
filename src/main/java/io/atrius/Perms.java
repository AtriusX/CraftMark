package io.atrius;

import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class Perms {
    private static final String PLUGIN_NAME            = "craftmark";
    public  static final String TRANSLATE_FORMAT_CODES = createPermission("translateformatcodes");
    public  static final String ALLOW_MARKDOWN         = createPermission("allowmarkdown");

    private static String createPermission(String... nodes) {
        List<String> n = Arrays.asList(PLUGIN_NAME);
        n.addAll(Arrays.asList(nodes));
        return String.join(".", n);
    }
}
