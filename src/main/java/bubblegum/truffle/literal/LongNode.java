package bubblegum.truffle.literal;


import com.oracle.truffle.api.frame.VirtualFrame;

import bubblegum.truffle.node.BubbleNode;

public class LongNode extends BubbleNode {
    public final long number;

    public LongNode(Long lng) {
        this.number = lng;
    }

    @Override
    public long executeLong(VirtualFrame virtualFrame) {
        return this.number;
    }

    @Override
    public Object execute(VirtualFrame virtualFrame) {
        return this.number;
    }

    @Override
    public String toString() {
        return "" + this.number;
    }
}
