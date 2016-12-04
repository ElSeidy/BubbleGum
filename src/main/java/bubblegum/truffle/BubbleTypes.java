package bubblegum.truffle;

import com.oracle.truffle.api.dsl.TypeSystem;

import bubblegum.truffle.matrix.BooleanMatrix;
import bubblegum.truffle.matrix.DoubleMatrix;

@TypeSystem({long.class, boolean.class, BooleanMatrix.class, DoubleMatrix.class, BubbleFunction.class, String.class})
public class BubbleTypes {

}
