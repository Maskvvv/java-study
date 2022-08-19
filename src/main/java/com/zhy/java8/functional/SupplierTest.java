package com.zhy.java8.functional;

import com.zhy.model.git.entity.People;
import org.junit.Test;

import java.util.function.Supplier;

/**
 * @author zhouhongyin
 * @since 2022/7/4 23:17
 */
public class SupplierTest {

    @Test
    public void supplierTest1() {
        Supplier<People> supplier = People::new;
        System.out.println(supplier.get());
    }
}
