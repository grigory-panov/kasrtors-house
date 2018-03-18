package ru.grigory.castorshouse.web;

import org.tautua.markdownpapers.HtmlEmitter;
import org.tautua.markdownpapers.ast.Header;
import org.tautua.markdownpapers.ast.Image;

/**
 * Created by grigory on 10.11.15.
 */
public class OnlyTextEmitter extends HtmlEmitter {
    private int length;
    private boolean end = false;
    private int MAX_LEN = 500;
    public OnlyTextEmitter(Appendable buffer) {
        super(buffer);
    }

    @Override
    public void visit(Image node) {
        // skip images
    }

    @Override
    public void visit(Header node) {
        // do nothing
    }

    @Override
    protected void append(String val) {
        if(length < MAX_LEN || !end) {
            super.append(val);
            length +=val.length();
        }
        if(length >= MAX_LEN && val.contains("</p>")){
            end=true;
        }
    }
}
