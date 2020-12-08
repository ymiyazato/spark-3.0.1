package org.apache.spark.storage;

public class JniWrapper{
//    final private static String LIB_NAME = "jnilibrary";
//
//    static {
//        System.loadLibrary(LIB_NAME);
//    }
    private native int my_madvise(long addr, long size);
    private native int returnzero();
    public int call_madvise(long addr, long size) {
        System.out.println("call madvise by madvise");
        return (my_madvise(addr, size));
    }
}
