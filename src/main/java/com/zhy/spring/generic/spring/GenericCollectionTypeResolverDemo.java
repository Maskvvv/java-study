package com.zhy.spring.generic.spring;

///**
// * {@link GenericCollectionTypeResolver} 示例
// *
// * @see GenericCollectionTypeResolver
// */
//public class GenericCollectionTypeResolverDemo {
//
//    private static StringList stringList;
//
//    private static List<String> strings;
//
//    public static void main(String[] args) throws Exception {
//
//        // StringList extends ArrayList<String> 具体化
//        // getCollectionType 返回具体化泛型参数类型集合的成员类型 = String
//        System.out.println(GenericCollectionTypeResolver.getCollectionType(StringList.class));
//
//        System.out.println(GenericCollectionTypeResolver.getCollectionType(ArrayList.class));
//
//        // 获取字段
//        Field field = GenericCollectionTypeResolverDemo.class.getDeclaredField("stringList");
//        System.out.println(GenericCollectionTypeResolver.getCollectionFieldType(field));
//
//        field = GenericCollectionTypeResolverDemo.class.getDeclaredField("strings");
//        System.out.println(GenericCollectionTypeResolver.getCollectionFieldType(field));
//    }
//}
