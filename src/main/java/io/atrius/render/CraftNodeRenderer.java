package io.atrius.render;

import org.bukkit.ChatColor;
import org.commonmark.node.*;
import org.commonmark.renderer.NodeRenderer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CraftNodeRenderer extends AbstractVisitor implements NodeRenderer {

    private final CraftNodeRendererContext context;
    private final CraftWriter writer;

    public CraftNodeRenderer(CraftNodeRendererContext context) {
        this.context = context;
        this.writer = context.getWriter();
    }

    @Override
    public Set<Class<? extends Node>> getNodeTypes() {
        return new HashSet<>(Arrays.asList(
                Document.class,
                Paragraph.class,
                Emphasis.class,
                StrongEmphasis.class,
                Link.class
        ));
    }

    @Override
    public void render(Node node) {
        node.accept(this);
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
    public void visit(Text text) {
        writer.write(text.getLiteral());
    }
}
