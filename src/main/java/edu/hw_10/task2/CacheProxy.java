package edu.hw_10.task2;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.ConcurrentHashMap;

public class CacheProxy {
    private CacheProxy() {
    }

    @SuppressWarnings("unchecked")
    public static <T> T create(final Class<T> cl, final T code) {

        final var argsToOutput = new ConcurrentHashMap<Args, Object>();

        final var saveModeInfo = new ConcurrentHashMap<Method, Boolean>();

        return (T) Proxy.newProxyInstance(
            cl.getClassLoader(),
            new Class<?>[] {cl},
            new CustomInvocator(argsToOutput, saveModeInfo, cl, code)
        );
    }
}
