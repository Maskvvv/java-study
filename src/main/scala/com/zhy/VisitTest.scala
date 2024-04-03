package com.zhy

/**
 * scala 访问修饰符
 *
 * @author zhouhongyin
 * @since 2024/4/2 21:40
 */
class VisitTest {

  class Super {
    protected def f() {
      println("f")
    }

    class Sub {
      f()
    }

  }

  class Other {
    //    (new Super).f() //错误
  }

}
