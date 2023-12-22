package edu.hw_10.task2;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CacheProxy {
    private CacheProxy() {
    }

    @SuppressWarnings("unchecked")
    public static <T> T create(final Class<T> cl, final T code) {

        final Map<Args, Object> argsToOutput = new ConcurrentHashMap<>();

        return (T) Proxy.newProxyInstance(
            cl.getClassLoader(),
            new Class<?>[] {cl},
            new CustomInvocator(argsToOutput, cl, code)
        );
    }
}
