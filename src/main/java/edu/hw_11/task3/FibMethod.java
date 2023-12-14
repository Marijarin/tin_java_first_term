package edu.hw_11.task3;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.constant.IntegerConstant;
import net.bytebuddy.implementation.bytecode.member.MethodReturn;
import net.bytebuddy.jar.asm.MethodVisitor;
import org.jetbrains.annotations.NotNull;

public enum FibMethod implements ByteCodeAppender {
    INSTANCE;

    @Override
    public @NotNull Size apply(
        @NotNull MethodVisitor mv,
        Implementation.@NotNull Context implementationContext,
        MethodDescription instrumentedMethod
    ) {
        if (!instrumentedMethod.getReturnType().asErasure().represents(int.class)) {
            throw new IllegalArgumentException(instrumentedMethod + " must return int");
        }
        StackManipulation.Size operandStackSize = new StackManipulation.Compound(
            FibNumber.INSTANCE,
            MethodReturn.INTEGER
        ).apply(mv, implementationContext);
        return new Size(operandStackSize.getMaximalSize(),
            instrumentedMethod.getStackSize());
    }
}

