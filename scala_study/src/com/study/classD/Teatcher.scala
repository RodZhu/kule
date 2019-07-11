package com.study.classD

/**
  * Created by rod on 2019/7/7.
  */

// 定义抽象类 抽象方法
abstract class Teatcher extends InterfaceDemo{

  // 定义抽象方法
  def teach(): Unit

  def makeNote(): String

  def say(word: String): Int

  def cook() = {

  }
}
