package com.study.classD

/**
  * Created by rod on 2019/7/7.
  */

/**
  * 通过case 来声明一个类为样例类
  * 必须提供一个主构造器
  * 样例类默认混入序列化特质
  * 样例类默认会创建空构造器
  * 样例类不需要new 就可以创建一个新对象
  * @param v1
  * @param v2
  */
case class CaseClass(v1:String, v2:String)  {

  var id = v1
  var title = v2

  def main(args: Array[String]): Unit = {
    var i1 = CaseClass("ss","")
    var i2 = CaseClass // 空构造器
  }
}
