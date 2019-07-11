package com.study.classD

/**
  * Created by rod on 2019/7/7.
  */
class Student(v1:String,v2: Int) extends ClassDemo(v1:String,v2: Int) with InterfaceDemo{


  // 覆盖父类的方法 必须添加关键字
  override def eat(food:String) = {

  }

  override def dance(): Unit = {

  }
}
