package org.apache.flink.quickstart.stduy

import org.apache.flink.api.common.functions.FilterFunction
import org.apache.flink.quickstart.average.SensorReading

 class FlinkFilter extends FilterFunction[SensorReading]{
  override def filter(t: SensorReading): Boolean = {
       true
  }
}
