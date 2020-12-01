package org.apache.spark.storage

import org.apache.spark.storage.memory._
import org.apache.spark.internal.Logging

private[spark] class MemoryMonitor(memoryStore : MemoryStore) extends Logging with Runnable{
  var count = 0
  var running = true
  def run() = {
    while (running) {
      logInfo(s"ymiyazato monitor test ${count}")
      count = count + 1
      Thread.sleep(10000)
    }
  }
  def stopRunning(): Unit ={
    running = false
  }

  def scanMemoryEntries(): Unit = {
    logInfo(s"entry num = ${memoryStore.size}")
    for ((addr, size) <- memoryStore.getAddrAndSize()){
      logInfo(s"${count} : ${size}")
    }
  }

}