package com.zhy.spring.aop;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class LogAdvice {

    /**
     * 切点
     */
    @Pointcut("execution(* com.zhy.spring.aop..*.*Controller.*(..))")
    public void matchExecution() {
    }

    /**
     * 匹配 类里所有方法
     */
    @Pointcut("within(com.zhy.spring.aop.controller.AopController)")
    public void matchType() {
    }

    /**
     * 匹配 包及子包下所有类方法
     */
    @Pointcut("within(com.zhy.spring.aop.controller..*)")
    public void matchPackage() {
    }

    /**
     *
     */
    @Pointcut("@annotation(com.zhy.spring.aop.AopAnnotation)")
    public void matchAnnotation() {
    }


    /**
     * 前置操作
     */
    @Before("matchAnnotation()")
    public void doBefore(JoinPoint joinPoint) throws ServletException, IOException, ClassNotFoundException, NoSuchMethodException {
        HttpServletRequest request = getRequest();
        log.info("-----------------doBefore-begin-----------------");

        log.info("PathInfo:{}", request.getPathInfo());
        log.info("HeaderNames:{}", JSON.toJSONString(request.getHeaderNames()));

        // the request doesn't contain a multipart/form-data or multipart/mixed stream, content type header is application/json
        //log.info("Parts:{}", JSON.toJSONString(request.getParts()));

        log.info("Method:{}", request.getMethod());
        log.info("QueryString:{}", request.getQueryString());
        log.info("RequestURL:{}", request.getRequestURL());
        log.info("RequestURI:{}", request.getRequestURI());
        log.info("RemoteUser:{}", request.getRemoteUser());
        log.info("UserPrincipal:{}", JSON.toJSONString(request.getUserPrincipal()));
        log.info("AttributeNames:{}", JSON.toJSONString(request.getAttributeNames()));
        log.info("CharacterEncoding:{}", request.getCharacterEncoding());
        log.info("ContentType:{}", request.getContentType());
        log.info("ContentLength():{}", request.getContentLength());
        log.info("ParameterMap:{}", JSON.toJSONString(request.getParameterMap()));
        log.info("ParameterNames:{}", JSON.toJSONString(request.getParameterNames()));
        log.info("LocalAddr:{}", request.getLocalAddr());
        log.info("Locale:{}", JSON.toJSONString(request.getLocale()));
        log.info("LocalName:{}", request.getLocalName());
        log.info("LocalPort:{}", request.getLocalPort());
        log.info("Protocol:{}", request.getProtocol());

        //ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        //ServletInputStream inputStream = request.getInputStream();
        //IoUtil.copy(inputStream, byteArrayOutputStream);
        //log.info("InputStream:{}", inputStream);

        //log.info("Reader:{}", request.getReader().readLine());

        log.info("RemoteAddr:{}", request.getRemoteAddr());
        log.info("RemoteHost:{}", request.getRemoteHost());
        log.info("RemotePort:{}", request.getRemotePort());
        log.info("Scheme:{}", request.getScheme());
        log.info("ServerName:{}", request.getServerName());
        log.info("ServerPort:{}", request.getServerPort());

        // 目标方法名
        Signature signature = joinPoint.getSignature();
        String declaringTypeName = signature.getDeclaringTypeName();
        String name = signature.getName();



        Class<?> targetClass = Class.forName(declaringTypeName);
        Method[] declaredMethods = targetClass.getDeclaredMethods();
        //Method method = targetClass.getDeclaredMethod(name);
        //Parameter[] parameters = method.getParameters();


        Object[] args = joinPoint.getArgs();
        Object target = joinPoint.getTarget();
        Object aThis = joinPoint.getThis();
        JoinPoint.StaticPart staticPart = joinPoint.getStaticPart();

        log.info("目标方法:{}, 对应的类名:{}", signature.getName(), signature.getDeclaringType().getSimpleName());


        log.info("-----------------doBefore-end-----------------\n");

    }

    /**
     * 后置操作
     */
    @After("matchAnnotation()")
    public void doAfter(JoinPoint joinPoint) throws IOException {
        log.info("-----------------doAfter-begin-----------------");

        HttpServletResponse response = getResponse();

        log.info("HeaderNames:{}", response.getHeaderNames());
        log.info("Locale:{}", JSON.toJSONString(response.getLocale()));
        log.info("CharacterEncoding:{}", response.getCharacterEncoding());
        log.info("Status:{}", response.getStatus());

        OutputStream outputStream = response.getOutputStream();
        log.info("OutputStream:{}", outputStream);

        log.info("-----------------doAfter-end-----------------\n");
    }

    /**
     * 返回通知
     */
    @AfterReturning(returning = "result", pointcut = "matchAnnotation()")
    public void doAfterReturning(Object result) {
        log.info("-------------doAfterReturning-begin------------------");

        log.info("doAfterReturning:{}", result);

        log.info("-------------doAfterReturning-begin------------------\n");
    }

    /**
     * 环绕通知
     */
    @Around("matchAnnotation()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("-------------doAround-begin------------------");

        Object result = joinPoint.proceed();
        log.info("doAround-result:{}", JSON.toJSONString(result));

        Signature signature = joinPoint.getSignature();

        Object[] args = joinPoint.getArgs();

        Object[] filterArgs = new Object[args.length]; // 创建一个新的集合，初始化长度
        // 只要是ServletRequest、ServletResponse、MultipartFile都不会添加到filterArgs中
        for(int i = 0; i < args.length; i++) {
            if(args[i] instanceof ServletRequest
                    || args[i] instanceof ServletResponse
                    || args[i] instanceof MultipartFile) {
                continue;
            }
            filterArgs[i] = args[i];
        }
        log.info("doAround-param: {}", JSON.toJSONString(filterArgs));


        //log.info("doAround-body: {}", JSON.toJSONString(result));



        log.info("-------------doAround-end------------------\n");

        return result;
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
