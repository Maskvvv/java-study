package com.zhy.spring.scan.model;

import com.zhy.spring.scan.MyRegistryComponent;
import lombok.Data;

/**
 * @author zhouhongyin
 * @since 2023/5/19 15:48
 */
@MyRegistryComponent
@Data
public class NeedRegistry1 implements NeedRegistry{

    private String name;

    private String age;
}
