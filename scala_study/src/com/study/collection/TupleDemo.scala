package com.study.collection

/**
  * Created by rod on 2019/7/7.
  */
object TupleDemo {
  // 声明元组
  val t1 = ("tome", "rose", "jary")

  // 元组可以存任何类型
  var t2 = ("tom",Array(1,2,3), List(3,4,5))

  // 元组嵌套
  var t3 = (("222",23), ("eee",33))

  // 元组取值
  var l1 = List("a a","b bb","c cc")
  def main(args: Array[String]): Unit = {
    //    print(t1._1)
    var tt2 = l1.map(x => (x, 1))
    tt2.map { case (key, value) => println(key) }

    var r1 = l1.map(x => x.split(" "))
    var r2 = l1.map { x => x.split(" ") }
    println {
      "r1: " + r1
    }
    println {
      "r2: " + r2
    }

    var m1 = Map("tome" -> List(("tome", 200), ("tome", 210)), "rose" -> List(("rose", 200), ("rose", 210)))

    var m2 = m1.mapValues { list => list.map(x => x._2) }
    println(m1)
    println(m2)


    var l4 = List("hello hadoop", "hello 1804", "hello hadoop")
    // list(List(("hello", 1),("hadoop", 1)))
    var m3: scala.collection.mutable.Map[String, Int] = scala.collection.mutable.Map()

    var t2 = l4.map(line => line.split(" ").map(x => (x, 1))).map { x =>
      x.map(str => {
        var count = m3.get(str._1).getOrElse(0);
        m3.put(str._1, count + str._2)
      })

      println(m3)
    }

    var l4_1 = l4.flatMap(x => x.split(" "))
    println(l4_1)
    var l4_2 = l4_1.groupBy(x => x)
    println(l4_2)
    var l4_3 = l4_2.mapValues(x => x.size).foreach(println(_))
    println(l4_3)
  }
}
