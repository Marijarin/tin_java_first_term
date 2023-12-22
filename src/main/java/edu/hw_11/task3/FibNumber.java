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
        methodVisitor.visitCode();
        // if (n <= 1)
        Label l1 = new Label();
        methodVisitor.visitVarInsn(Opcodes.ILOAD, 0);
        methodVisitor.visitInsn(Opcodes.ICONST_1);
        methodVisitor.visitJumpInsn(Opcodes.IF_ICMPGT, l1);
        // return n
        methodVisitor.visitVarInsn(Opcodes.ILOAD, 0);
        methodVisitor.visitInsn(Opcodes.I2L);
        methodVisitor.visitInsn(Opcodes.LRETURN);
        // if (n > 1)
        methodVisitor.visitLabel(l1);
        methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
        // fib(n - 1)
        methodVisitor.visitVarInsn(Opcodes.ILOAD, 0);
        methodVisitor.visitInsn(Opcodes.ICONST_1);
        methodVisitor.visitInsn(Opcodes.ISUB);
        methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "edu/hw_11/FibExample", "fib", "(I)J", false);
        // fib(n - 2)
        methodVisitor.visitVarInsn(Opcodes.ILOAD, 0);
        methodVisitor.visitInsn(Opcodes.ICONST_2);
        methodVisitor.visitInsn(Opcodes.ISUB);
        methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "edu/hw_11/FibExample", "fib", "(I)J", false);
        // fib(n - 1) + fib(n - 2);
        methodVisitor.visitInsn(Opcodes.LADD);
        methodVisitor.visitInsn(Opcodes.LRETURN);
        methodVisitor.visitEnd();
        return new Size(-1, 4);
    }
}
