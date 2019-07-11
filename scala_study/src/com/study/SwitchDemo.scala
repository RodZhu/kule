package com.study

/**
  * Created by rod on 2019/6/11.
  */
import util.control.Breaks._

object SwitchDemo {

  def main(args: Array[String]): Unit = {

    // match 具有返回值
      var v1 = "ssseee"
      var r1= v1 match {
        case "hello" =>{
          print("hello")
        }
        case "ddd" => {
          print("ddd")
        }
        case _ => {}
          println("null")
          "dddfc"
      }
     print(r1)
  }


  for(i<- 1 to 10) {

    if(i == 8) {
      break
    }
    println(i)
  }
}
