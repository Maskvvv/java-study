package com.zhy

/**
 * Scala 方法与函数
 *
 * @author zhouhongyin
 * @since 2024/4/3 17:51
 */
object MethodAndFunctionTest {

  /**
   * 方法: 方法是类的一部分
   */
  def addInt(a: Int, b: Int): Int = {
    var sum: Int = 0
    sum = a + b

    return sum
  }

  /**
   * 方法没有返回值
   */
  def printMe(): Unit = {
    println("Hello, Scala!")
  }

  /**
   * 函数: 函数是一个对象可以赋值给一个变量
   */
  val f = (x: Int) => x + 3

  def main(args: Array[String]): Unit = {
    println("Returned Value : " + addInt(5, 7))
  }

}
