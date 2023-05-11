package com.zhy.spring.resource.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * @author zhouhongyin
 * @since 2023/5/6 14:24
 */
public class ResourceLoaderTest {
    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ResourceLoaderConfiguration.class);

        ResourceLoader resourceLoader = applicationContext.getBean("resourceLoader", ResourceLoader.class);
        Resource resource = resourceLoader.getResource("classpath:cp_id.txt");
        ResourceLoaderTest.print(resource.getInputStream());

        System.out.println("-------------------------------");

        ClassPathResource pathResource = new ClassPathResource("cp_id.txt");
        ResourceLoaderTest.print(pathResource.getInputStream());
    }


    public static void print(InputStream inputStream) throws IOException {

        InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

        BufferedReader bufferedReader = new BufferedReader(reader);

        String line = bufferedReader.readLine();
        while (line != null) {

            System.out.println(line);
            line = bufferedReader.readLine();
        }

    }

}
