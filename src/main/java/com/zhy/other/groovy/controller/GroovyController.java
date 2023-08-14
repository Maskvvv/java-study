package com.zhy.other.groovy.controller;

import com.zhy.other.groovy.loader.GroovyLoader;
import com.zhy.other.groovy.loader.ILoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhouhongyin
 * @since 2023/5/20 12:37
 */
@RequestMapping("groovy")
@RestController
public class GroovyController {
    @Resource
    private GroovyLoader groovyLoader;

    @GetMapping
    public String test() {

        String scriptBase64 = "cGFja2FnZSBjb20uemh5LnNwcmluZy5ncm9vdnkubG9hZGVyOwoKLyoqCiAqIOiOt+WPluWKoOi9veexu+WQjeensAogKgogKiBAYXV0aG9yIHpob3Vob25neWluCiAqIEBzaW5jZSAyMDIzLzUvMjAgMTE6NTQKICovCnB1YmxpYyBjbGFzcyBNeUxvYWRlciBpbXBsZW1lbnRzIElMb2FkZXIgewoKICAgIHB1YmxpYyBTdHJpbmcgZ2V0TG9hZGVyTmFtZSgpIHsKICAgICAgICByZXR1cm4gIuaIkeeahCBsb2FkZXIiOwogICAgfQp9Cg==";
        Object myClassLoader = groovyLoader.getBean("myClassLoader", scriptBase64);
        ILoader loader = (ILoader) myClassLoader;

        return loader.getLoaderName();
    }


}
