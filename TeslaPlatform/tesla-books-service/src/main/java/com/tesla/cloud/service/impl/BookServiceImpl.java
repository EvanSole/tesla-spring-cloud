package com.tesla.cloud.service.impl;

import com.tesla.cloud.domain.BookEntity;
import com.tesla.cloud.mapper.BookMapper;
import com.tesla.cloud.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class BookServiceImpl implements BookService {

    @Autowired
    BookMapper bookMapper;

    @Override
    public List<BookEntity> getAllBooks() {
        return bookMapper.selectAll();
    }
}
