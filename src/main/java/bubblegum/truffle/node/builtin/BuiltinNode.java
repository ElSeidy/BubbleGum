package bubblegum.truffle.node.builtin;

import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.NodeFactory;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.VirtualFrame;

import bubblegum.truffle.BubbleFunction;
import bubblegum.truffle.node.BubbleNode;
import bubblegum.truffle.node.BubbleRootNode;
import bubblegum.truffle.node.read.ReadArgumentNode;

@NodeChild(value = "arguments", type =  BubbleNode[].class)
public abstract class BuiltinNode extends BubbleNode {
    public static BubbleFunction createBuiltinFunction(
            NodeFactory<? extends BuiltinNode> factory,
            VirtualFrame outerFrame) {
        int argumentCount = factory.getExecutionSignature().size();
        BubbleNode[] argumentNodes = new BubbleNode[argumentCount];
        for (int i=0; i<argumentCount; i++) {
            argumentNodes[i] = new ReadArgumentNode(i);
        }
        BuiltinNode node = factory.createNode((Object) argumentNodes);
        return new BubbleFunction(Truffle.getRuntime().createCallTarget(
                new BubbleRootNode(new BubbleNode[] {node},
                        new FrameDescriptor())));
    }
}
