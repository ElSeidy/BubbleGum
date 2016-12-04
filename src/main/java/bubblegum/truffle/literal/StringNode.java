package bubblegum.truffle.literal;


import com.oracle.truffle.api.frame.VirtualFrame;

import bubblegum.truffle.node.BubbleNode;

public class StringNode extends BubbleNode {
    public final String str;

    public StringNode(String str) {
        this.str = str;
    }

    @Override
    public String executeString(VirtualFrame virtualFrame) {
        return this.str;
    }

    @Override
    public Object execute(VirtualFrame virtualFrame) {
        return this.str;
    }

}
