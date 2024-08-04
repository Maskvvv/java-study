package com.zhy.model.git.entity;

import lombok.Data;

import java.util.List;

/**
 *
 * @author zhouhongyin
 * @since 2021/9/5 16:51
 */
@Data
public class People {

    private String name;

    private int age;

    /**
     * pop1
     */
    private String pop1;

    /**
     * pop2
     */
    private String pop2;

    /**
     * zhy1
     */
    private String zhy;

    /**
     * pop3
     */
    private String pop3;



    private String revert;


    public String test(List<String> list, String name) {
        return list.get(0);
    }
}
