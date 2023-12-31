package edu.hw_2.task4;

public record CallingInfo(String className, String methodName) {
    public static CallingInfo callingInfo() {
        String className;
        String methodName;
        Exception e = new Exception();
        try {
            throw e;
        } catch (Throwable throwable) {
            StackTraceElement[] stacktrace
                = throwable.getStackTrace();
            if (stacktrace.length >= 2) {
                className = stacktrace[1].getClassName();
                methodName = stacktrace[1].getMethodName();
            } else {
                className = stacktrace[stacktrace.length - 1].getClassName();
                methodName = stacktrace[stacktrace.length - 1].getMethodName();
            }
        }
        return new CallingInfo(className, methodName);
    }
}

