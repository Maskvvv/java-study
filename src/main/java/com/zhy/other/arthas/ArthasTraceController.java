package com.zhy.other.arthas;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhouhongyin
 * @since 2023/2/17 11:59
 */
@RestController
@RequestMapping("arthas_trace")
public class ArthasTraceController {

    @GetMapping("test")
    public void test() {

        method1();

        method2();
    }

    public String method1() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "method1";
    }

    public String method2() {

        try {
            Thread.sleep(700);
            method3();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        method3();
        return "method2";
    }

    public String method3() {
        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "method3";
    }

}
