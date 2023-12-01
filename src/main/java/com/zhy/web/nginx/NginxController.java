package com.zhy.web.nginx;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Header Test
 *
 * @author zhouhongyin
 * @since 2022/7/1 16:50
 */
@RestController
@RequestMapping("nginx")
public class NginxController {


    @GetMapping("timeout")
    public String contentDispositionPdf(HttpServletRequest request, HttpServletResponse response) throws IOException, InterruptedException {

        Thread.sleep(10 * 1000);

        System.out.println("timeout request");
        return "timeout test";
    }

    @GetMapping("time")
    public long timeout(int time) throws InterruptedException {
        Thread.sleep(time * 1000L);
        return System.currentTimeMillis();
    }

}
