package com.zhy.spring.annotation.enable.importannotition;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author zhouhongyin
 * @since 2023/2/20 17:19
 */
@Import({ImportBean.class, ImportImportBeanDefinitionRegistrar.class})
@Configuration
public class ImportConfiguration {
}
