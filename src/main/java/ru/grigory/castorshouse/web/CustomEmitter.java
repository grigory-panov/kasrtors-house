package ru.grigory.castorshouse.web;

import org.tautua.markdownpapers.HtmlEmitter;
import org.tautua.markdownpapers.ast.Image;
import org.tautua.markdownpapers.ast.Resource;

/**
 * Created by gr on 06.03.16.
 */
public class CustomEmitter extends HtmlEmitter {

    public CustomEmitter(Appendable buffer) {
        super(buffer);
    }

    @Override
    public void visit(Image node) {
        Resource resource = node.getResource();
        if (resource == null) {
            append("<img src=\"\" alt=\"");
            escapeAndAppend(node.getText());
            append("\"/>");
        } else {
            append("<img");
            append(" src=\"");
            escapeAndAppend(resource.getLocation());
            if (node.getText() != null) {
                append("\" alt=\"");
                String alt = node.getText();
                if(alt.contains("|")){
                    String[] attrs = alt.split("\\|");
                    escapeAndAppend(attrs[0]);
                    if(attrs.length > 1){
                        append("\" class=\"");
                        escapeAndAppend(attrs[1]);
                    }
                }else {
                    escapeAndAppend(node.getText());
                }
            }
            if (resource.getHint() != null) {
                append("\" title=\"");
                escapeAndAppend(resource.getHint());
            }
            append("\"/>");
        }
    }
}
