package threadtest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;

/**
 * @description:
 * @author: zhouhongyin
 * @time: 2021/8/3 16:44
 */

@SpringBootTest
public class ThreadPoolTaskExecutorTest {

    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Test
    public void ThreadTest(){
        threadPoolTaskExecutor.execute(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(i);
            }
        });
    }

}
