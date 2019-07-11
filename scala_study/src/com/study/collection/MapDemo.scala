package com.study.collection

/**
  * Created by rod on 2019/7/7.
  */
object MapDemo {

  // 定长
  val m1 = Map("name" ->20, "age" -> 40)

  // 不定长
  val m2= scala.collection.mutable.Map("name" ->"ddd", "age" -> 30)

  // 取值 如果key不存在会抛异常
  m1.apply("name")
  m1("name")

  // 取值，若key不存在，不会抛异常
  m1.get("name")

  // 操作map中的值
  m1.mapValues(x => x.toString.toInt + 2)

  // 过滤map
  m1.filter{case(key,value) => value > 30}


  def main(args: Array[String]): Unit = {
    var s= m1.filter{case(key,value) =>
      // print("key: " + key + " - value: " + value)
      value > 30}

    s.map(x => {
      print(x._1 + ": " + x._2)
    })
  }

}
