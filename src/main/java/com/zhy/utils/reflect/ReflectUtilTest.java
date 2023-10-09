package com.zhy.utils.reflect;

import com.zhy.model.git.entity.People;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * <p> ReflectUtilTest </p>
 *
 * @author zhouhongyin
 * @since 2023/10/9 10:45
 */
public class ReflectUtilTest {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        People people = new People();


        try {
            Field name = ReflectionUtils.findField(People.class, "name");
            name.setAccessible(true);
            ReflectionUtils.setField(name, people, "1111");
            System.out.println(ReflectionUtils.getField(name, people));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
