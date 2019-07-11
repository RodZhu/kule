package com.study

object HelloWorld {
  def main(args: Array[String]): Unit = {
    // 变量可以修改
    var a = 10
    // val 修饰的是常量，一旦赋值，不可更改。
    val b:Int = 10
    var str = "hello word"
    //println("hello world")


//    println(str.head)
//    println(str.tail)
//
    println(str.count(x => { x.equals('l') }));

    // 生成区间方法
    var ss = b.to(20,2)
    // 化简写法，效果和上面相同
    1 to 20 by 2
    ss.map(x => println(x))

    // scala中的操作符号等于方法
    // scala会将基本类型int专转换城RichInt

    b.+(2).*(2)
    // 正数2
    var v1 = +2
    // 负数2
    var v2 = -2
    var v3 = !true
    var v4 = ~0XFF
  }
}
