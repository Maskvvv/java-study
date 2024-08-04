package com.zhy.javabase.jvm.bytecodeprecess.dynnamic;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;

/**
 * <p> InvokeDynamicTest </p>
 *
 * @author zhouhongyin
 * @since 2023/10/22 23:29
 */
public class InvokeDynamicTest2 {
    class GrandFather {
        void thinking() {
            System.out.println("i am grandfather");
        }
    }

    class Father extends GrandFather {
        void thinking() {
            System.out.println("i am father");
        }
    }

    class Son extends Father {
        void thinking() {
            try {
                MethodType mt = MethodType.methodType(void.class);
                Field lookupImpl = MethodHandles.Lookup.class.getDeclaredField("IMPL_LOOKUP");
                lookupImpl.setAccessible(true);
                MethodHandle mh = ((MethodHandles.Lookup) lookupImpl.get(null))
                        .findSpecial(GrandFather.class, "thinking", mt, GrandFather.class);
                mh.invoke(this);

            } catch (Throwable e) {
            }
        }
    }

    public static void main(String[] args) {
        (new InvokeDynamicTest2().new Son()).thinking();
    }
}
