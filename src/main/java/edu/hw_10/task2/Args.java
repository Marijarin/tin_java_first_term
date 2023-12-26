package edu.hw_10.task2;

import java.lang.reflect.Method;
import java.util.Objects;

@SuppressWarnings("MagicNumber")
public final class Args {
    private final Method mMethod;

    private final Object[] mArgs;

    private final int mHash;

    Args(final Method m, final Object[] args) {
        this.mMethod = m;
        this.mArgs = args;
        // precalculate hash
        this.mHash = calcHash();
    }

    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass") @Override
    public boolean equals(final Object obj) {
        final Args other = (Args) obj;
        if (!mMethod.equals(other.mMethod)) {
            return false;
        }
        for (int i = 0; i < mArgs.length; ++i) {
            Object o1 = mArgs[i];
            Object o2 = other.mArgs[i];
            if (!(Objects.equals(o1, o2))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return mHash;
    }

    public int calcHash() {
        int h = mMethod.hashCode();
        for (final Object o : mArgs) {
            h = h * 65599 + (o == null ? 0 : o.hashCode());
        }
        return h;
    }
}
