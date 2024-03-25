package com.example.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.JwtTokenUtils;
import com.example.dao.PersonDao;
import com.example.entity.Admin;
import com.example.entity.Params;
import com.example.entity.Person;
import com.example.exception.CustomException;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class PersonService {


    @Resource
    private PersonDao personDao;

    public PageInfo<Person> findBySearch(Params params ) {
        Admin user = JwtTokenUtils.getCurrentUser();
        if (ObjectUtil.isNull(user)) {
            throw new CustomException("从token中未解析到用户信息，请重新登录");
        }
        // 获取当前登录用户ID
        params.setUserId(user.getId());
        List<Person> list = personDao.findBySearch(params);
        return PageInfo.of(list);
    }

    public void update(Person person) {
        // 1. 学号不可为空
        if (person.getNumber() == null || "".equals(person.getNumber())) {
            throw new CustomException("学号不能为空");
        }
        // 2. 进行重复性判断，学号不可重复
        Admin user = personDao.findByNumber(person.getNumber());
        if (user != null) {
            throw new CustomException("该用户已存在!");
        }
        personDao.updateByPrimaryKeySelective(person);
    }

    public void delete(Integer id) {
        personDao.deleteByPrimaryKey(id);
    }
}
