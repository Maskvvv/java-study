package com.zhy.web.param;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
