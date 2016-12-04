package bubblegum.truffle.node;

import com.oracle.truffle.api.dsl.TypeSystemReference;
import com.oracle.truffle.api.frame.Frame;
import com.oracle.truffle.api.frame.MaterializedFrame;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.api.nodes.UnexpectedResultException;

import bubblegum.truffle.BubbleFunction;
import bubblegum.truffle.BubbleTypes;
import bubblegum.truffle.BubbleTypesGen;
import bubblegum.truffle.matrix.BooleanMatrix;
import bubblegum.truffle.matrix.DoubleMatrix;

@TypeSystemReference(BubbleTypes.class)
@NodeInfo(language = "Mumbler Language", description = "The abstract base node for all expressions")
public abstract class BubbleNode extends Node {
    
    public abstract Object execute(VirtualFrame virtualFrame);

    public long executeLong(VirtualFrame virtualFrame)
            throws UnexpectedResultException {
        return BubbleTypesGen.expectLong(this.execute(virtualFrame));
    }

    public boolean executeBoolean(VirtualFrame virtualFrame)
            throws UnexpectedResultException {
        return BubbleTypesGen.expectBoolean(this.execute(virtualFrame));
    }
    
    public BooleanMatrix executeBooleanMatrix(VirtualFrame virtualFrame)
            throws UnexpectedResultException {
        return BubbleTypesGen.expectBooleanMatrix(this.execute(virtualFrame));
    }
    
    public DoubleMatrix executeDoubleMatrix(VirtualFrame virtualFrame)
            throws UnexpectedResultException {
        return BubbleTypesGen.expectDoubleMatrix(this.execute(virtualFrame));
    }

    public BubbleFunction executeMumblerFunction(VirtualFrame virtualFrame)
            throws UnexpectedResultException {
        return BubbleTypesGen.expectBubbleFunction(
                this.execute(virtualFrame));
    }

    public String executeString(VirtualFrame virtualFrame)
            throws UnexpectedResultException{
        return BubbleTypesGen.expectString(this.execute(virtualFrame));
    }

    protected boolean isArgumentIndexInRange(VirtualFrame virtualFrame,
            int index) {
        return (index + 1) < virtualFrame.getArguments().length;
    }

    protected Object getArgument(VirtualFrame virtualFrame, int index) {
        return virtualFrame.getArguments()[index + 1];
    }

    protected static MaterializedFrame getLexicalScope(Frame frame) {
        Object[] args = frame.getArguments();
        if (args.length > 0) {
            return (MaterializedFrame) frame.getArguments()[0];
        } else {
            return null;
        }
    }
}
