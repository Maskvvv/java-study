package com.zhy.java基础.generic;

public class Pair<T extends Number, U> {
    private T first;
    private U last;

    public Pair(T first, U last) {
        this.first = first;
        this.last = last;
    }

    public Pair(T first) {
        this.first = first;

    }

    public T getFirst() {
        return first;
    }

    public U getLast() {
        return last;
    }

    // 静态泛型方法应该使用其他类型区分:
    public static <K extends Number, L> Pair<K, L> create(K first) {
        return new Pair<>(first);
    }


    public static void main(String[] args) {

        Pair<Integer, Object> integerObjectPair = Pair.create(1);
    }
}
