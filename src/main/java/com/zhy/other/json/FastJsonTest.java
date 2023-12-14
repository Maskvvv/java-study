package com.zhy.other.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zhy.other.json.pcg.PackageDirectory;
import com.zhy.other.json.pcg.PackageEvent;
import com.zhy.other.json.pcg.PackageFile;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhouhongyin
 * @since 2023/12/14 22:17
 */
public class FastJsonTest {

    @Test
    public void test() {

        Obj obj = new Obj();
        obj.setObjName("obj Name 周");
        List<Parent> list = new ArrayList<>();
        obj.setList(list);

        People1 people1 = new People1();
        people1.setName("p1 name");
        people1.setP1("p1");
        list.add(people1);

        People2 people2 = new People2();
        people2.setName("p2 name");
        people2.setP2("p2");
        list.add(people2);


        SerializerFeature[] features = new SerializerFeature[]{
                SerializerFeature.WriteClassName,
                //SerializerFeature.SkipTransientField,
                //SerializerFeature.DisableCircularReferenceDetect
        };
        String jsonString = JSON.toJSONString(obj, features);
        System.out.println(jsonString);

        Obj obj1 = JSON.parseObject(jsonString, Obj.class);
        System.out.println(JSON.toJSONString(obj1));


    }


    @Test
    public void test1() {
        PackageEvent pk1 = g();


        SerializerFeature[] features = new SerializerFeature[]{SerializerFeature.WriteClassName};
        String jsonString = JSON.toJSONString(pk1, features);
        System.out.println(jsonString);

        PackageEvent pk2 = JSON.parseObject(jsonString, PackageEvent.class);
        System.out.println(JSON.toJSONString(pk2));


    }

    public static PackageEvent g() {

        PackageEvent packageEvent = PackageEvent.Builder.builder()
                .name("test task")
                .packageName("test.zip")
                .resourceType("resource type")
                .createBy("test")
                .build();

        packageEvent.addResource(PackageDirectory.Builder.builder().name("目录1").build()
                .addResource(PackageFile.Builder.builder()
                        .name("问答系统1demo.doc")
                        .url("https://ourea-develop.obs.cn-north-1.myhuaweicloud.com:443/competitionDeliverable/8334d0b757dc4c92844b57aef4ba9f8c.doc")
                        .resourceType("resource type").build())
                .addResource(PackageFile.Builder.builder()
                        .name("蓝屏速查表.xlsx")
                        .url("https://ourea-develop.obs.cn-north-1.myhuaweicloud.com:443/competitionDeliverable/4cd1d28498d24371a1c5ae0ba81be73b.xlsx")
                        .resourceType("resource type").build())
                .addResource(PackageFile.Builder.builder()
                        .name("蓝屏速查表.xlsx")
                        .url("https://ourea-develop.obs.cn-north-1.myhuaweicloud.com:443/competitionDeliverable/4cd1d28498d24371a1c5ae0ba81be73b.xlsx")
                        .resourceType("resource type").build()));


        PackageDirectory directory1 = PackageDirectory.Builder.builder().name("目录2").build();
        directory1.addResource(PackageFile.Builder.builder()
                        .name("600多个人工智能AI工具汇总（AIGC时代-超级个体的崛起.xlsx")
                        .url("https://ourea-develop.obs.cn-north-1.myhuaweicloud.com:443/competitionDeliverable/d770a038934f4dea8c494625df97a0ac.xlsx")
                        .resourceType("resource type").build())

                .addResource(PackageFile.Builder.builder()
                        .name("lanpingle.jpg")
                        .url("https://ourea-develop.obs.cn-north-1.myhuaweicloud.com:443/competitionDeliverable/ce57ddaea81d4894b9a42f316a551da9.jpg")
                        .resourceType("resource type").build());
        packageEvent.addResource(directory1);


        packageEvent.addResource(PackageFile.Builder.builder()
                .name("软盟logo.png")
                .url("https://ourea-develop.obs.cn-north-1.myhuaweicloud.com:443/competitionDeliverable/868a4acea3044e7db73171448b13223b.png")
                .resourceType("resource type").build());

        return packageEvent;
    }

}
