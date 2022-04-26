package org.com.dao;

import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface UserMapper {
    public Integer vilidataPhoneCount(String phone);
}
