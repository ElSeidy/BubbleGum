package bubblegum.truffle.node.arithmetic;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.dsl.GenerateNodeFactory;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import com.oracle.truffle.api.nodes.NodeInfo;

import bubblegum.truffle.matrix.BooleanMatrix;
import bubblegum.truffle.matrix.DoubleMatrix;
import bubblegum.truffle.node.builtin.BuiltinNode;

@NodeInfo(shortName = "%+%")
@GenerateNodeFactory
public abstract class AddMatrixBuiltInNode extends BuiltinNode{
	
	@Specialization
	@ExplodeLoop
    protected BooleanMatrix add(BooleanMatrix A, BooleanMatrix B) {
		final int m = A.getRows();
		final int l = A.getCols();
		final int n = B.getCols();
		CompilerAsserts.compilationConstant(m);
		CompilerAsserts.compilationConstant(n);
		CompilerAsserts.compilationConstant(l);
		final BooleanMatrix C = BooleanMatrix.createZeros(m,n); 
		for(int i=0; i< m; i++){
			for(int j=0; j< n; j++)
				for(int k=0; k< l; k++)
					C.getData()[i][j]|=A.getData()[i][k]&B.getData()[k][j];
		}
        return C;
    }
	
	@Specialization
	@ExplodeLoop
    protected DoubleMatrix add(DoubleMatrix A, DoubleMatrix B) {
		final int m = A.getRows();
		final int n = A.getCols();
		CompilerAsserts.compilationConstant(m);
		CompilerAsserts.compilationConstant(n);
		final DoubleMatrix C = DoubleMatrix.createZeros(m,n); 
		for(int i=0; i< m; i++)
			for(int j=0; j< n; j++)
				C.getData()[i][j]=A.getData()[i][j]+B.getData()[i][j];
		
        return C;
    }

}
