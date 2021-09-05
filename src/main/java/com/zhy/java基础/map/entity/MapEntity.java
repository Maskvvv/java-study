package com.zhy.java基础.map.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

/**
 * @description:
 * @author: zhouhongyin
 * @time: 2021/8/26 16:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MapEntity extends HashMap<String, Object> {

    private String code;

}
