package com.zhy.spring.scan;

import com.zhy.spring.scan.model.NeedRegistry;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class MyRegistry {

    private static final List<NeedRegistry> ROOTS = new CopyOnWriteArrayList<>();

    static final String CLASSPATH_ALL_URL_PREFIX = "classpath*:";
    static final String DEFAULT_RESOURCE_PATTERN = "/**/*.class";
    static final String PACKAGE_SEPARATOR = ".";
    static final String PATH_SEPARATOR = "/";

    public static void initialize(String... basePackages) {
        initialize(false, basePackages);
    }

    public static void initialize(boolean force, String... basePackages) {
        MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory();
        if (ArrayUtils.isEmpty(basePackages)) {
            basePackages = new String[] { "" };
        }
        try {
            for (String basePackage : basePackages) {
                String searchPath = resolveSearchPath(basePackage);
                Resource[] resources = new PathMatchingResourcePatternResolver().getResources(searchPath);
                for (Resource resource : resources) {
                    if (resource == null || !resource.isReadable()) {
                        continue;
                    }
                    MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(resource);
                    String classname = metadataReader.getClassMetadata().getClassName();

                    try {
                        Class<?> cls = Class.forName(classname);

                        System.out.println(classname);

                        MyRegistryComponent annotation = cls.getAnnotation(MyRegistryComponent.class);
                        if (annotation == null) {
                            continue;
                        }

                        NeedRegistry needRegistry = (NeedRegistry) cls.getConstructor().newInstance();
                        ROOTS.add(needRegistry);


                    } catch (Exception | Error ex) {
                        System.out.println(ex);
                    }

                }
            }
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

    private static String resolveSearchPath(String basePackage) {
        return CLASSPATH_ALL_URL_PREFIX + StringUtils.replace(basePackage, PACKAGE_SEPARATOR, PATH_SEPARATOR) + DEFAULT_RESOURCE_PATTERN;
    }

    private String[] basePackages;

    public MyRegistry() {
    }

    public MyRegistry(String... basePackages) {
        this.basePackages = basePackages;
    }

    public void init(String... basePackages) {
        MyRegistry.initialize(basePackages);
    }

    public void init() {
        MyRegistry.initialize(this.basePackages);
    }

}
