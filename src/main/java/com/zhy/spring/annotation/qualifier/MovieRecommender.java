package com.zhy.spring.annotation.qualifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
