package com.tesla.cloud.core.config.mybatis;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface TeslaMapper<T> extends Mapper<T>,MySqlMapper {

}
