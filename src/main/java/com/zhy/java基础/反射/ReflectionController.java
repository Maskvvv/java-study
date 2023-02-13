package com.zhy.java基础.反射;

import com.zhy.spring.ioc.extensionpoint.BeanPostProcessorBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhouhongyin
 * @since 2023/2/13 10:56
 */
@Slf4j
@RestController
@RequestMapping("reflection")
public class ReflectionController {

    @Autowired
    private BeanPostProcessorBean bean;

    @GetMapping
    public void postProcessAfterInitialization() {
        log.info("ReflectionController-postProcessAfterInitialization:{}", bean.toString());
    }

}
