package com.spark.app.sort

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by rod on 2019/7/11.
  */
object Driver {

  def main(args: Array[String]): Unit = {
    var conf = new SparkConf().setMaster("local").setAppName("Sort")

    var sc = new SparkContext(conf)

    var sort = sc.textFile("file:///apps/logs/spark/sort.txt")

    var rdd2 = sort.map { x =>
      var line = x.split(" ")
      var s = new SecondSort(line(0), line(1).toInt)
      (s, line)
    }.sortByKey(false)

    rdd2.foreach(x => {
      print(x._1 +" ->")
      x._2.foreach{x => print(x + " ")}
      println()
    }
    )
  }
}
