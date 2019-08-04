package com.spark.app.streaming

import org.apache.spark.streaming.kafka010.KafkaUtils
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}

object kafkaDemo {


  def main(args: Array[String]): Unit = {
    // 对2.3.0之前的kafka消费线程至少是2个 local[2] 表示开启两个线程，一个用于消费kafka消息，一个用于监听流数据
    var conf = new SparkConf().setMaster("local").setAppName("KafkaDemo").set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    var sc = new SparkContext(conf)
    var ssc = new StreamingContext(sc, Seconds(2))

//    var zkHosts = "10.199.164.183:2181,10.199.164.184:2181"
//    var group = "spark-test"
    // map用来指定消费的主题已经消费的线程数。数据结构用map来表示，其中key为主题，value为消费该主题的线程数
//    val topics = Map("spark-test" -> 1)


    val kafkaParams = Map[String, Object](
      "bootstrap.servers" -> "10.199.242.226:9092,10.199.198.37:9092",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> "use_a_separate_group_id_for_each_stream",
      "auto.offset.reset" -> "latest",
      "enable.auto.commit" -> (false: java.lang.Boolean)
    )

    val topicsSet = Array("spark-test")
    val stream = KafkaUtils.createDirectStream[String, String](
      ssc,
      PreferConsistent,
      Subscribe[String, String](topicsSet, kafkaParams)
    )

    println("=======================")
    stream.print()
    stream.foreachRDD(x => {
      x.foreach( tm => println("value ===" + tm.value()))
    })
    ssc.start()
    ssc.awaitTermination()
  }
}
