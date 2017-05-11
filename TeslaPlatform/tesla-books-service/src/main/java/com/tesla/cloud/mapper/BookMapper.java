package com.tesla.cloud.mapper;


import com.tesla.cloud.core.config.mybatis.TeslaMapper;
import com.tesla.cloud.domain.BookEntity;

import java.util.List;

public interface BookMapper extends TeslaMapper<BookEntity> {

    List<BookEntity> queryByBookName (String bookBame);

}
