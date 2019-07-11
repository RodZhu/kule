package com.spark.app.sort

/**
  * Created by rod on 2019/7/11.
  */
class SecondSort(var v1: String, var v2: Int) extends Ordered[SecondSort] with Serializable {

  override def compare(that: SecondSort): Int = {
    var result = this.v1.compareTo(that.v1)
    if (result == 0) {
      that.v2.compareTo(this.v2)
    } else {
      result
    }
  }

  override def toString: String = {
    return this.v1 + ": " + this.v2
  }
}
