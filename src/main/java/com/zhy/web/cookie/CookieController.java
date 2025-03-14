package com.zhy.web.cookie;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author zhouhongyin
 * @since 2022/11/3 16:40
 */
@Slf4j
@RestController
@RequestMapping("cookie")
public class CookieController {

    @GetMapping("set_cookie")
    public String setCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        log.info(JSON.toJSONString(cookies));

        Cookie cookie = new Cookie("cookieId", IdUtil.simpleUUID());
        cookie.setMaxAge(10);
        response.addCookie(cookie);

        return "set_cookie";
    }

    @GetMapping("set_session")
    public String setSession(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.setAttribute("sessionId1", IdUtil.simpleUUID());
        session.setAttribute("sessionId2", IdUtil.simpleUUID());

        return "set_session";
    }

    @GetMapping("get_session")
    public String getSession(HttpServletRequest request, HttpServletResponse response) {
        Object sessionId = request.getSession().getAttribute("sessionId");
        log.info((String) sessionId);

        return "get_session";
    }



}
