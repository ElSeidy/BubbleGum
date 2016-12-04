package bubblegum;

import static org.junit.Assert.*;

import org.junit.Test;

import com.oracle.truffle.api.frame.FrameSlot;

import bubblegum.truffle.BubbleContext;
import bubblegum.truffle.BubbleFunction;
import bubblegum.truffle.literal.DoubleMatrixNode;
import bubblegum.truffle.node.BubbleNode;
import bubblegum.truffle.node.arithmetic.MulMatrixBuiltInNodeFactory;

public class MatrixTest {

	@Test
	public void test() {
		
		//Program2 add MatA * MatB
		BubbleContext context = new BubbleContext();
		DoubleMatrixNode A= new DoubleMatrixNode(10000, 10000);
		DoubleMatrixNode B= new DoubleMatrixNode(10000, 5);
		DoubleMatrixNode C= new DoubleMatrixNode(10000, 5);
		
		BubbleNode[] mulargs = new BubbleNode[]{A, B};
		BubbleNode mul = MulMatrixBuiltInNodeFactory.create( mulargs );
		BubbleNode[] addargs = new BubbleNode[]{mul, C};
		BubbleNode add = MulMatrixBuiltInNodeFactory.create( addargs );
		BubbleNode[] finalexpr = new BubbleNode[]{ add };
		
		BubbleFunction function = BubbleFunction.create(new FrameSlot[] {},
				finalexpr, context.getGlobalFrame().getFrameDescriptor());
		
		// Warm-up the JVM & EVAL		
		for(int i=0; i<1000; i++){
			long start = System.currentTimeMillis();
			Object result = function.callTarget.call(new Object[] {context.getGlobalFrame()});
				System.out.println((System.currentTimeMillis()-start)+" "+result);
		}
		
		fail("Performance still comparable to J2SE 8");
	}

}
