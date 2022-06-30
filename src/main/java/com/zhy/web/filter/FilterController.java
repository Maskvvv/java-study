package com.zhy.web.filter;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author zhouhongyin
 * @since 2022/6/29 14:06
 */
@RestController
@RequestMapping("filter")
public class FilterController {

    @GetMapping("path1")
    public String path1(String name) {

        return name;
    }


    @PostMapping ("path2")
    public String path2(@RequestBody Map<String, Object> body) {
        return JSON.toJSONString(body);
    }


}
