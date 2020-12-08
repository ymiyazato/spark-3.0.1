package org.apache.spark.storage;
import org.apache.spark.internal.Logging;

public class JniWrapper extends Logging{
//    final private static String LIB_NAME = "jnilibrary";
//
//    static {
//        System.loadLibrary(LIB_NAME);
//    }
    private native int madvise(long addr, long size);
    public int call_madvise(long addr, long size){
        logInfo(s"start call madvise in jniwrapper");
        return (madvise(addr, size));
    }
}