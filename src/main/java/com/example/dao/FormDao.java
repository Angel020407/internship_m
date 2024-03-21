package com.example.dao;

import com.example.entity.Params;
import com.example.entity.Form;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


@Repository
public interface FormDao extends Mapper<Form> {

    List<Form> findBySearch(@Param("params") Params params);
}
