package com.study

/**
  * Created by rod on 2019/6/12.
  */
object FunctionDemo {

  // 定义函数:  方法名称(参数列表s:String):返回值
  // scala会根据返回值类型自动推断返回值类型，前提是函数的定义必须有=号，例如function3，function4不可自动推断
  // function2 等价function4
  // 函数不需要加访问修饰符，默认public不需要手动添加
  def function1(): String = {
    println("function1")
    "function1"
  }

  def function2(s: String): Unit = {

  }

  // 有默认值的参数
  def function3(s: String = "ddd") = {

  }

  protected def function4(s: String) {

  }

  // scala范型用[]表示
  def function5(s: Array[Int]): Unit = {

  }

  // 变长参数
  // 参数本质是一个集合
  def function5(s: String*): Unit = {
    for (i <- s) {
      println(i)
    }
  }

  // 匿名函数 最大特点是没有函数名字
  (ss: Int, dd: String) => {}

  def function6(s: String, f: String => Int) {
    println(f(s))
  }

  def hight(a: Int, b: Int, f: (Int, Int) => Int) = {
    f(a, b)
  }

  def stringn(a: String, fun: (String) => String) = {
    fun(a)
  }

  def main(args: Array[String]): Unit = {
    function6("sss", (s) => s.toString.length)
    println(hight(1, 8, (a: Int, b: Int) => {
      a + b
    }))
    stringn("sss", (a) => {
      a
    })
    println("----------------------------------------")
    var arr1 = Array(1, 2, 3, 4)
    arr1.foreach(x => {
      println(x)
    })

  }
}
