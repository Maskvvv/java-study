package com.zhy.spring.注解.qualifier;

import com.zhy.spring.di.qualifier.MyQualifier;
import org.checkerframework.framework.qual.QualifierArgument;
import org.checkerframework.framework.qual.QualifierForLiterals;
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

}
