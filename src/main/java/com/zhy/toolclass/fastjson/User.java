package com.zhy.toolclass.fastjson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *  
 * @author zhouhongyin
 * @since 2021/7/23 13:58
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable{
    private String name;
    private Integer age;
}
