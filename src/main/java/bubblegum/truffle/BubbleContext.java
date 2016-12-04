package bubblegum.truffle;

import static bubblegum.truffle.node.builtin.BuiltinNode.createBuiltinFunction;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.MaterializedFrame;
import com.oracle.truffle.api.frame.VirtualFrame;

import bubblegum.truffle.node.arithmetic.AddBuiltinNodeFactory;
import bubblegum.truffle.node.arithmetic.AddMatrixBuiltInNodeFactory;
import bubblegum.truffle.node.arithmetic.MulBuiltinNodeFactory;
import bubblegum.truffle.node.arithmetic.MulMatrixBuiltInNodeFactory;
import bubblegum.truffle.node.arithmetic.SubBuiltinNodeFactory;

public class BubbleContext {
    private final FrameDescriptor globalFrameDescriptor;
    private final MaterializedFrame globalFrame;

    public BubbleContext() {
        this.globalFrameDescriptor = new FrameDescriptor();
        this.globalFrame = this.initGlobalFrame();
    }

    private MaterializedFrame initGlobalFrame() {
        VirtualFrame frame = Truffle.getRuntime().createVirtualFrame(null,
                this.globalFrameDescriptor);
        addGlobalFunctions(frame);
        return frame.materialize();
    }

    private static void addGlobalFunctions(VirtualFrame virtualFrame) {
        FrameDescriptor frameDescriptor = virtualFrame.getFrameDescriptor();
        virtualFrame.setObject(frameDescriptor.addFrameSlot("+"),
                createBuiltinFunction(AddBuiltinNodeFactory.getInstance(),
                        virtualFrame));
        virtualFrame.setObject(frameDescriptor.addFrameSlot("-"),
                createBuiltinFunction(SubBuiltinNodeFactory.getInstance(),
                        virtualFrame));
        virtualFrame.setObject(frameDescriptor.addFrameSlot("*"),
                createBuiltinFunction(MulBuiltinNodeFactory.getInstance(),
                        virtualFrame));
        virtualFrame.setObject(frameDescriptor.addFrameSlot("%*%"),
                createBuiltinFunction(MulMatrixBuiltInNodeFactory.getInstance(),
                        virtualFrame));
        virtualFrame.setObject(frameDescriptor.addFrameSlot("%+%"),
                createBuiltinFunction(AddMatrixBuiltInNodeFactory.getInstance(),
                        virtualFrame));
    }

    /**
     * @return A {@link MaterializedFrame} on the heap that contains all global
     * values.
     */
    public MaterializedFrame getGlobalFrame() {
        return this.globalFrame;
    }

}
