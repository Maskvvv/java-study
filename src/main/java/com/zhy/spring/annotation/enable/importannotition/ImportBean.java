package com.zhy.spring.annotation.enable.importannotition;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanNameAware;

/**
 * @author zhouhongyin
 * @since 2023/2/20 17:18
 */
@Slf4j
@Data
public class ImportBean implements BeanNameAware {

    private String name;

    private Integer age;

    @Override
    public void setBeanName(String name) {

        //com.zhy.spring.注解.importannotition.ImportBean
        log.info("ImportBean-setBeanName:{}", name);
    }
}
