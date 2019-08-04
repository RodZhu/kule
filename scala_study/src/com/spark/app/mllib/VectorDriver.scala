package com.spark.app.mllib

import org.apache.spark.mllib.linalg.Vectors

object VectorDriver {

  def main(args: Array[String]): Unit = {
    // 创建密集型向量， 只处理Double，如果Int，可以自动转Double
    val v1 = Vectors.dense(1,2,3,4,5)

    val a1 = Array[Double](1,2,3,4)


    val v2 = Vectors.dense(a1)
    println(v1)


    // 创建稀疏向量
    // 稀疏向量: 概念只是向量中很多0
    // 参数解释：第一个参数数组的长度，第二个数组指定向量的位置 第三个数组传入的位置对应的数据
    // 其他位置定的位置都是0
    val v3= Vectors.sparse(5, Array(1,3), Array(2.1,5.6))
    println(v3(3))

  }
}
