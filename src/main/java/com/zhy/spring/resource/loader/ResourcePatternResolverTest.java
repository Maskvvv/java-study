package com.zhy.spring.resource.loader;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.util.stream.Stream;

/**
 * <p> </p>
 *
 * @author zhouhongyin
 * @since 2023/6/5 17:16
 */
public class ResourcePatternResolverTest {

    public static void main(String[] args) throws IOException {
        PathMatchingResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        //resourcePatternResolver.setPathMatcher(new PathMatcher() {
        //    @Override
        //    public boolean isPattern(String path) {
        //        return path.endsWith("meta.dat");
        //    }
        //
        //    @Override
        //    public boolean match(String pattern, String path) {
        //        return false;
        //    }
        //
        //    @Override
        //    public boolean matchStart(String pattern, String path) {
        //        return false;
        //    }
        //
        //    @Override
        //    public String extractPathWithinPattern(String pattern, String path) {
        //        return null;
        //    }
        //
        //    @Override
        //    public Map<String, String> extractUriTemplateVariables(String pattern, String path) {
        //        return null;
        //    }
        //
        //    @Override
        //    public Comparator<String> getPatternComparator(String path) {
        //        return null;
        //    }
        //
        //    @Override
        //    public String combine(String pattern1, String pattern2) {
        //        return null;
        //    }
        //});
        Resource[] resources = resourcePatternResolver.getResources("file:D:/UserFiles/桌面/轻软相关资料/result/meta/**/meta.dat");
        Stream.of(resources).map(ResourceUtils::getContent).forEach(System.out::println);

    }
}
