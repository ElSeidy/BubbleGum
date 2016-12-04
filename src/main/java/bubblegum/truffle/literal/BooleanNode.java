package bubblegum.truffle.literal;

import com.oracle.truffle.api.frame.VirtualFrame;

import bubblegum.truffle.node.BubbleNode;

public class BooleanNode extends BubbleNode {
    public final boolean value;

    public BooleanNode(Boolean bool) {
        this.value = bool;
    }

    @Override
    public boolean executeBoolean(VirtualFrame virtualFrame) {
        return this.value;
    }

    @Override
    public Object execute(VirtualFrame virtualFrame) {
        return this.value;
    }

    @Override
    public String toString() {
        if (this.value) {
            return "#t";
        } else {
            return "#f";
        }
    }
}
