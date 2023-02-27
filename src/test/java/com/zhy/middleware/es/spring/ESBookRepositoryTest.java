package com.zhy.middleware.es.spring;

import com.zhy.JavaStudyApplication;
import com.zhy.middleware.es.model.Book;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author zhouhongyin
 * @since 2023/2/27 22:18
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JavaStudyApplication.class)
public class ESBookRepositoryTest extends TestCase {

    @Resource
    private ESBookRepository esBookRepository;

    @Test
    public void testFind() {

        SearchHits<Book> searchHits = esBookRepository.find("西游");

        for (SearchHit<Book> searchHit : searchHits) {
            System.out.println(searchHit.getContent());
        }
    }

    @Test
    public void save() {
        Book book = new Book();

        book.setId("1001");
        book.setAuthor("吴承恩");
        book.setCreateTime(new Date());
        book.setPrice(200D);
        book.setTitle("西游记");
        book.setUpdateTime(new Date());

        esBookRepository.save(book);

    }


}
