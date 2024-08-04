package com.zhy.other.test.junit;

import com.zhy.model.git.entity.People;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.anyList;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
//@SpringBootTest(classes = {JavaStudyApplication.class})
public class UnitTest1 {

    @BeforeClass
    public static void beforeClass() {
        System.out.println("=================BeforeClass================");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("=================AfterClass================");
    }

    @Before
    public void beforeTest() {
        System.out.println("before test");
    }

    @After
    public void afterTest() {
        System.out.println("after test");
    }

    @Test
    public void test1() {
        System.out.println("test1");
    }

    @Test
    public void test2() {

        People mock = mock(People.class);
        when(mock.test(anyList(), anyString())).thenReturn("aaa");
        System.out.println(mock.toString());
        System.out.println("test2");
    }
}

