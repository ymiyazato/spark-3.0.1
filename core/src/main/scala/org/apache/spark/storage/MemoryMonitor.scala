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
    val memoryMap = memoryStore.getAddrAndSize()
    logInfo(s"entry num = ${memoryMap.size}")
    for ((addr, size) <- memoryMap){
      logInfo(s"${addr} : ${size}")
    }
  }

}