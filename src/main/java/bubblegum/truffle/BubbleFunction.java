package bubblegum.truffle;

import com.oracle.truffle.api.RootCallTarget;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.FrameSlot;

import bubblegum.truffle.node.BubbleNode;
import bubblegum.truffle.node.BubbleRootNode;

public class BubbleFunction {
    public final RootCallTarget callTarget;

    public BubbleFunction(RootCallTarget callTarget) {
        this.callTarget = callTarget;
    }

    public static BubbleFunction create(FrameSlot[] arguments,
            BubbleNode[] bodyNodes, FrameDescriptor frameDescriptor) {
        return new BubbleFunction(
                Truffle.getRuntime().createCallTarget(
                        BubbleRootNode.create(arguments, bodyNodes, frameDescriptor)));
    }
}
