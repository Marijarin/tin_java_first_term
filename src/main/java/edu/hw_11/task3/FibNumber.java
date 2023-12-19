package edu.hw_11.task3;

import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("MagicNumber")
enum FibNumber implements StackManipulation {

    INSTANCE; // singleton

    @Override
    public boolean isValid() {
        return true;
    }

    @SuppressWarnings({"LineLength", "MultipleStringLiterals"})
    @Override
    public @NotNull Size apply(
        MethodVisitor methodVisitor,
        Implementation.@NotNull Context implementationContext
    ) {
        Label l0 = new Label();
        methodVisitor.visitLabel(l0);
        methodVisitor.visitLineNumber(9, l0);
        methodVisitor.visitInsn(Opcodes.LCONST_0);
        methodVisitor.visitVarInsn(Opcodes.LSTORE, 1);
        Label l1 = new Label();
        methodVisitor.visitLabel(l1);
        methodVisitor.visitLineNumber(10, l1);
        methodVisitor.visitInsn(Opcodes.LCONST_1);
        methodVisitor.visitVarInsn(Opcodes.LSTORE, 3);
        Label l2 = new Label();
        methodVisitor.visitLabel(l1);
        methodVisitor.visitLineNumber(11, l2);
        methodVisitor.visitInsn(Opcodes.LCONST_0);
        methodVisitor.visitVarInsn(Opcodes.LSTORE, 5);
        Label l3 = new Label();
        methodVisitor.visitLabel(l3);
        methodVisitor.visitFrame(
            Opcodes.F_APPEND,
            3,
            new Object[] {Opcodes.LONG, Opcodes.LONG, Opcodes.INTEGER},
            0,
            null
        );
        methodVisitor.visitVarInsn(Opcodes.ILOAD, 5);
        methodVisitor.visitVarInsn(Opcodes.ILOAD, 0);
        Label l4 = new Label();
        methodVisitor.visitJumpInsn(Opcodes.IF_ICMPGE, l4);
        Label l5 = new Label();
        methodVisitor.visitLineNumber(12, l5);
        methodVisitor.visitVarInsn(Opcodes.LLOAD, 1);
        methodVisitor.visitVarInsn(Opcodes.LSTORE, 6);
        Label l6 = new Label();
        methodVisitor.visitLineNumber(13, l6);
        methodVisitor.visitVarInsn(Opcodes.LLOAD, 3);
        methodVisitor.visitVarInsn(Opcodes.LSTORE, 1);
        Label l7 = new Label();
        methodVisitor.visitLineNumber(14, l7);
        methodVisitor.visitVarInsn(Opcodes.LLOAD, 6);
        methodVisitor.visitVarInsn(Opcodes.LLOAD, 3);
        methodVisitor.visitInsn(Opcodes.LADD);
        methodVisitor.visitVarInsn(Opcodes.LSTORE, 3);
        Label l8 = new Label();
        methodVisitor.visitLineNumber(11, l8);
        methodVisitor.visitIincInsn(5, 1);
        methodVisitor.visitJumpInsn(Opcodes.GOTO, l3);
        methodVisitor.visitLineNumber(16, l4);
        methodVisitor.visitFrame(Opcodes.F_CHOP, 1, null, 0, null);
        methodVisitor.visitVarInsn(Opcodes.LLOAD, 1);
        methodVisitor.visitInsn(Opcodes.LRETURN);
        Label l9 = new Label();
        methodVisitor.visitLocalVariable("oldLast", "Ljava/lang/Long;", "J", l6, l8, 6);
        methodVisitor.visitLocalVariable("i", "Ljava/lang/Integer;", "I", l3, l4, 5);
        methodVisitor.visitLocalVariable("n", "Ljava/lang/Integer;", "I", l0, l9, 0);
        methodVisitor.visitLocalVariable("last", "Ljava/lang/Long;", "J", l1, l9, 1);
        methodVisitor.visitLocalVariable("next", "Ljava/lang/Long;", "J", l2, l9, 3);
        methodVisitor.visitMaxs(4, 8);
        methodVisitor.visitEnd();
        return new Size(-8, 4);
    }
}
