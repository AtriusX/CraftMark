package io.atrius.render;

import io.atrius.Main;
import org.commonmark.node.Node;
import org.commonmark.renderer.Renderer;
import org.commonmark.renderer.html.HtmlRenderer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CraftChatRenderer implements Renderer {

    private HtmlRenderer renderer = HtmlRenderer.builder().extensions(Main.getExtensions()).build();

    @Override
    public void render(Node node, Appendable output) {}

    @Override
    public String render(Node node) {
        // TODO Handle chat colors
        String render = renderer.render(node);
        // Define the color matcher
        Pattern pat = Pattern.compile("&[0-9a-fA-f]");
        Matcher mat = pat.matcher(render);
        String currentColor;
        // Replace all closing tags with a reset and current color tag
        while (mat.find()) {
            currentColor = mat.group();
            render = render.replaceAll("</(strong|em|ins|del|sp)>", "&r" + currentColor);
        }

        return render
                .replace("<strong>", "&l").replace("<em>", "&o")
                .replace("<ins>", "&n").replace("<del>", "&m")
                .replace("<sp>", "&k").replaceAll("</?p>", "");
    }
}
