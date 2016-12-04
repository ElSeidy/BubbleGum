package bubblegum.truffle.node.arithmetic;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.dsl.GenerateNodeFactory;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import com.oracle.truffle.api.nodes.NodeInfo;

import bubblegum.truffle.matrix.BooleanMatrix;
import bubblegum.truffle.matrix.DoubleMatrix;
import bubblegum.truffle.node.builtin.BuiltinNode;

@NodeInfo(shortName = "%*%")
@GenerateNodeFactory
public abstract class MulMatrixBuiltInNode extends BuiltinNode{
	
	
	@Specialization
	@ExplodeLoop
    public BooleanMatrix multiply(BooleanMatrix A, BooleanMatrix B) {
		final int m = A.getRows();
		final int l = A.getCols();
		final int n = B.getCols();
		CompilerAsserts.compilationConstant(m);
		CompilerAsserts.compilationConstant(n);
		CompilerAsserts.compilationConstant(l);
		final BooleanMatrix C = BooleanMatrix.createZeros(m,n);
		
		System.out.println(CompilerDirectives.inCompiledCode() +" "+CompilerDirectives.inInterpreter()); 
		for(int i=0; i< m; i++){
			CompilerDirectives.injectBranchProbability(0.00001, i % 7000 == 0);
			for(int j=0; j< n; j++)
				for(int k=0; k< l; k++)
					C.getData()[i][j]|=A.getData()[i][k]&B.getData()[k][j];
		}
        return C;
    }
	
	@Specialization
	@ExplodeLoop
    public DoubleMatrix multiply(DoubleMatrix A, DoubleMatrix B) {
		final int m = A.getRows();
		final int l = A.getCols();
		final int n = B.getCols();
		CompilerAsserts.compilationConstant(m);
		CompilerAsserts.compilationConstant(n);
		CompilerAsserts.compilationConstant(l);
		final DoubleMatrix C = DoubleMatrix.createZeros(m,n);
		
		System.out.println(CompilerDirectives.inCompiledCode() +" "+CompilerDirectives.inInterpreter()); 
		for(int i=0; i< m; i++){
			CompilerDirectives.injectBranchProbability(0.00001, i % 7000 == 0);
			for(int j=0; j< n; j++)
				for(int k=0; k< l; k++)
					C.getData()[i][j]+=A.getData()[i][k]*B.getData()[k][j];
		}
        return C;
    }
}
