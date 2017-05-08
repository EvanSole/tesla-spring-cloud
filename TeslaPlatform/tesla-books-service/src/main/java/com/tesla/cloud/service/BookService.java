package com.tesla.cloud.service;

import com.tesla.cloud.domain.BookEntity;

import java.util.List;

/**
 * Created by Edwin.wang on 17/5/5.
 */
public interface BookService {

    List<BookEntity> getAllBooks();

}
