package com.tesla.cloud;


import com.tesla.cloud.domain.BookEntity;
import com.tesla.cloud.service.BookService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(value = SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BooksApplication.class)
public class BookServiceTest {

    @Autowired
    BookService bookService;

    @Test
    public void testFindBooks() throws Exception {
        List<BookEntity> books = bookService.getAllBooks();
        //PageHelper.startPage(1,10);
        Assert.assertEquals("1", books.size());
    }

}
