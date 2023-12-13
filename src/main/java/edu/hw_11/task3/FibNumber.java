package edu.hw_11.task3;

import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;
import net.bytebuddy.jar.asm.Type;
import org.jetbrains.annotations.NotNull;

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
        methodVisitor.visitLineNumber(5, label0);
        methodVisitor.visitInsn(Opcodes.ICONST_0);
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
        methodVisitor.visitFrame(Opcodes.F_APPEND, 3, new Object[] {"I", "I", "I"}, 0, null);
        methodVisitor.visitVarInsn(Opcodes.ILOAD, 3);
        methodVisitor.visitVarInsn(Opcodes.ILOAD, 0);
        methodVisitor.visitMethodInsn(Opcodes.IF_ICMPGE, "FibExample", "label4", "()I", false);
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
        methodVisitor.visitInsn(Opcodes.IINC);
        methodVisitor.visitJumpInsn(Opcodes.GOTO, label3);
        Label label4 = new Label();
        methodVisitor.visitLabel(label4);
        methodVisitor.visitLineNumber(11, label4);
        methodVisitor.visitFrame(Opcodes.F_CHOP, 1, null, 0, null);
        methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
        methodVisitor.visitInsn(Opcodes.I2L);
        methodVisitor.visitInsn(Opcodes.LRETURN);
        Label label9 = new Label();
        methodVisitor.visitLabel(label9);
        methodVisitor.visitLocalVariable("oldLast", Type.getType(int.class).getDescriptor(), "I", label6, label8, 4);
        methodVisitor.visitLocalVariable("i", Type.getType(int.class).getDescriptor(), "I", label3, label4, 3);
        methodVisitor.visitLocalVariable("n", Type.getType(int.class).getDescriptor(), "I", label0, label9, 0);
        methodVisitor.visitLocalVariable("last", Type.getType(int.class).getDescriptor(), "I", label1, label9, 1);
        methodVisitor.visitLocalVariable("next", Type.getType(int.class).getDescriptor(), "I", label2, label9, 2);
        methodVisitor.visitMaxs(2, 5);
        methodVisitor.visitEnd();
        return new Size(-3, 2);
    }
}
