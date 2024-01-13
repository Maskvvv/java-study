package com.zhy.spring.aop.controller;

import com.alibaba.fastjson.JSON;
import com.zhy.spring.aop.lock.AthenaLock;
import com.zhy.spring.aop.model.LockBody;
import com.zhy.spring.aop.model.LockParam;
import com.zhy.spring.aop.transaction.MyTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author zhouhongyin
 * @since 2023/12/16 17:10
 */
@Service
public class TransactionService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final Random random = new Random(1000);

    @Autowired
    private PlatformTransactionManager transactionManager;
    @Autowired
    private TransactionTemplate transactionTemplate;

    @MyTransaction
    @Transactional
    public String test1() throws SQLException {
        String sql = "SELECT * FROM `user`;";

        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);

        System.out.println(maps);
        return "1111";

    }

    @AthenaLock(spEl = "#{ #TestLockKey.key(#param.id + #lockBody.name + #lockBody.age) }")
    @Transactional
    public String lock(LockParam param, LockBody lockBody) throws SQLException {
        String sql = "SELECT * FROM `user`;";

        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        System.out.println(maps);

        String id = String.valueOf(random.nextInt());
        String insert = String.format("INSERT INTO `test`.`user` (`id`, `name`, `age`) VALUES (%s, '%s', %s);", id, id, id);
        jdbcTemplate.execute(insert);

        return JSON.toJSONString(maps);

    }

    @AthenaLock(keyConvert = TestKeyConvert.class)
    @Transactional
    public String lockV2(LockParam param, LockBody lockBody) throws SQLException {
        String sql = "SELECT * FROM `user`;";

        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        System.out.println(maps);

        String id = String.valueOf(random.nextInt());
        String insert = String.format("INSERT INTO `test`.`user` (`id`, `name`, `age`) VALUES (%s, '%s', %s);", id, id, id);
        jdbcTemplate.execute(insert);

        return JSON.toJSONString(maps);

    }

    public void test() {
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            // ....  业务代码
            transactionManager.commit(status);
        } catch (Exception e) {
            transactionManager.rollback(status);
        }
    }

    public void test3() {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                try {

                    // ....  业务代码
                } catch (Exception e) {
                    //回滚
                    transactionStatus.setRollbackOnly();
                }

            }
        });
    }

    public static void main(String[] args) {
        String id = String.valueOf(random.nextInt());

        String insert = String.format("INSERT INTO `test`.`user` (`id`, `name`, `age`) VALUES (%s, '%s', %s);", id, id, id);

        System.out.println(insert);

    }
}
