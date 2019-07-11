package com.study


import util.control.Breaks._
/**
  * Created by rod on 2019/6/12.
  */
object BreakDemo {

  def main(args: Array[String]): Unit = {

    for(i <- 1 to 10) {

      breakable(if (i ==8) {
        break
      }else {
        println(i)
      })
    }
  }

}
