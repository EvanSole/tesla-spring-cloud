package com.tesla.cloud;


import com.github.pagehelper.PageHelper;
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
    public void testGetBooks() throws Exception {
        BookEntity bookEntity = new BookEntity();
        //bookEntity.setId(2000L);
        bookEntity.setPublisher("电子工业出版社");
        List<BookEntity> books = bookService.getBooks(bookEntity);
        Assert.assertEquals(1, books.size());
    }


    @Test
    public void testGetFindBooksByName() throws Exception {
        PageHelper.startPage(1,10);
        List<BookEntity> books = bookService.getBooksByName("《大型网站技术架构》");
        Assert.assertEquals(1, books.size());
    }


    @Test
    public void testGetAllBooks() throws Exception {
        List<BookEntity> books = bookService.getAllBooks();
        PageHelper.startPage(1,1);
        Assert.assertEquals(1, books.size());
    }


    @Test
    public void testAddBooks() throws Exception {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setBookName("大型网站系统与Java中间件实践");
        bookEntity.setAuthor("曾宪杰");
        bookEntity.setPublisher("天津工业出版社");
        bookEntity.setDescription("摩尔定律");
        bookEntity.setId(2000L);
        bookService.addBooks(bookEntity);
    }


}
