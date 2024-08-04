package com.zhy.javabase.继承;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author zhouhongyin
 * @since 2021/8/25 11:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageOneListRecipeParam extends NcdHealthControllerBaseParam{
    private String test;

    private String doLx;

}
