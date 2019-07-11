package com.study

/**
  * Created by rod on 2019/6/11.
  */
object MapDemo {

  def main(args: Array[String]): Unit = {
    var map1 = Map("name"->"zhang","age"->1)
    for(i <- map1){
      println(i)
    }
    for((k,v)<- map1){
      print(s"key:$k,value:$v\n");
    }
  }
}
