package com.zhy.other.groovy;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

/**
 * Groovy 3.x 测试类
 * 用于验证 Groovy 3.0.15 依赖是否正常工作
 *
 * @author zhouhongyin
 * @since 2023/8/13
 */
public class GroovyTest {

    public static void main(String[] args) {
        try {
            // 测试 Groovy 3.x 基本功能
            GroovyClassLoader loader = new GroovyClassLoader();
            
            // 简单的 Groovy 脚本
            String script = "class TestGroovy { " +
                    "def sayHello(name) { " +
                    "return 'Hello ' + name + ' from Groovy 3.x!' " +
                    "} " +
                    "}";
            
            Class<?> groovyClass = loader.parseClass(script);
            GroovyObject groovyObject = (GroovyObject) groovyClass.newInstance();
            
            Object result = groovyObject.invokeMethod("sayHello", "World");
            System.out.println("Groovy 3.x 测试结果: " + result);
            
            // 输出 Groovy 版本信息
            System.out.println("Groovy 版本: " + groovy.lang.GroovySystem.getVersion());
            
        } catch (Exception e) {
            System.err.println("Groovy 3.x 测试失败: " + e.getMessage());
            e.printStackTrace();
        }
    }
}