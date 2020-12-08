package org.apache.spark.storage;

public class JniWrapper{
//    final private static String LIB_NAME = "jnilibrary";
//
//    static {
//        System.loadLibrary(LIB_NAME);
//    }
    private native int mymadvise(long addr, long size);
    private native int returnzero(long addr, long size);
    public int call_madvise(long addr, long size) {
        System.out.println("call madvise by madvise");
        return (returnzero(addr, size));
    }
}
