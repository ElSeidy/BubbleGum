package bubblegum;

import static org.junit.Assert.*;

import org.junit.Test;

import com.oracle.truffle.api.frame.FrameSlot;

import bubblegum.truffle.BubbleContext;
import bubblegum.truffle.BubbleFunction;
import bubblegum.truffle.literal.LongNode;
import bubblegum.truffle.node.BubbleNode;
import bubblegum.truffle.node.arithmetic.AddBuiltinNodeFactory;

public class ArithmeticTest {

	@Test
	public void test() {
		//Program1 add (1+2) + (1+1)
		BubbleContext context = new BubbleContext();
		BubbleNode[] args1 = new BubbleNode[]{ new LongNode(1L), new LongNode(2L) };
		BubbleNode add1 = AddBuiltinNodeFactory.create( args1   );
		BubbleNode[] args2 = new BubbleNode[]{ new LongNode(1L), new LongNode(1L) };
		BubbleNode add2 = AddBuiltinNodeFactory.create( args2  );
		BubbleNode[] args3 = new BubbleNode[]{ add1, add2 };
		BubbleNode finalAdd = AddBuiltinNodeFactory.create(args3);
		BubbleNode[] finalexpr = new BubbleNode[]{ finalAdd };
		
		BubbleFunction function = BubbleFunction.create(new FrameSlot[] {},
				finalexpr, context.getGlobalFrame().getFrameDescriptor());
		Object result = function.callTarget.call(new Object[] {context.getGlobalFrame()});
		assertTrue((Long)result== 5l);
	}

}
