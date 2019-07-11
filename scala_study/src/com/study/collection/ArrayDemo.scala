package com.study.collection

import scala.collection.mutable.ArrayBuffer

/**
  * Created by rod on 2019/7/6.
  */
object ArrayDemo {
  // 定长数组
  var arr1 = Array(1,2,3)
  var arr2 = new Array[Int](3);

  // 变长数组
  var arr3 = ArrayBuffer(1,2,3,4)
  var arr4 = ArrayBuffer[Int](1,2,3,4)
  for (i <- 1 to 10) {
    arr4.append(i)
  }

  // 定长数组拼接
   var a5 = Array(1,2,3)
   Array.concat(arr1,a5)

  // 不定长数组
  var a6 = ArrayBuffer.concat(arr3, arr4)


  // 生成区间数组
  val a9 =Array.range(1,100,2)

  Array.iterate(1,2)(x=> x*2)
  Array.tabulate(5)(x => x *2)
  a9.foreach(println(_))
  a9.filter(x => x ==3)




}
