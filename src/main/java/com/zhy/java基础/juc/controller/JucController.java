package com.zhy.java基础.juc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p> </p>
 *
 * @author zhouhongyin
 * @since 2023/12/29 16:49
 */
@RestController
@RequestMapping("juc")
public class JucController {


    @Autowired
    private JucService jucService;

    @GetMapping("sync")
    public String sync() throws InterruptedException {

        jucService.sync();

        return "success";
    }

}
