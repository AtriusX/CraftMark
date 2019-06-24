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

public class CraftNodeRenderer extends AbstractVisitor implements NodeRenderer {

    private final CraftWriter writer;

    public CraftNodeRenderer(CraftNodeRendererContext context) {
        this.writer = context.getWriter();
    }

    @Override
    public Set<Class<? extends Node>> getNodeTypes() {
        return new HashSet<>(Arrays.asList(
                Document.class,
                Paragraph.class,
                Emphasis.class,
                StrongEmphasis.class,
                CustomNode.class,
                Link.class
        ));
    }

    @Override
    public void render(Node node) {
        node.accept(this);
    }

    @Override
    public void visit(CustomNode customNode) {
        // Spoiler formatting
        if (customNode instanceof Spoiler) {
            writer.format(ChatColor.MAGIC);
        }
        // Underline formatting
        if (customNode instanceof Ins) {
            writer.format(ChatColor.UNDERLINE);
        }
        // Strikethrough formatting
        if (customNode instanceof Strikethrough) {
            writer.format(ChatColor.STRIKETHROUGH);
        }

        visitChildren(customNode);
        writer.format(ChatColor.RESET);
    }

    @Override
    public void visit(Document document) {
        visitChildren(document);
    }

    @Override
    public void visit(Paragraph paragraph) {
        visitChildren(paragraph);
    }

    @Override
    public void visit(Emphasis emphasis) {
        writer.format(ChatColor.ITALIC);
        visitChildren(emphasis);
        writer.format(ChatColor.RESET);
    }

    @Override
    public void visit(StrongEmphasis strongEmphasis) {
        writer.format(ChatColor.BOLD);
        visitChildren(strongEmphasis);
        writer.format(ChatColor.RESET);
    }

    @Override
    public void visit(Link link) {
        super.visit(link);
    }

    @Override
    public void visit(Text text) {
        writer.write(text.getLiteral());
    }
}
