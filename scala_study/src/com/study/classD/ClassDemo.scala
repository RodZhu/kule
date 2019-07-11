package com.study.classD

/**
  * Created by rod on 2019/7/7.
  */

//
class ClassDemo(v1:String,v2:Int) {

  var name = v1
  var age = v2
  var name2 =""

  private var gw2 = ""


  // 辅助构造器, 可以定义多个，要求必须显示的调用同类的其他构造器。
  def this(ar1:String) {
    this(ar1, 0)
  }

  def eat(food:String): Unit = {

  }
}
