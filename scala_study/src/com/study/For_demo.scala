package com.study

/**
  * Created by rod on 2019/4/19.
  */
object For_demo {

  def main(args: Array[String]): Unit = {

    var array = List(1, 2, 3, 4)

    for (i <- array) {
      println(i)
    }

    for (i <- (1 to 10 by 2)) println(i)

    println("=============")
    for (i <- (1 to 10 by 2); if i > 3) println(i)


    for (i <- 1 to 9) {
      for (j <- 1 to i) {
         print(j +  "*" + i + "=" + (i*j) + " ")
      }
      println("")
    }

    // 利用s函数拼接输出
    for (i <- 1 to 9) {
      for (j <- 1 to i) {
        print(s"$j*$i=${i*j} ")
      }
      println("")
    }
    println("======")
    // 利用双层输出
    for(i <-1 to 9;j <-1 to i;sep=if(i==j)"\r\n" else "\t") {
        print(s"$i*$j=${i*j}$sep")
    }

    // 遍历数组
    println("遍历数组=====》")
    var arr1=Array(1,2,3)
    for(a <- arr1){
      println(a)
    }
    //遍历map
  }
}
