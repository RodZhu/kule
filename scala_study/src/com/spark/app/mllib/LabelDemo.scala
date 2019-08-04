package com.spark.app.mllib

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LabeledPoint

object LabelDemo {

  // label point： 向量标签对于mllib中机器学习算法的不同值做标记。例如分类问题，可以将不同
  // 的数据集分成若干份，以整型0，1，2...进行标记，即程序的编写者可以根据自己的需要对数据进行标记
  def main(args: Array[String]): Unit = {
    val v1= Vectors.dense(1.1,2.5,3.4)
    val l1=LabeledPoint(1,v1)

    println(l1)
    println(l1.label) // 只获取类标号
    println(l1.features) // 只获取特征值


    val conf = new SparkConf().setMaster("local").setAppName("lb")
    val sc = new SparkContext(conf)

    // 其中mllib.txt的内容如下
    // 2.3 3.2 1
    // 1.6 2.3 2
    // 5.1 7.2 1
    // 6.1 1.5 2
    // 3.1 2.1 1
    // 9.1 2.1 1
    val data = sc.textFile("/apps/logs/mllib.txt")

    val result = data.map{
      line => line.split(" ").map{ num => num.toDouble}}.map{
        arr =>
          val label=arr(2)
          val features= arr.dropRight(1)
          LabeledPoint(label, Vectors.dense(features))
      }
    result.foreach(println(_))
  }
}
