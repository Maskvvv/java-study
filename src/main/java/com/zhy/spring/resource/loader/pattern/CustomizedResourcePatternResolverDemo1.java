package com.zhy.spring.resource.loader.pattern;


import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.PathMatcher;

import java.io.IOException;
import java.util.stream.Stream;


/**
 * 自定义 {@link ResourcePatternResolver} 示例
 * <p>
 * 相当于对{@link FileSystemResourceLoader} 的增强
 *
 * @see ResourcePatternResolver
 * @see PathMatchingResourcePatternResolver
 * @see PathMatcher
 */
public class CustomizedResourcePatternResolverDemo1 {

    public static void main(String[] args) throws Exception {
        // 读取当前 package 对应的所有的 .java 文件
        String currentPackagePath = "D:/资料/blog/springboot/**/**.**";
        PathMatchingResourcePatternResolver resourcePatternResolver =
                new PathMatchingResourcePatternResolver(new FileSystemResourceLoader());

        Resource[] resources = resourcePatternResolver.getResources(currentPackagePath);

        Stream.of(resources).forEach(resource -> {

            try {
                System.out.println(resource.getURI().getPath());
                System.out.println(resource.getURL().getPath());
                System.out.println(resource.getURL().getFile());
                System.out.println(resource.getFile().getPath());

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println(resource.getFilename());
        });
    }

}
