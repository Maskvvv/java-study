package com.zhy.spring.注解.condition;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

/**
 * {@link Profile} 示例
 *
 * @see Profile
 * @see Environment#getActiveProfiles()
 */
@Configuration
public class ProfileDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class
        context.register(ProfileDemo.class);

        // 获取 Environment 对象（可配置的）
        ConfigurableEnvironment environment = context.getEnvironment();
        // 默认 profiles = [ "odd" ] （兜底 profiles)
        environment.setDefaultProfiles("odd");
        // 增加活跃 profiles
//        environment.addActiveProfile("even");

        // --spring.profiles.active = even
        // -Dspring.profiles.active=even

        // 启动 Spring 应用上下文
        context.refresh();

        Integer number = context.getBean("number", Integer.class);

        System.out.println(number);

        // 关闭 Spring 应用上下文
        context.close();
    }

    @Bean(name = "number")
    @Profile("odd") // 奇数
    public Integer odd() {
        return 1;
    }

    @Bean(name = "number")
//    @Profile("even") // 偶数
    @Conditional(EvenProfileCondition.class)
    public Integer even() {
        return 2;
    }

}
