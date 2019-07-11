package com.study.collection

import scala.collection.mutable.ListBuffer

/**
  * Created by rod on 2019/7/7.
  */
object ListDemo {

  val l1 = List(2,3,4,5)
  val l2:List[Int] = List(1,2,3,4)
  // 将1，2，3，4放进list中，这种声明方式必须以Nil结尾
  var l3 = 1::2::3::4::Nil

  // 空列表
  val val5 = List[Nothing]()


  // 声明一个变长list
  val l4= ListBuffer(1,2,3,4)
  l4.head
  l4.last
  l4.isEmpty

  // 拼接list
  List.concat(l1,l2)
  // ::: 同样可以表示拼接
  l1:::l2:::l3

  // 向list左则添加元素并返回新的list
  var l9 =  0 +:l1
  // 向list右则添加元素并返回新的list
  var l10 =  l1:+0

  // list取下标为2元素
  l9(2)
  // list取下标为2元素
  l9.apply(2)

   var arra1 = Array(1,2)
  l9.copyToArray(arra1, 0,2)
  l9.toArray

  //去重
  l9.distinct

  l9.exists(x => x==2)
  // 取交集
  l9.intersect(l4)

  l9.map(x => x * 2)

  //集合中的元素转成string并指定分隔符
  l9.mkString(",")

  // 排序
  l9.sortBy(x => x)

}
