package com.zhy.spring.注解.qualifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Component
public class MovieRecommender {

    @Autowired
    @MovieQualifier("actionVhsCatalog")
    private MovieCatalog actionVhsCatalog;

    // ...

    public void customerQualifier() {

        System.out.println(actionVhsCatalog);
    }
}
