package bubblegum.truffle.node;

import java.util.Arrays;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import com.oracle.truffle.api.nodes.RootNode;

import bubblegum.truffle.node.read.DefineNodeGen;
import bubblegum.truffle.node.read.ReadArgumentNode;

public class BubbleRootNode extends RootNode {
    @Children private final BubbleNode[] bodyNodes;
    @CompilationFinal public String name;

    public BubbleRootNode(BubbleNode[] bodyNodes,
            FrameDescriptor frameDescriptor) {
        super(TruffleLanguage.class, null, frameDescriptor);
        this.bodyNodes = bodyNodes;
    }

    @Override
    @ExplodeLoop
    public Object execute(VirtualFrame virtualFrame) {
        int last = this.bodyNodes.length - 1;
        CompilerAsserts.compilationConstant(last);
        for (int i=0; i<last; i++) {
            this.bodyNodes[i].execute(virtualFrame);
        }
        return this.bodyNodes[last].execute(virtualFrame);
    }

    public static BubbleRootNode create(FrameSlot[] argumentNames,
            BubbleNode[] bodyNodes, FrameDescriptor frameDescriptor) {
    	BubbleNode[] allNodes = new BubbleNode[argumentNames.length
                                                 + bodyNodes.length];
        for (int i=0; i<argumentNames.length; i++) {
            allNodes[i] = DefineNodeGen.create(
                    new ReadArgumentNode(i), argumentNames[i]);
        }
        System.arraycopy(bodyNodes, 0, allNodes,
                argumentNames.length, bodyNodes.length);
        return new BubbleRootNode(allNodes, frameDescriptor);
    }

    public void setName(String name) {
        if (this.name == null) {
            this.name = name;
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(this.bodyNodes);
    }
}
