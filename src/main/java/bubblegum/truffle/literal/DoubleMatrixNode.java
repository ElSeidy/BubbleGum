package bubblegum.truffle.literal;

import com.oracle.truffle.api.frame.VirtualFrame;

import bubblegum.truffle.matrix.DoubleMatrix;
import bubblegum.truffle.node.BubbleNode;

public class DoubleMatrixNode extends BubbleNode {
	public final DoubleMatrix value;
	
	public DoubleMatrixNode(int m, int n) {
		value= DoubleMatrix.createRandom(m, n);
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
