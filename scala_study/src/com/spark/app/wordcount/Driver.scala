package com.spark.app.wordcount

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by rod on 2019/7/9.
  */
object Driver {
  def main(args: Array[String]): Unit = {
    var conf = new SparkConf().setMaster("local").setAppName("Word Count")
    //conf.setMaster("spark://10.199.211.183:7077") // 集群模式的master
//    conf.setMaster("local")
    var sc = new SparkContext(conf)
    var word = sc.textFile("file:///apps/logs/spark/test.txt",2)

    Thread.sleep(20000)
    var rdd = word.flatMap{_.split(" ")}.map{(_,1)}.reduceByKey(_+_)
    rdd.foreach(println(_))
  }
}
