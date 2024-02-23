package com.example.dao;

import com.example.entity.Params;
import com.example.entity.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


@Repository
public interface StudentDao extends Mapper<Student> {

    List<Student> findBySearch(@Param("params") Params params);
}