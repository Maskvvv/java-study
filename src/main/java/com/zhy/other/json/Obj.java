package com.zhy.other.json;

import java.util.List;

/**
 * @author zhouhongyin
 * @since 2023/12/14 22:19
 */
public class Obj {

    private String objName;

    private List<Parent> list;

    public String getObjName() {
        return objName;
    }

    public void setObjName(String objName) {
        this.objName = objName;
    }

    public List<Parent> getList() {
        return list;
    }

    public void setList(List<Parent> list) {
        this.list = list;
    }
}
