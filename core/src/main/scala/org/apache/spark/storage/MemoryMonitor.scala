package org.apache.spark.storage

import org.apache.spark.storage.memory._
import org.apache.spark.internal.Logging

private[spark] class MemoryMonitor(memoryStore : MemoryStore) extends Logging with Runnable{
  var count = 0
  var running = true
  def run() = {
    while (running) {
      logInfo(s"ymiyazato monitor test ${count}")
      scanMemoryEntries()
      count = count + 1
      Thread.sleep(10000)
    }
  }
  def stopRunning(): Unit ={
    running = false
  }

  def scanMemoryEntries(): Unit = {
    val hugePageSize : Long = 2 * 1024 * 1024
    val mask : Long= 0xffffffffff000L
    val threshHold : Double= 0.7
    var addresses : Array[Long] = Array.empty
    val memoryMap = memoryStore.getAddrAndSize()

    logInfo(s"entry num = ${memoryMap.size}")

    var pageStartAddr : Long = -1L
    var pageEndAddr : Long = 0L
    var objectStartAddr : Long = 0L
    var objectEndAddr : Long = 0L
    var used : Long = 0L
    for ((key, value) <- memoryMap.sorted){
      objectStartAddr = key
      objectEndAddr = key + value

      val objectBelongPageAddr = objectStartAddr & mask
      if (pageStartAddr != objectBelongPageAddr){
        if (used.toDouble / hugePageSize.toDouble > threshHold) {
          addresses = addresses :+ pageStartAddr
        }
        used = 0
        pageStartAddr = objectBelongPageAddr
        pageEndAddr = pageStartAddr + hugePageSize
      }

      while (pageEndAddr < objectEndAddr){
        used = used + (pageEndAddr - objectStartAddr)
        if (used.toDouble / hugePageSize.toDouble > threshHold) {
          addresses = addresses :+ pageStartAddr
        }
        pageStartAddr = pageEndAddr
        pageEndAddr = pageStartAddr + hugePageSize
        objectStartAddr = pageStartAddr
        used = 0
      }
      used = used + (objectEndAddr - objectStartAddr)
    }
    if (used.toDouble / hugePageSize.toDouble > threshHold) {
      addresses = addresses :+ pageStartAddr
    }
    logInfo(s"huge page num = ${addresses.size}")
    for (addr <- addresses){
      logInfo(s"huge page address = ${addr}")
    }
  }

}