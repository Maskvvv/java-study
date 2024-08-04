package com.zhy.javabase.juc.future;

/**
 * 提交Runnable任务
 *      Future submit(Runnable task);
 *      提交 Runnable 任务 submit(Runnable task) ：
 *      这个方法的参数是一个 Runnable 接口，Runnable 接口的 run() 方法是没有返回值的，
 *      所以 submit(Runnable task) 这个方法返回的 Future 仅可以用来断言任务已经结束了，类似于 Thread.join()。
 *
 * 提交Callable任务
 *      Future submit(Callable task);
 *      提交 Callable 任务 submit(Callable task)：
 *      这个方法的参数是一个 Callable 接口，它只有一个 call() 方法，
 *      并且这个方法是有返回值的，所以这个方法返回的 Future 对象可以通过调用其 get() 方法来获取任务的执行结果。
 *
 * 提交Runnable任务及结果引用
 *      Future submit(Runnable task, T result);
 *      提交 Runnable 任务及结果引用 submit(Runnable task, T result)：
 *      这个方法很有意思，假设这个方法返回的 Future 对象是 f，
 *      f.get() 的返回值就是传给 submit() 方法的参数 result。
 *      这个方法该怎么用呢？下面这段示例代码展示了它的经典用法。
 *      需要你注意的是 Runnable 接口的实现类 Task 声明了一个有参构造函数 Task(Result r) ，
 *      创建 Task 对象的时候传入了 result 对象，这样就能在类 Task 的 run() 方法中对 result 进行各种操作了。r
 *      esult 相当于主线程和子线程之间的桥梁，通过它主子线程可以共享数据。
 *
 * @author zhouhongyin
 * @since 2023/2/16 23:02
 */
public class ThreadPoolExecutorSubmit {


}
