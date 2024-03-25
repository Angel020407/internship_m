package com.example.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.JwtTokenUtils;
import com.example.dao.FormDao;
import com.example.entity.Admin;
import com.example.entity.Form;
import com.example.entity.Params;
import com.example.exception.CustomException;
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
        Admin user = JwtTokenUtils.getCurrentUser();
        if (ObjectUtil.isNull(user)) {
            throw new CustomException("从token中未解析到用户信息，请重新登录");
        }
        if ("ROLE_STUDENT".equals(user.getRole())) {
            params.setUserId(user.getId());
        }
        if("ROLE_TEACHER2".equals(user.getRole())) {
            params.setUserSchool(user.getSchool());
        }
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
