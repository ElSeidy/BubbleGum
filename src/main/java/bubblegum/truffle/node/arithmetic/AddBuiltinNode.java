package bubblegum.truffle.node.arithmetic;

import com.oracle.truffle.api.ExactMath;
import com.oracle.truffle.api.dsl.GenerateNodeFactory;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;

import bubblegum.truffle.node.builtin.BuiltinNode;

@NodeInfo(shortName = "+")
@GenerateNodeFactory
public abstract class AddBuiltinNode extends BuiltinNode {
    
	@Specialization(rewriteOn = ArithmeticException.class)
    public long add(long value0, long value1) {
        return ExactMath.addExact(value0, value1);
    }

}
