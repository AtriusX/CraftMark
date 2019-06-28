package io.atrius.internal.renderer;

import io.atrius.internal.extensions.spoiler.Spoiler;
import org.bukkit.ChatColor;
import org.commonmark.ext.gfm.strikethrough.Strikethrough;
import org.commonmark.ext.ins.Ins;
import org.commonmark.node.*;
import org.commonmark.renderer.NodeRenderer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * The core of the renderer used for handling the rendering of each individual <code>{@link Node}</code>.
 */
public class CraftNodeRenderer extends AbstractVisitor implements NodeRenderer {

    private final CraftWriter writer;
    // Supported node types
    private Set<Class<? extends Node>> types = new HashSet<>(Arrays.asList(
            Document.class, Paragraph.class, Emphasis.class,
            StrongEmphasis.class, CustomNode.class, Link.class,
            Code.class
    ));

    /**
     * Creates a new <code>{@link CraftNodeRenderer}</code> instance.
     *
     * @param context The renderer context used for this renderer.
     */
    public CraftNodeRenderer(CraftNodeRendererContext context) {
        this.writer = context.getWriter();
    }

    /**
     * Gets a list of valid node types that this renderer will handle.
     *
     * @return The list of valid node types.
     */
    @Override
    public Set<Class<? extends Node>> getNodeTypes() {
        return types;
    }

    /**
     * Renders out a given node using this <code>{@link NodeRenderer}</code>.
     *
     * @param node The node to accept with this renderer.
     */
    @Override
    public void render(Node node) {
        node.accept(this);
    }

    /**
     * Escapes regions so markdown won't be parsed within the block.
     *
     * @param code The area to ignore parsing.
     */
    @Override
    public void visit(Code code) {
        visitChildren(code);
        writer.write(code.getLiteral());
    }

    /**
     * Handles all custom node renderings.
     *
     * @param customNode The custom node to parse.
     */
    @Override
    public void visit(CustomNode customNode) {
        // Default code if the node is none of the following types.
        ChatColor code = ChatColor.RESET;
        // Spoiler formatting
        if (customNode instanceof Spoiler) {
            code = ChatColor.MAGIC;
        }
        // Underline formatting
        if (customNode instanceof Ins) {
            code = ChatColor.UNDERLINE;
        }
        // Strikethrough formatting
        if (customNode instanceof Strikethrough) {
            code = ChatColor.STRIKETHROUGH;
        }
        // Formats the node according to the given data
        formatNode(customNode, code);
    }

    /**
     * No special rendering done here.
     */
    @Override
    public void visit(Document document) {
        visitChildren(document);
    }

    /**
     * No special rendering done here.
     */
    @Override
    public void visit(Paragraph paragraph) {
        visitChildren(paragraph);
    }

    /**
     * Applies italic text styling to the given region.
     *
     * @param emphasis The region to apply italic styling to.
     */
    @Override
    public void visit(Emphasis emphasis) {
        formatNode(emphasis, ChatColor.ITALIC);
    }

    /**
     * Applies bold text styling to the given region.
     *
     * @param strongEmphasis The region to apply bold styling to.
     */
    @Override
    public void visit(StrongEmphasis strongEmphasis) {
        formatNode(strongEmphasis, ChatColor.BOLD);
    }

    /**
     * TODO: Support the creation of named-link blocks in the renderer.
     */
    @Override
    public void visit(Link link) {
        super.visit(link);
    }

    /**
     * Pushes the text between each node into the writer.
     *
     * @param text The text supplied by this specific node.
     */
    @Override
    public void visit(Text text) {
        writer.write(text.getLiteral());
        writer.escape();
    }

    /**
     * Formats the node tree by applying relevant styling and tracking the current branches style tree.
     *
     * @param node         The node tree to format.
     * @param includeStyle The style applied to the message, also tracked by the style tree.
     */
    private void formatNode(Node node, ChatColor includeStyle) {
        writer.format(includeStyle);
        writer.includeStyle(includeStyle);
        visitChildren(node);
        writer.removeStyle(includeStyle);
        writer.escape();
    }
}
