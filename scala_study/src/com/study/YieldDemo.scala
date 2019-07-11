package com.study

/**
  * Created by rod on 2019/6/11.
  */
object YieldDemo {

  def main(args: Array[String]): Unit = {
    var arr1 =Array(1,2,3)
    var list1 =List(1,2,3)

    var arr2=for(i<- arr1)yield{i*2}
    for(i<- arr2) {
      println(i)
    }

    // yield 遍历的集合每个元素返回新的集合
    // 返回新的集合类型和遍历的集合类型一致
    //  scala集合:Array,List,Range,Map,Set,Tuple
    var list2=for(i<- list1)yield{i*2}
    for(i<- list2) {
      println(i)
    }
  }
}
