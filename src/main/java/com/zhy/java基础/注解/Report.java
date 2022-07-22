package com.zhy.java基础.注解;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 *  @Target: 可以定义Annotation能够被应用于源码的哪些位置
 *      类或接口：ElementType.TYPE
 *      字段：ElementType.FIELD
 *      方法：ElementType.METHOD
 *      构造方法：ElementType.CONSTRUCTOR
 *      方法参数：ElementType.PARAMETER
 *
 *  @Retention: 定义了Annotation的生命周期
 *      仅编译期：RetentionPolicy.SOURCE
 *      仅class文件：RetentionPolicy.CLASS
 *      运行期：RetentionPolicy.RUNTIME
 *
 *  @Repeatable: 这个元注解可以定义Annotation是否可重复。这个注解应用不是特别广泛
 *      @Repeatable(Reports.class)
 *
 *  @Inherited: 定义子类是否可继承父类定义的Annotation。@Inherited仅针对@Target(ElementType.TYPE)类型的annotation有效，
 *              并且仅针对class的继承，对interface的继承无效
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})

// 定义了Annotation的生命周期
@Retention(RetentionPolicy.RUNTIME)
public @interface Report {
    int type() default 0;

    String level() default "info";

    String value() default "";

}
