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

    @Override
    public @NotNull Size apply(
        MethodVisitor methodVisitor,
        Implementation.@NotNull Context implementationContext
    ) {
        methodVisitor.visitCode();
        Label label0 = new Label();
        methodVisitor.visitLabel(label0);
        methodVisitor.visitLineNumber(3, label0);
        methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
        methodVisitor.visitVarInsn(Opcodes.ISTORE, 1);
        Label label1 = new Label();
        methodVisitor.visitLabel(label1);
        methodVisitor.visitInsn(Opcodes.ICONST_1);
        methodVisitor.visitVarInsn(Opcodes.ISTORE, 2);
        Label label2 = new Label();
        methodVisitor.visitLabel(label2);
        methodVisitor.visitLineNumber(6, label2);
        methodVisitor.visitInsn(Opcodes.ICONST_0);
        methodVisitor.visitVarInsn(Opcodes.ISTORE, 3);
        Label label3 = new Label();
        methodVisitor.visitLabel(label3);
        methodVisitor.visitFrame(Opcodes.F_APPEND, 3, new Object[] {"I", "I", "I"}, 2, null);
        methodVisitor.visitVarInsn(Opcodes.ILOAD, 3);
        methodVisitor.visitVarInsn(Opcodes.ILOAD, 0);
        Label label4 = new Label();
        methodVisitor.visitJumpInsn(Opcodes.IF_ICMPGE, label4);
        Label label5 = new Label();
        methodVisitor.visitLabel(label5);
        methodVisitor.visitLineNumber(7, label5);
        methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
        methodVisitor.visitVarInsn(Opcodes.ISTORE, 4);
        Label label6 = new Label();
        methodVisitor.visitLabel(label6);
        methodVisitor.visitLineNumber(8, label6);
        methodVisitor.visitVarInsn(Opcodes.ILOAD, 2);
        methodVisitor.visitVarInsn(Opcodes.ISTORE, 1);
        Label label7 = new Label();
        methodVisitor.visitLabel(label7);
        methodVisitor.visitLineNumber(9, label7);
        methodVisitor.visitVarInsn(Opcodes.ILOAD, 4);
        methodVisitor.visitVarInsn(Opcodes.ILOAD, 2);
        methodVisitor.visitInsn(Opcodes.IADD);
        methodVisitor.visitVarInsn(Opcodes.ISTORE, 2);
        Label label8 = new Label();
        methodVisitor.visitLabel(label8);
        methodVisitor.visitLineNumber(6, label8);
        methodVisitor.visitVarInsn(Opcodes.IINC, 3);
        methodVisitor.visitJumpInsn(Opcodes.GOTO, label3);
        methodVisitor.visitLabel(label4);
        methodVisitor.visitLineNumber(11, label4);
        methodVisitor.visitVarInsn(Opcodes.F_CHOP, 1);
        methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
        methodVisitor.visitInsn(Opcodes.I2L);
        methodVisitor.visitInsn(Opcodes.LRETURN);
        Label label9 = new Label();
        methodVisitor.visitLabel(label9);
        methodVisitor.visitLocalVariable("n", "I", null, label0, label9, 0);
        methodVisitor.visitLocalVariable("last", "I", null, label1, label9, 1);
        methodVisitor.visitLocalVariable("next", "I", null, label2, label9, 2);
        methodVisitor.visitLocalVariable("i", "I", null, label3, label4, 3);
        methodVisitor.visitLocalVariable("oldLast", "I", null, label6, label8, 4);

        methodVisitor.visitMaxs(2, 5);
        methodVisitor.visitEnd();
        return new Size(-2, 5);
    }
}
