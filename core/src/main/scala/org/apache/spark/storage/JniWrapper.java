package org.apache.spark.storage;

public class JniWrapper {
    final private static String LIB_NAME = "jnilibrary";

    static {
        System.loadLibrary(LIB_NAME);
    }
    public native int madvise(long addr, long size);
}