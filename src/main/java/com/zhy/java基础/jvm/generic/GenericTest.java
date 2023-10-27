package com.zhy.java基础.jvm.generic;

import java.lang.reflect.Array;
import java.util.List;

/**
 * <p> GenericTest </p>
 *
 * @author zhouhongyin
 * @since 2023/10/27 17:50
 */
public class GenericTest {

    /**
     * <p> 不得不加入的类型参数 </p>
     *
     * <p>
     * 运行期无法取到泛型类型信息，会让一些代码变得相当啰嗦，譬如代码清单10-2中罗列的
     * 几种Java不支持的泛型用法，都是由于运行期Java虚拟机无法取得泛型类型而导致的。像代码清单10-8
     * 这样，我们去写一个泛型版本的从List到数组的转换方法，由于不能从List中取得参数化类型T，所以
     * 不得不从一个额外参数中再传入一个数组的组件类型进去
     */
    public static <T> T[] convert(List<T> list, Class<T> componentType) {
        T[] array = (T[]) Array.newInstance(componentType, list.size());

        return array;
    }

}
