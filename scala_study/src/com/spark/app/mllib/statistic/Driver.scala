package com.spark.app.mllib.statistic

import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.stat.Statistics
import org.apache.spark.{SparkConf, SparkContext}

object Driver {
  def main(args: Array[String]): Unit = {
    var conf = new SparkConf().setMaster("local").setAppName("statistic")
    var sc = new SparkContext(conf)

    // 相当于定义5个一维向量
    var rdd = sc.makeRDD(List(1,2,3,4,5))

    // 将rdd转成一维向量数组
    var  rd = rdd.map(x => Vectors.dense(x))

    var result = Statistics.colStats(rd)

    println(result.max)
    println(result.min)
    println(result.count)
    println(result.variance) // 数据集方差
    println(result.normL1) // 曼哈顿距离
    println(result.normL2) // 欧式距离
    println(result.numNonzeros) // 统计不为0个数
    println(result.mean) // 统计均值

  }
}
