package bubblegum.truffle.literal;

import com.oracle.truffle.api.frame.VirtualFrame;

import bubblegum.truffle.matrix.BooleanMatrix;
import bubblegum.truffle.node.BubbleNode;

public class BooleanMatrixNode extends BubbleNode {
	public final BooleanMatrix value;

	public BooleanMatrixNode(int m, int n) {
		value= BooleanMatrix.createRandom(m, n);
	}
	
	@Override
	public Object execute(VirtualFrame virtualFrame) {
		return this.value;
	}
	
	@Override
    public String toString() {
        return this.value.toString();
    }
}
