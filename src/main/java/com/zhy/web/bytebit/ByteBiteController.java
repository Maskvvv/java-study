package com.zhy.web.bytebit;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p> </p>
 * json类型 返回值大小的计算方式：
 * - 汉字: 3B
 * - ASCII: 1B
 *
 * @author zhouhongyin
 * @since 2023/7/19 15:02
 */
@RequestMapping("byte_bite")
@RestController
public class ByteBiteController {

    @GetMapping("string")
    public String string(String s) {
        return s;
    }

    @GetMapping("integer")
    public Integer integer(Integer s) {
        return s;
    }

    @PostMapping("model")
    public ByteBitModel model(@RequestBody ByteBitModel s) {
        return s;
    }


}
