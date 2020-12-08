package org.apache.spark.storage;

public class JniWrapper{
//    final private static String LIB_NAME = "jnilibrary";
//
//    static {
//        System.loadLibrary(LIB_NAME);
//    }
    private native int myMadvise(long startAddr, long hugePageSize);
    public int call_madvise(long startAddr, long hugePageSize) {
        //System.out.println("call madvise by madvise");
        return (myMadvise(startAddr, hugePageSize));
    }
}
