package com.tesla.cloud.service.impl;

import com.tesla.cloud.core.config.database.DbShareField;
import com.tesla.cloud.core.config.database.TargetDataSource;
import com.tesla.cloud.domain.BookEntity;
import com.tesla.cloud.mapper.BookMapper;
import com.tesla.cloud.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookMapper bookMapper;

    //使用主数据源,默认主数据源
    @TargetDataSource(name = DbShareField.MASTER)
    @Override
    public List<BookEntity> getBooks(BookEntity bookEntity) {
        return bookMapper.selectByExample(bookEntity);
    }

    //使用从数据源
    @TargetDataSource(name = DbShareField.SLAVE)
    @Override
    public List<BookEntity> getAllBooks() {
        return bookMapper.selectAll();
    }


    @TargetDataSource(name = DbShareField.SLAVE)
    @Override
    public Integer addBooks(BookEntity bookEntity) {
        return bookMapper.insert(bookEntity);
    }

    @Override
    public Integer updateBooks(BookEntity bookEntity) {
        return bookMapper.updateByPrimaryKey(bookEntity);
    }

    @Override
    public Integer removeBooks(Long id) {
        return bookMapper.deleteByPrimaryKey(id);
    }

}
