package com.zhy.javabase.jvm.bytecodeprecess.dynnamic;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;

import static java.lang.invoke.MethodHandles.lookup;

/**
 * JSR 292 MethodHandle 基础用法演示
 *
 * <pre>
 * 1.Reflection中的java.lang.reflect.Method对象远比MethodHandle机制中的
 * java.lang.invoke.MethodHandle对象所包含的信息来得多。前者是方法在Java端的全面映像，包含了方法
 * 的签名、描述符以及方法属性表中各种属性的Java端表示方式，还包含执行权限等的运行期信息。而
 * 后者仅包含执行该方法的相关信息。用开发人员通俗的话来讲，Reflection是重量级，而MethodHandle
 * 是轻量级。
 *
 * 2.由于MethodHandle是对字节码的方法指令调用的模拟，那理论上虚拟机在这方面做的各种优化
 * （如方法内联），在MethodHandle上也应当可以采用类似思路去支持（但目前实现还在继续完善
 * 中），而通过反射去调用方法则几乎不可能直接去实施各类调用点优化措施。
 *
 * 3.MethodHandle与Reflection除了上面列举的区别外，最关键的一点还在于去掉前面讨论施加的前
 * 提“仅站在Java语言的角度看”之后：Reflection API的设计目标是只为Java语言服务的，而MethodHandle
 * 则设计为可服务于所有Java虚拟机之上的语言，其中也包括了Java语言而已，而且Java在这里并不是主
 * 角。
 *
 *
 *
 * </pre>
 *
 * @author zzm
 */
public class MethodHandleTest {
    static class ClassA {
        public void println(String s) {
            System.out.println(s);
        }
    }

    public static void main(String[] args) throws Throwable {
        Object obj = System.currentTimeMillis() % 2 == 0 ? System.out : new ClassA();
        // 无论obj最终是哪个实现类，下面这句都能正确调用到println方法。
        getPrintlnMH(obj).invokeExact("icyfenix");
    }

    private static MethodHandle getPrintlnMH(Object reveiver) throws Throwable {
        // MethodType：代表“方法类型”，包含了方法的返回值（methodType()的第一个参数）和 具体参数（methodType() 第二个及以后的参数）。
        MethodType mt = MethodType.methodType(void.class, String.class);

        // lookup()方法来自于MethodHandles.lookup，
        // 这句的作用是在指定类中查找符合给定的方法名称、方法类型，并且符合调用权限的方法句柄。
        // 因为这里调用的是一个虚方法，按照Java语言的规则，方法第一个参数是隐式的，代表该方法的接
        // 收者，也即this指向的对象，这个参数以前是放在参数列表中进行传递，现在提供了bindTo() 方法来完成这件事情。
        return lookup().findVirtual(reveiver.getClass(), "println", mt).bindTo(reveiver);
    }
}
