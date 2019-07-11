package com.study

/**
  * Created by rod on 2019/7/6.
  */
object Homework {

  def forFunc(): Unit = {
    var arr = List(10 to 1)
    for(e <- 10 to 0 by -1) {
      print(e)
    }
  }

  def main(args: Array[String]): Unit = {
    forFunc();
  }

}
