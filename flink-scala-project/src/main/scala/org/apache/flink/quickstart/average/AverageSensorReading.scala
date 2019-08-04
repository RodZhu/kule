package org.apache.flink.quickstart.average

import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.scala.function.WindowFunction
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.api.windowing.windows.TimeWindow
import org.apache.flink.util.Collector

/**
  * import org.apache.flink.streaming.api.TimeCharacteristic
  * import org.apache.flink.streaming.api.scala._
  * import org.apache.flink.streaming.api.scala.function.WindowFunction
  * import org.apache.flink.streaming.api.windowing.time.Time
  * import org.apache.flink.streaming.api.windowing.windows.TimeWindow
  * import org.apache.flink.util.Collector
  */
object AverageSensorReading {
  def main(args: Array[String]): Unit = {
    var env = StreamExecutionEnvironment.getExecutionEnvironment
//    StreamExecutionEnvironment
    println("------> " + env.getParallelism)
    println(Long.MinValue - 60000)
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)
    var sensorData: DataStream[SensorReading] = env.addSource(new SensorSource).assignTimestampsAndWatermarks(new SensorTimeAssigner)
    var avgTemp: DataStream[SensorReading] = sensorData.map(r => {
      val celsius = (r.temperature - 32) * (5.0 / 9.0)
      SensorReading(r.id, r.timestamp, celsius)
    }).keyBy(_.id).timeWindow(Time.seconds(5)).apply(new TemperatureAverager)
    avgTemp.print()
    env.execute("Compute average sensor temperature")
  }
}

/** User-defined WindowFunction to compute the average temperature of SensorReadings */
class TemperatureAverager extends WindowFunction[SensorReading, SensorReading, String, TimeWindow] {

  /** apply() is invoked once for each window */
  override def apply(
                        sensorId: String,
                        window: TimeWindow,
                        vals: Iterable[SensorReading],
                        out: Collector[SensorReading]): Unit = {

    // compute the average temperature
    val (cnt, sum) = vals.foldLeft((0, 0.0))((c, r) => (c._1 + 1, c._2 + r.temperature))
    val avgTemp = sum / cnt
    // emit a SensorReading with the average temperature
    out.collect(SensorReading(sensorId, window.getEnd, avgTemp))
  }
}