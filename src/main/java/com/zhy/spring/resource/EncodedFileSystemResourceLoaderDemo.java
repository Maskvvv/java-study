package com.zhy.spring.resource;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.io.Reader;

/**
 * 带有字符编码的 {@link FileSystemResourceLoader} 示例
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see FileSystemResourceLoader
 * @see FileSystemResource
 * @see EncodedResource
 * @since
 */
public class EncodedFileSystemResourceLoaderDemo {

    public static void main(String[] args) throws IOException {
        String currentJavaFilePath = "/" + System.getProperty("user.dir") + "/thinking-in-spring/resource/src/main/java/org/geekbang/thinking/in/spring/resource/EncodedFileSystemResourceLoaderDemo.java";
        // 新建一个 FileSystemResourceLoader 对象
        FileSystemResourceLoader resourceLoader = new FileSystemResourceLoader();
        // FileSystemResource => WritableResource => Resource
        Resource resource = resourceLoader.getResource(currentJavaFilePath);
        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
        // 字符输入流
        try (Reader reader = encodedResource.getReader()) {
            System.out.println(IOUtils.toString(reader));
        }
    }
}
