package com.zhy.javabase.枚举类;

public class EunmTest {
    public static void main(String[] args) {
        System.out.println(Weekday.MON.name());
        System.out.println(Weekday.MON.ordinal());
        System.out.println(Weekday.MON);
        System.out.println(Weekday.MON.number);
    }
}
enum Weekday {
    MON(1), TUE(2), WED(3), THU(4), FRI(5), SAT(6), SUN(7);

    int number;

    Weekday(int number) {
        this.number = number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
