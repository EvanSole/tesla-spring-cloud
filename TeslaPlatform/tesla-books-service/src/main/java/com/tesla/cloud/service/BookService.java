package com.tesla.cloud.service;

import com.tesla.cloud.domain.BookEntity;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * Created by Edwin.wang on 17/5/5.
 */
public interface BookService  {

    List<BookEntity> getBooks(BookEntity bookEntity);

    List<BookEntity> getAllBooks();

    Integer addBooks(BookEntity bookEntity);

    Integer updateBooks(BookEntity bookEntity);

    Integer removeBooks(Long  id);

    List<BookEntity> getBooksByName(String bookName);


}
