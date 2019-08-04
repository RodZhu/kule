package com.spark.app.sql

import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext, sql}

object SparkSqlDemo {

  def main(args: Array[String]): Unit = {
    var conf = new SparkConf().setMaster("local").setAppName("sql demo")
    var sc = new SparkContext(conf)
    var ss = SparkSession.builder().master("local").getOrCreate()


    var rdd1 = sc.makeRDD(List((1, "tom"), (2, "alice"), (3, "rod")))

    var df = ss.createDataFrame(rdd1).toDF("id", "name")

    df.createOrReplaceTempView("user")

    // 直接打印到控制台上
    ss.sql("select * from user").show()

      // 保存到文件中
//    var result = ss.sql("select * from user")
//    result.toJavaRDD.saveAsTextFile("......")


  }
}
