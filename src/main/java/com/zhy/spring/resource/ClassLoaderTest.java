package com.zhy.spring.resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * @author zhouhongyin
 * @since 2023/5/6 14:07
 */
public class ClassLoaderTest {

    public static void main(String[] args) throws IOException {
        InputStream inputStream = ClassLoaderTest.class.getClassLoader().getResourceAsStream("classpath:cp_id.txt");

        InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

        BufferedReader bufferedReader = new BufferedReader(reader);

        String line = bufferedReader.readLine();
        System.out.println(line);

    }

}
