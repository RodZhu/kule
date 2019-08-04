package org.apache.flink.quickstart.stduy

import org.apache.flink.api.common.functions.{RichFlatMapFunction, RuntimeContext}
import org.apache.flink.configuration.Configuration
import org.apache.flink.util.Collector

class MyRichFlatMap extends RichFlatMapFunction {

  override def flatMap(value: Nothing, out: Collector[Nothing]): Unit = ???

  //初始化方法，
  override def open(parameters: Configuration): Unit = {
    getRuntimeContext.getIndexOfThisSubtask
  }
  override def close(): Unit = {

  }
}
