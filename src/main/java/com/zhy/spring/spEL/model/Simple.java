package com.zhy.spring.spEL.model;

import java.util.ArrayList;
import java.util.List;

public class Simple {
    public List<Boolean> booleanList = new ArrayList<Boolean>();

    public boolean isMember(String name) {

        return name.equals("Mihajlo Pupin");
    }

    public static boolean isName(String name) {

        return name.equals("Mihajlo Pupin");
    }


}
