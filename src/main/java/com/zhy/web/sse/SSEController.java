package com.zhy.web.sse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Random;

/**
 * <p> </p>
 *
 * @author zhouhongyin
 * @since 2023/7/11 13:56
 */
@RequestMapping("sse")
@Controller
public class SSEController {

    @GetMapping(value = "test", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public void push(HttpServletResponse response) {
        response.setContentType("text/event-stream");
        response.setCharacterEncoding("utf-8");

        while (true ) {
            Random r = new Random();
            try {
                Thread.sleep(1000);
                PrintWriter pw = response.getWriter();

                if (pw.checkError()) {
                    System.out.println("客户端断开连接");
                    return;
                }

                String s = "data:Testing 1,2,3" + r.nextInt() + "\n\n";
                System.out.println(s);
                pw.write(s);
                pw.flush();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @GetMapping(value = "chat", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public void chat(HttpServletResponse response) {
        response.setContentType("text/event-stream");
        response.setCharacterEncoding("utf-8");

        FileReader fileReader = new FileReader("D:\\UserFiles\\桌面\\新建文本文档 (2).txt");
        fileReader.read()

        while (true ) {
            Random r = new Random();
            try {
                Thread.sleep(1000);
                PrintWriter pw = response.getWriter();


                if (pw.checkError()) {
                    System.out.println("客户端断开连接");
                    return;
                }

                String s = "data:Testing 1,2,3" + r.nextInt() + "\n\n";
                System.out.println(s);
                pw.write(s);
                pw.flush();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
