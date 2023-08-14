package com.zhy.other.groovy.loader;

/**
 * 获取加载类名称
 *
 * @author zhouhongyin
 * @since 2023/5/20 11:54
 */
public class MyLoader implements ILoader {

    public String getLoaderName() {
        return "我的 loader";
    }
}
