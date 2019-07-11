package com.study

/**
  * Created by rod on 2019/6/12.
  */
object TryCatchDemo {

  def main(args: Array[String]): Unit = {
    try {
      throw new NullPointerException()
    } catch {
      case t: NullPointerException => {
        println("NullPointerException")
      }
      case t: Exception => {
        println("Exception")
      }
    } finally {
      println("finally ====>")
    }
  }
}
