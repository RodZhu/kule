package com.study.collection

/**
  * Created by rod on 2019/7/7.
  */
object SetDemo {

  // 定长
  val s1=Set(1,2,3,4,5)

  // 不定长
  val s2=scala.collection.mutable.Set(1,2,3)

  // 交集
  s1&s2

  // 差集
  s1&~s2
  s2&~s1

  // 合并
  s1++s2

  //差集
  s1.diff(s2)

}
