package edu.hw_10.task2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CustomInvocationHandler implements InvocationHandler {
 Method method;

    public CustomInvocationHandler(Method method) {
        this.method = method;
    }
    public CustomInvocationHandler() {}

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method == null) {
            return null;
        }
        return method.invoke(args);
    }
}
