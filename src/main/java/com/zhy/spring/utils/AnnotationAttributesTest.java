package com.zhy.spring.utils;

import com.zhy.spring.scan.EnableMyRegistry;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

/**
 * <p> AnnotationConfigUtils test </p>
 *
 * @author zhouhongyin
 * @since 2024/1/10 14:30
 */
public class AnnotationAttributesTest {

    public static void main(String[] args) {

    }

    public void test1(AnnotationMetadata importingClassMetadata) {
        //AnnotationAttributes enableAspectJAutoProxy =
        //        AnnotationConfigUtils.attributesFor(importingClassMetadata, EnableAspectJAutoProxy.class);


        AnnotationAttributes annotationAttributes =
                AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(EnableMyRegistry.class.getName()));

    }
}
