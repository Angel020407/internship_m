package com.example.dao;

import com.example.entity.Admin;
import com.example.entity.Params;
import com.example.entity.Person;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


@Repository
public interface PersonDao extends Mapper<Person> {

    List<Person> findBySearch(@Param("params") Params params);

    @Select("select * from admin where number = #{number} limit 1")
    Admin findByNumber(@Param("number") String number);
}