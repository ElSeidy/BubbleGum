package bubblegum.truffle;


import java.io.IOException;

import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.MaterializedFrame;

import bubblegum.truffle.literal.DoubleMatrixNode;
import bubblegum.truffle.node.BubbleNode;
import bubblegum.truffle.node.arithmetic.MulMatrixBuiltInNodeFactory;


public class BubbleMain {

	public static void main(String[] args) throws IOException {
		
		BubbleContext context = new BubbleContext();
		//Program2 add MatA * MatB
		DoubleMatrixNode A= new DoubleMatrixNode(10000, 10000);
		DoubleMatrixNode B= new DoubleMatrixNode(10000, 5);
		DoubleMatrixNode C= new DoubleMatrixNode(10000, 5);
		
		BubbleNode[] mulargs = new BubbleNode[]{A, B};
		BubbleNode mul = MulMatrixBuiltInNodeFactory.create( mulargs );

		BubbleNode[] addargs = new BubbleNode[]{mul, C};
		BubbleNode add = MulMatrixBuiltInNodeFactory.create( addargs );
		
		BubbleNode[] finalexpr = new BubbleNode[]{ add };
		
		// Warm-up the JVM & EVAL		
		for(int i=0; i<1000; i++){
			long start = System.currentTimeMillis();
			Object result = execute(finalexpr, context.getGlobalFrame());
			if (result != null) {
				System.out.println((System.currentTimeMillis()-start)+" "+result);
			}
		}
		
		
		
	}


	private static Object execute(BubbleNode[] nodes, MaterializedFrame globalFrame) {
		BubbleFunction function = BubbleFunction.create(new FrameSlot[] {},
				nodes, globalFrame.getFrameDescriptor());

		return function.callTarget.call(new Object[] {globalFrame});
	}
}
