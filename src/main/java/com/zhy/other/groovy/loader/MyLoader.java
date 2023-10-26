package com.zhy.other.groovy.loader;

import cn.hutool.core.io.IoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;

/**
 * 获取加载类名称
 *
 * @author zhouhongyin
 * @since 2023/5/20 11:54
 */
//@Component
public class MyLoader implements ILoader {

    @Autowired
    private AutowiredBean autowiredBean;

    public String getLoaderName() throws Exception {
        autowiredBean.print();

        HttpServletResponse response = getResponse();
        ServletOutputStream outputStream = response.getOutputStream();

        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=a.docx");
        response.setHeader(HttpHeaders.CONTENT_TYPE, "application/vnd.openxmlformats-officedocument.wordprocessingml.document");

        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\lenovo\\Desktop\\sql.txt");
        IoUtil.copy(fileInputStream, outputStream);

        IoUtil.close(outputStream);
        IoUtil.close(fileInputStream);

        return "我的 loader";
    }

    /**
     * 获取 request
     */
    private HttpServletRequest getRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attributes.getRequest();
    }

    /**
     * 获取 response
     */
    private HttpServletResponse getResponse() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attributes.getResponse();
    }

}
