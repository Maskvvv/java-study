package com.zhy.spring.annotation.enable.importaware;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * <p> ImportAware test </p>
 *
 * @author zhouhongyin
 * @since 2024/1/10 16:34
 */
@EnableProxy
public class ImportAwareDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ImportAwareDemo.class);
        context.register(ProxyConfiguration1.class);

        context.refresh();

        //User bean = context.getBean("user2", User.class);
        //System.out.println(bean);
        context.close();
    }
}
