package org.apache.spark.storage;

public class JniWrapper{
//    final private static String LIB_NAME = "jnilibrary";
//
//    static {
//        System.loadLibrary(LIB_NAME);
//    }
    private native int mymadvise(long startAddr, long hugePageSize);
    private native int returnzero(long num, long numz);
    public int call_madvise(long startAddr, long hugePageSize) {
        System.out.println("call madvise by madvise");
        return (mymadvise(startAddr, hugePageSize));
    }
}
