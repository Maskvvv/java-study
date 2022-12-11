package com.zhy.web.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhouhongyin
 * @since 2022/11/25 17:18
 */
@Controller("page")
public class PageController {

    @GetMapping("page")
    public String json() {
        return "page";
    }

    @ResponseBody
    @GetMapping("json")
    public String page() {
        return "json";
    }

}
