package com.zhy.常用java类.http;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p> java http </p>
 *
 * @author zhouhongyin
 * @since 2023/8/4 11:33
 */
@Slf4j
@RestController
@RequestMapping("inet")
public class InternetController {


    @GetMapping("ip")
    public String getClientIP(HttpServletRequest request) {
        String ipAddress = InternetUtils.getIpAddress(request);
        return ipAddress;
    }

    @GetMapping("remote")
    public String getRemote(HttpServletRequest request) {

        log.info("RemoteHost: {}", request.getRemoteHost());
        log.info("RemoteAddr: {}", request.getRemoteAddr());
        log.info("RemotePort: {}", request.getRemotePort());
        log.info("RemoteUser: {}", request.getRemoteUser());
        System.out.println();

        log.info("Protocol: {}", request.getProtocol());
        log.info("Scheme: {}", request.getScheme());
        System.out.println();

        log.info("ServerName: {}", request.getServerName());
        log.info("ServerPort: {}", request.getServerPort());
        System.out.println();

        log.info("LocalAddr: {}", request.getLocalAddr());
        log.info("LocalPort: {}", request.getLocalPort());
        log.info("LocalName: {}", request.getLocalName());
        System.out.println();

        log.info("RequestURI: {}", request.getRequestURI());
        log.info("RequestURL: {}", request.getRequestURL());


        String ipAddress = InternetUtils.getIpAddress(request);
        return ipAddress;

    }

}
