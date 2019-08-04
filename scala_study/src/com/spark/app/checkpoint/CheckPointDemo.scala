package com.spark.app.checkpoint

import org.apache.spark.{SparkConf, SparkContext}

class CheckPointDemo {

  def main(args: Array[String]): Unit = {

    var conf = new SparkConf()
    conf.setMaster("local")
    conf.setAppName("Median App")

    var sc = new SparkContext(conf)

    //设置check point
    sc.setCheckpointDir("hdfs://10.1.1.1:9000/checkpoint/")


    var wordtext = sc.textFile("file:///apps/logs/spark/median.txt")
    wordtext.cache()
    wordtext.checkpoint()


    var rdd1 = wordtext.flatMap { x => {
      x.split(" ").map(x => x.toInt)
    }
    }.sortBy(x => x)


    println("================1====================")
    rdd1.foreach(x => print(x + " "))

    var index = (rdd1.count() + 1) / 2
    println("================2====================")
    // 1 2 4 5 6 7 8 10 11 17 19 20 23 29 45
    println(rdd1.take(index.toInt).last)


    //    rdd1.foreach(println(_))
    //    println(wordtext.glom().collect())


  }

}
