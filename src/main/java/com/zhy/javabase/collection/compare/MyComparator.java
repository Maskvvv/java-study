package com.zhy.javabase.collection.compare;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author zhouhongyin
 * @since 2023/5/11 21:34
 */
public class MyComparator implements Comparator<PeopleCompareModel> {

    @Override
    public int compare(PeopleCompareModel o1, PeopleCompareModel o2) {
        return o1.getAge() - o2.getAge();
    }

    public static void main(String[] args) {
        List<PeopleCompareModel> list = new ArrayList<>();

        list.add(new PeopleCompareModel("2", 2));
        list.add(new PeopleCompareModel("1", 1));

        list.sort(new MyComparator());

        System.out.println(list);

    }
}
