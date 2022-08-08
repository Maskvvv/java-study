package com.zhy.web.interceptor.annotation;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhouhongyin
 * @since 2022/8/8 14:18
 */
@RestController
@RequestMapping("annotation")
public class AnnotationController {

    @PermissionsAnnotation("get")
    @PostMapping("{id}/test1")
    public String annotationTest1(@PathVariable String id, @RequestBody AnnotationParam param) {
        param.setId(id);
        return JSON.toJSONString(param);
    }

}
