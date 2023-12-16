package com.zhy.spring.aop.transaction;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhouhongyin
 * @since 2022/8/19 16:29
 */
@Slf4j
@RestController
@RequestMapping("transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("test1")
    public Map<String, String> test1(String queryParam) throws SQLException {
        Map<String, String> response = new HashMap<>();
        response.put("result1", queryParam);

        transactionService.test1();
        return response;

    }


}
