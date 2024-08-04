package com.zhy.javabase.exception_;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author zhouhongyin
 * @since 2024/8/4 19:36
 */
@Slf4j
public class StackException {

    @Test
    public void tes7() {


        for (int i = 0; i < 200000; i++) {
            try {
                ((String) null).toString();
            } catch (Exception e) {
                int stackTraceLength = e.getStackTrace().length;
                System.out.println(i + "，堆栈长度：" + stackTraceLength);
                log.error("error", e);
            }
        }

    }

}
