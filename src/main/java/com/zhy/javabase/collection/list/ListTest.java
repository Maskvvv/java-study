package com.zhy.javabase.collection.list;

import com.zhy.java8.stream.ListFactory;
import com.zhy.java8.stream.People;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

/**
 * <p> </p>
 *
 * @author zhouhongyin
 * @since 2023/12/11 11:40
 */
public class ListTest {

    @Test
    public void sort() {
        List<People> peopleList = ListFactory.getPeopleList();

        for (People people : peopleList) {
            System.out.println(people.getTheClass());
        }

        peopleList.sort(Comparator.comparingInt(People::getTheClass));

        System.out.println();

        for (People people : peopleList) {
            System.out.println(people.getTheClass());
        }

    }

}
