package bubblegum.truffle.node.arithmetic;

import com.oracle.truffle.api.dsl.GenerateNodeFactory;
import com.oracle.truffle.api.dsl.Specialization;

import bubblegum.truffle.node.builtin.BuiltinNode;

@GenerateNodeFactory
public abstract class SubBuiltinNode extends BuiltinNode {
    @Specialization
    protected long minus(long value0, long value1) {
        return value0 - value1;
    }
}
