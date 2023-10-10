package com.zhy.spring.annotation.ConditionalOnBean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author zhouhongyin
 * @since 2022/7/4 13:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class People {
    private String name;
    private String age;
}
