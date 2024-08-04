package com.zhy.javabase.注解.注解测试1;

public class Student {
//    @Ranged(min = 2, max = 10)
//    private String name;
//    @Ranged(min = 1, max = 100)
//    private int age;
//
//    public Student(String name, int age) {
//        this.name = name;
//        this.age = age;
//    }
//
//    /**
//     * Field getField(name)：根据字段名获取某个public的field（包括父类）     * Field[] getFields()：获取所有public的field（包括父类）     *     * Field getDeclaredField(name)：根据字段名获取当前类的某个field（不包括父类）     * Field[] getDeclaredFields()：获取当前类的所有field（不包括父类）     *     * @param student     * @throws Exception
//     */
//    public void checkRanged(Student student) throws Exception {
//        for (Field field : student.getClass().getDeclaredFields()) {
//            Ranged ranged = field.getAnnotation(Ranged.class);
//            if (ranged != null) {
//                Object value = field.get(this);
//                if (value instanceof String) {
//                    String s = (String) value;
//                    if (s.length() < ranged.min() || s.length() > ranged.max()) {
//                        throw new IllegalArgumentException("Invalid field: " + field.getName());
//                    }
//                }
//                if (value instanceof Integer) {
//                    Integer i = (Integer) value;
//                    if (i < ranged.min() || i > ranged.max()) {
//                        throw new IllegalArgumentException("Invalid field: " + field.getName());
//                    }
//                }
//            }
//        }
//    }
}
