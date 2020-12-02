package org.apache.spark.storage;

public class JniWrapper {
    final private static String LIB_NAME = "jnilibrary";

    static {
        System.loadLibrary(LIB_NAME);
    }

    public native long setBytes(byte[] bytes);

    public native byte[] getBytes(long id);

    public native void freeBytes(long id);

    public native byte getByte(long addr);

    public native boolean madvise(long addr, long size);
}