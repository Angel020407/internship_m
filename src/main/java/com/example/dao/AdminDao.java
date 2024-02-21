package com.example.dao;

import com.example.entity.Admin;
import com.example.entity.Params;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface AdminDao extends Mapper<Admin> {

    List<Admin> findBySearch(@Param("params") Params params);

    @Select("select * from admin where name = #{name} limit 1")
    Admin findByName(@Param("name") String name);

    @Select("select * from admin where number = #{number} limit 1")
    Admin findByNumber(@Param("number") String number);

    @Select("select * from admin where number = #{number} and password = #{password} limit 1")
    Admin findByNumberAndPassword(@Param("number") String number, @Param("password") String password);
}
