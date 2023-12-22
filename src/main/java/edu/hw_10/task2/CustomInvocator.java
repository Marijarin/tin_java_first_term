package edu.hw_10.task2;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Map;

@SuppressWarnings("RegexpSingleLineJava")
public class CustomInvocator implements InvocationHandler {
    final Map<Args, Object> argsToOutput;

    final Map<Method, Boolean> saveModeInfo;
    final Class<?> cl;
    final Object code;

    public CustomInvocator(
        Map<Args, Object> argsToOutput,
        Map<Method, Boolean> saveModeInfo,
        Class<?> cl,
        Object code
    ) {
        this.argsToOutput = argsToOutput;
        this.saveModeInfo = saveModeInfo;
        this.cl = cl;
        this.code = code;
    }

    @Override
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        Args input = new Args(method, args);
        Object result = argsToOutput.get(input);
        System.out.println(result);
        if (result == null && !argsToOutput.containsKey(input)) {
            try {
                result = method.invoke(code, args);
                if (saveModeDisk(method)) {
                    synchronized (this) {
                        saveToDisk(result);
                    }
                }
                argsToOutput.put(input, result);
            } catch (InvocationTargetException e) {
                throw e.getTargetException();
            }
        }
        return result;
    }

    private void saveToDisk(Object obj) {
        String fileName = "src/test/java/edu/hw_10/diskMap.txt";
        try (RandomAccessFile writer = new RandomAccessFile(fileName, "rw");
             FileChannel channel = writer.getChannel()) {
            String oneSet = obj.toString() + "\n";
            byte[] bytes = oneSet.getBytes();
            ByteBuffer byteBuffer = ByteBuffer.allocate(oneSet.length());
            byteBuffer.put(bytes);
            byteBuffer.flip();
            channel.write(byteBuffer);
            byteBuffer.clear();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private boolean areResultsToDiskCheck(Method md) {
        if (md.isAnnotationPresent(Cache.class)) {
            Cache annotation = md.getAnnotation(Cache.class);
            return annotation.persist();
        }
        return false;
    }

    private boolean saveModeDisk(Method md) {
        return saveModeInfo.computeIfAbsent(md, this::areResultsToDiskCheck);
    }
}
