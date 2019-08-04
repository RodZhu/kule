package org.apache.flink.quickstart.stduy

import org.apache.flink.api.common.functions.MapFunction
import org.apache.flink.quickstart.average.SensorReading

class MyMapFunction extends MapFunction[String,SensorReading] {

  override def map(t: String): SensorReading = {
    new SensorReading("ee",2L,3)
  }
}
