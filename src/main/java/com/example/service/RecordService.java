package com.example.service;

import com.example.dao.RecordDao;
import com.example.entity.Record;
import com.example.entity.Params;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class RecordService {

    @Resource
    private RecordDao recordDao;


    public PageInfo<Record> findBySearch(Params params) {
        // 开启分页查询
        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        // 接下来的查询会自动按照当前开启的分页设置来查询
        List<Record> list = recordDao.findBySearch(params);
        return PageInfo.of(list);
    }


    public void add(Record record) {
        recordDao.insertSelective(record);
    }

    public void update(Record record) {
        recordDao.updateByPrimaryKeySelective(record);
    }

    public void delete(Integer id) {
        recordDao.deleteByPrimaryKey(id);
    }
}
