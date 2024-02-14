package com.example.service;

import com.example.dao.RecordDao;
import com.example.entity.Record;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RecordService {

    @Resource
    private RecordDao recordDao;

    public void add(Record record) {
        recordDao.insertSelective(record);
    }

    public void update(Record record) {
        recordDao.updateByPrimaryKey(record);
    }


}
