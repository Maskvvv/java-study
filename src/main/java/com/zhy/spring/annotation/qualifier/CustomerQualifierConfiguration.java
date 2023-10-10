package com.zhy.spring.annotation.qualifier;

import org.springframework.beans.factory.annotation.CustomAutowireConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zhouhongyin
 * @since 2023/1/9 22:23
 */
@Configuration
public class CustomerQualifierConfiguration {

    @Bean
    public CustomAutowireConfigurer customAutowireConfigurer() {
        CustomAutowireConfigurer customAutowireConfigurer = new CustomAutowireConfigurer();
        Set<Class<?>> customQualifierTypes = Stream.of(MovieQualifier.class).collect(Collectors.toSet());
        customAutowireConfigurer.setCustomQualifierTypes(customQualifierTypes);

        return customAutowireConfigurer;
    }

    @Bean
    public MovieCatalog actionVhsCatalog() {
        MovieCatalog actionVhsCatalog = new MovieCatalog();
        //actionVhsCatalog.setFormat(MovieQualifier.Format.VHS);
        return actionVhsCatalog;
    }

    @Bean
    @Qualifier("actionVhsCatalog2")
    public MovieCatalog actionVhsCatalog1() {
        MovieCatalog actionVhsCatalog = new MovieCatalog();
        actionVhsCatalog.setName("actionVhsCatalog2");
        actionVhsCatalog.setAge(1);
        return actionVhsCatalog;
    }

    //@Bean
    //@Qualifier("actionVhsCatalog3")
    //public MovieCatalog actionVhsCatalog2(@Value("#{actionVhsCatalog1.age}") Integer age) {
    //    MovieCatalog actionVhsCatalog = new MovieCatalog();
    //    actionVhsCatalog.setName("actionVhsCatalog2");
    //    actionVhsCatalog.setAge(age + 1);
    //    return actionVhsCatalog;
    //}

}
