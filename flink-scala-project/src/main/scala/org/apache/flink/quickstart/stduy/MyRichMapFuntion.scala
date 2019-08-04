package org.apache.flink.quickstart.stduy

import org.apache.flink.api.common.functions.{RichFlatMapFunction, RichMapFunction}
import org.apache.flink.quickstart.average.SensorReading
import org.apache.flink.util.Collector

class MyRichMapFuntion extends RichMapFunction[String, SensorReading] {
  override def map(value: String): SensorReading = ???
}
