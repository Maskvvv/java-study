package com.zhy.other.arthas;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhouhongyin
 * @since 2023/2/17 11:59
 */
@RestController
@RequestMapping("arthas_stack")
public class ArthasStackController {

    @GetMapping("test")
    public void test() {
        method1();
    }

    public String method1() {
        method2();

        return "method1";
    }

    public String method2() {
        method3();
        return "method2";
    }

    public String method3() {

        return "method3";
    }

}
