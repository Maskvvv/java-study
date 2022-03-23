package com.zhy.java8.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *  
 * @author zhouhongyin
 * @since 2021/8/10 15:27
 */

public class ListFactory {
    public static List<Integer> getIntegerList(){
        List<Integer> numbers = new ArrayList<>();
        numbers.add(3);
        numbers.add(3);
        numbers.add(4);
        numbers.add(8);
        numbers.add(16);
        numbers.add(19);
        numbers.add(27);
        numbers.add(23);
        numbers.add(99);
        numbers.add(15);
        numbers.add(32);
        numbers.add(5);
        numbers.add(232);
        numbers.add(56);
        return numbers;
    }

    public static List<People> getPeopleList(){
        List<People> peopleList = new ArrayList<>();
        peopleList.add(new People("maik",10, 1));
        peopleList.add(new People("jone1",56, 1));
        peopleList.add(new People("jone2",56,3));
        peopleList.add(new People("tom1",32,2));
        peopleList.add(new People("tom2",32,3));
        peopleList.add(new People("tom3",32,4));

        return peopleList;
    }
}

class People {
    private String name;
    private int age;
    private int theClass;

    public People(String name, int age, int theClass) {
        this.name = name;
        this.age = age;
        this.theClass = theClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getTheClass() {
        return theClass;
    }

    public void setTheClass(int theClass) {
        this.theClass = theClass;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", theClass=" + theClass +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof People)) return false;
        People people = (People) o;
        return age == people.age && theClass == people.theClass && Objects.equals(name, people.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, theClass);
    }
}
