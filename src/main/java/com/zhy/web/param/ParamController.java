package com.zhy.web.param;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author zhouhongyin
 * @since 2022/11/3 16:40
 */
@Slf4j
@RestController
@RequestMapping("p")
public class ParamController {

    @PostMapping("body")
    public void setCookie(PParam p1, @RequestBody PBody p2) {

        System.out.println(p1);
        System.out.println(p2);
    }

    //@PostMapping("test1")
    @RequestMapping(value = "/test1", method = RequestMethod.POST)
    public String body(HttpServletRequest request) throws Exception {
        String body = generateBody(request);
        log.info(body);

        return body;

    }

    private String generateBody(HttpServletRequest httpServletRequest) throws IOException {
        StringBuilder data = new StringBuilder();
        String line;
        BufferedReader reader;
        reader = httpServletRequest.getReader();
        while (null != (line = reader.readLine())) {
            data.append(line);
        }
        return data.toString();
    }

}
