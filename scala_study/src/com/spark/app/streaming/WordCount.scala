package com.spark.app.streaming

import org.apache.spark.streaming._
import org.apache.spark.{SparkConf, SparkContext}

object WordCount {

  def main(args: Array[String]): Unit = {
    var conf = new SparkConf().setMaster("local").setAppName("wordcount")
    var sc = new SparkContext(conf)

    // 窗口周期为5秒
    var stream = new StreamingContext(sc, Seconds(4))
    //存放检查点目录，可以对之前的数据进行累加
    stream.checkpoint("/apps/chk")
    // 监听指定目录下是否有文件
    var datastream = stream.textFileStream("file:///apps/logs/spark/count")

    // 更改窗口可以进行.reduceByWindow()，也就是说可以接window函数。
    // 很重要的是 滑动窗口必须是batch的整数倍。
    var r1 = datastream.flatMap(x => x.split(" ")).map{x => (x,1)}.reduceByKeyAndWindow((x:Int, y: Int) => {x + y}, Seconds(4), Seconds(8))

    // updateStateByKey可以将之前的结果保存在checkpoint目录下 ，并可以对历史的数据进行整合
    r1.updateStateByKey{(seq,op:Option[Int]) => Some(seq.sum + op.getOrElse(0))}
    r1.print()

    stream.start()             // Start the computation
    stream.awaitTermination()
  }
}
