package com.zhy.spring.aop.spel;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSON;
import com.zhy.spring.aop.AopAnnotation;
import com.zhy.spring.aop.AopBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhouhongyin
 * @since 2022/8/19 16:29
 */
@Slf4j
@RestController
@RequestMapping("spellog")
public class SpELLogController {

    @MethodLog(content = "queryParam: #{ #queryParam }, pathParam: #{ #pathParam }, aopBody: #{ #json( #aopBody ) }")
    @PostMapping("test1/{pathParam}")
    public Map<String, String> test1(String queryParam, @PathVariable String pathParam, @RequestBody AopBody aopBody) {

        Map<String, String> response = new HashMap<>();
        response.put("result1", queryParam);
        response.put("result2", pathParam);
        response.put("result3", JSON.toJSONString(aopBody));

        return response;

    }

}
