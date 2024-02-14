package com.example.dao;

import com.example.entity.Params;
import com.example.entity.Record;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


@Repository
public interface RecordDao extends Mapper<Record> {

    List<Record> findBySearch(Params params);
}
