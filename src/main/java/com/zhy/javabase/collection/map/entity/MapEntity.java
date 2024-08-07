package com.zhy.javabase.collection.map.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

/**
 * 自定义 map
 *
 * @author zhouhongyin
 * @since 2021/8/26 16:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MapEntity extends HashMap<String, Object> {

    private String code;

}
