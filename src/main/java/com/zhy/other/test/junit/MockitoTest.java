package com.zhy.other.test.junit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

/**
 * @author zhouhongyin
 * @since 2024/8/4 11:58
 */
@RunWith(MockitoJUnitRunner.class)
public class MockitoTest {

    /**
     * 行为验证
     * • 一旦mock对象被创建了，mock对象会记住所有的交互，然后你就可以选择性的验证你感兴趣的交互，验证不通过则抛出异常。
     */
    @Test
    public void test1() {
        final List mockList = Mockito.mock(List.class);
        mockList.add("mock1");
        mockList.get(0);
        mockList.size();
        mockList.clear();
        // 验证方法被使用（默认1次）
        Mockito.verify(mockList).add("mock1");
        // 验证方法被使用1次
        Mockito.verify(mockList, Mockito.times(1)).get(0);
        // 验证方法至少被使用1次
        Mockito.verify(mockList, Mockito.atLeast(1)).size();
        // 验证方法没有被使用
        Mockito.verify(mockList, Mockito.never()).contains("mock2");
        // 验证方法至多被使用5次
        Mockito.verify(mockList, Mockito.atMost(5)).clear();
        // 指定方法调用超时时间
        Mockito.verify(mockList, timeout(100)).get(0);
        // 指定时间内需要完成的次数
        Mockito.verify(mockList, timeout(200).atLeastOnce()).size();
    }

    /**
     * 如何做一些测试桩stub
     * • 默认情况下，所有的函数都有返回值。mock函数默认返回的是null，一个空的集合或者一个被对象类型包装的内置类型，例如0、false对应的对象类型为Integer、Boolean；
     * <p>
     * • 一旦测试桩函数被调用，该函数将会一致返回固定的值；
     * <p>
     * • 对于 static 和 final 方法， Mockito 无法对其 when(…).thenReturn(…) 操作。
     */
    @Test
    public void test2() {
        //静态导入，减少代码量：import static org.mockito.Mockito.*;
        final ArrayList mockList = mock(ArrayList.class);

        // 设置方法调用返回值
        when(mockList.add("test2")).thenReturn(true);
        doReturn(true).when(mockList).add("test2");
        System.out.println(mockList.add("test2"));  //true

        // 设置方法调用抛出异常
        when(mockList.get(0)).thenThrow(new RuntimeException());
        doThrow(new RuntimeException()).when(mockList).get(0);
        System.out.println(mockList.get(0));    //throw RuntimeException

        // 无返回方法打桩
        doNothing().when(mockList).clear();

        // 为回调做测试桩（对方法返回进行拦截处理）
        final Answer<String> answer = new Answer<String>() {
            @Override
            public String answer(InvocationOnMock invocationOnMock) throws Throwable {
                final List mock = (List) invocationOnMock.getMock();
                return "mock.size result => " + mock.size();
            }
        };
        when(mockList.get(1)).thenAnswer(answer);
        doAnswer(answer).when(mockList).get(1);
        System.out.println(mockList.get(1));    //mock.size result => 0

        // 对同一方法多次打桩，以最后一次为准
        when(mockList.get(2)).thenReturn("test2_1");
        when(mockList.get(2)).thenReturn("test2_2");
        System.out.println(mockList.get(2));    //test2_2
        System.out.println(mockList.get(2));    //test2_2

        // 设置多次调用同类型结果
        when(mockList.get(3)).thenReturn("test2_1", "test2_2");
        when(mockList.get(3)).thenReturn("test2_1").thenReturn("test2_2");
        System.out.println(mockList.get(3));    //test2_1
        System.out.println(mockList.get(3));    //test2_2

        // 为连续调用做测试桩（为同一个函数调用的不同的返回值或异常做测试桩）
        when(mockList.get(4)).thenReturn("test2").thenThrow(new RuntimeException());
        doReturn("test2").doThrow(new RuntimeException()).when(mockList).get(4);
        System.out.println(mockList.get(4));    //test2
        System.out.println(mockList.get(4));    //throw RuntimeException

        // 无打桩方法，返回默认值
        System.out.println(mockList.get(99));    //null
    }

    /**
     * 参数匹配器
     * • 参数匹配器使验证和测试桩变得更灵活；
     * <p>
     * • 为了合理的使用复杂的参数匹配，使用equals()与anyX() 的匹配器会使得测试代码更简洁、简单。有时，会迫使你重构代码以使用equals()匹配或者实现equals()函数来帮助你进行测试；
     * <p>
     * • 如果你使用参数匹配器,所有参数都必须由匹配器提供；
     * <p>
     * • 支持自定义参数匹配器；
     */
    @Test
    public void test3() {
        final Map mockMap = mock(Map.class);

        // 正常打桩测试
        when(mockMap.get("key")).thenReturn("value1");
        System.out.println(mockMap.get("key"));     //value1

        // 为灵活起见，可使用参数匹配器
        when(mockMap.get(anyString())).thenReturn("value2");
        System.out.println(mockMap.get(anyString()));   //value2
        System.out.println(mockMap.get("test_key"));    //value2
        System.out.println(mockMap.get(0)); //null

        // 多个入参时，要么都使用参数匹配器，要么都不使用，否则会异常
        when(mockMap.put(anyString(), anyInt())).thenReturn("value3");
        System.out.println(mockMap.put("key3", 3));     //value3
        System.out.println(mockMap.put(anyString(), anyInt()));     //value3
        System.out.println(mockMap.put("key3", anyInt()));    //异常

        // 行为验证时，也支持使用参数匹配器
        verify(mockMap, atLeastOnce()).get(anyString());
        verify(mockMap).put(anyString(), eq(3));

        // 自定义参数匹配器
        //final ArgumentMatcher<People> myArgumentMatcher = new ArgumentMatcher<People>() {
        //    @Override
        //    public boolean matches(People request) {
        //        return "name".equals(request.getName()) || "zhy".equals(request.getZhy());
        //    }
        //};
        //// 自定义参数匹配器使用
        //final People mock = mock(People.class);
        //when(mock.equals(argThat(myArgumentMatcher))).thenReturn("success");
        //doReturn("success").when(mock).argumentTestMethod(argThat(myArgumentMatcher));
        //System.out.println(mock.argumentTestMethod(new ArgumentTestRequest("name", "value")));  // success
        //System.out.println(mock.argumentTestMethod(new ArgumentTestRequest()));     //null
    }

    /**
     * 执行顺序验证
     * • 验证执行顺序是非常灵活的-你不需要一个一个的验证所有交互,只需要验证你感兴趣的对象即可；
     * <p>
     * • 你可以仅通过那些需要验证顺序的mock对象来创建InOrder对象；
     */
    @Test
    public void test4() {
        // 验证同一个对象多个方法的执行顺序
        final List mockList = mock(List.class);
        mockList.add("first");
        mockList.add("second");
        final InOrder inOrder = inOrder(mockList);
        inOrder.verify(mockList).add("first");
        inOrder.verify(mockList).add("second");

        // 验证多个对象多个方法的执行顺序
        final List mockList1 = mock(List.class);
        final List mockList2 = mock(List.class);
        mockList1.get(0);
        mockList1.get(1);
        mockList2.get(0);
        mockList1.get(2);
        mockList2.get(1);
        final InOrder inOrder1 = inOrder(mockList1, mockList2);
        inOrder1.verify(mockList1).get(0);
        inOrder1.verify(mockList1).get(2);
        inOrder1.verify(mockList2).get(1);
    }

    /**
     * 确保交互（interaction）操作不会执行在mock对象上
     * • 一些用户可能会在频繁地使用verifyNoMoreInteractions()，甚至在每个测试函数中都用。但是verifyNoMoreInteractions()并不建议在每个测试函数中都使用；
     * <p>
     * • verifyNoMoreInteractions()在交互测试套件中只是一个便利的验证，它的作用是当你需要验证是否存在冗余调用时；
     */
    @Test
    public void test5() {
        // 验证某个交互是否从未被执行
        final List mock = mock(List.class);
        mock.add("first");
        verify(mock, never()).add("test5");   //通过
        verify(mock, never()).add("first");  //异常

        // 验证mock对象没有交互过
        final List mock1 = mock(List.class);
        final List mock2 = mock(List.class);
        verifyZeroInteractions(mock1);  //通过
        verifyNoMoreInteractions(mock1, mock2); //通过
        verifyZeroInteractions(mock, mock2);  //异常

        // 注意：可能只想验证前面的逻辑，但是加上最后一行，会导致出现异常。建议使用方法层面的验证，如：never()；
        //      在验证是否有冗余调用的时候，可使用此种方式。如下：
        final List mockList = mock(List.class);
        mockList.add("one");
        mockList.add("two");
        verify(mockList).add("one");    // 通过
        verify(mockList, never()).get(0);    //通过
        verifyZeroInteractions(mockList);   //异常
    }


    // 代替 mock(ArgumentTestService.class) 创建mock对象；
    @Mock
    private ArgumentTestService argumentTestService;
    // 若改注解修饰的对象有成员变量，@Mock定义的mock对象会被自动注入；
    @InjectMocks
    private MockitoAnnotationServiceImpl mockitoAnnotationService;

    @Test
    public void test6() {
        // 注意！下面这句代码需要在运行测试函数之前被调用,一般放到测试类的基类或者test runner中;
        //MockitoAnnotations.initMocks(this);
        when(argumentTestService.argumentTestMethod(new ArgumentTestRequest())).thenReturn("success");
        System.out.println(argumentTestService.argumentTestMethod(new ArgumentTestRequest()));  //success
        System.out.println(mockitoAnnotationService.mockitoAnnotationTestMethod()); //null
    }


}
