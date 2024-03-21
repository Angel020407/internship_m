package com.example.service;

import com.example.dao.FormDao;
import com.example.entity.Form;
import com.example.entity.Params;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class FormService {

    @Resource
    private FormDao formDao;


    public PageInfo<Form> findBySearch(Params params) {
        // 开启分页查询
        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        // 接下来的查询会自动按照当前开启的分页设置来查询
        List<Form> list = formDao.findBySearch(params);
        return PageInfo.of(list);
    }


    public void add(Form form) {
        formDao.insertSelective(form);
    }

    public void update(Form form) {
        formDao.updateByPrimaryKeySelective(form);
    }

    public void delete(Integer id) {
        formDao.deleteByPrimaryKey(id);
    }
}
