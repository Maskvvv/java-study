package com.zhy.javabase.collection.compare;

import com.zhy.java8.stream.People;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author zhouhongyin
 * @since 2023/12/19 23:00
 */
public class SortTest {

    public static void main(String[] args) {
        List<People> list = new ArrayList<>();

        list.add(new People("", 1, 1));
        list.add(new People("", 2, 1));
        list.add(new People("", 3, 1));
        list.add(new People("", 4, 1));
        list.add(new People("", 5, 2));
        list.add(new People("", 6, 2));
        list.add(new People("", 7, 4));
        list.add(new People("", 8, 4));


        for (int i = 0; i < 10; i++) {
            Random random = new Random();
            list.sort((p1, p2) -> {
                if (p1.getTheClass().equals(p2.getTheClass())) {
                    int r1 = random.nextInt();
                    int r2 = random.nextInt();
                    return Integer.compare(r1, r2);
                } else {
                    return Integer.compare(p1.getTheClass(), p2.getTheClass());
                }
            });


            list.forEach(p -> {
                System.out.println(p.getAge() + " : " + p.getTheClass());
            });


            System.out.println("---------------------------");
        }

    }


}
