package com.example.service;

import com.example.common.JwtTokenUtils;
import com.example.dao.AdminDao;
import com.example.entity.Admin;
import com.example.entity.Params;
import com.example.exception.CustomException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminService {

    private static final Logger log = LoggerFactory.getLogger(AdminService.class);

    @Resource
    private AdminDao adminDao;

    public List<Admin> findAll() {
        return adminDao.selectAll();
    }

    public PageInfo<Admin> findBySearch(Params params) {
        // 开启分页查询
        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        // 接下来的查询会自动按照当前开启的分页设置来查询
        List<Admin> list = adminDao.findBySearch(params);
        return PageInfo.of(list);
    }

    public void add(Admin admin) {
        // 1. 学号不可为空
        if (admin.getNumber() == null || "".equals(admin.getNumber())) {
            throw new CustomException("学号不能为空");
        }
        // 2. 进行重复性判断，学号不可重复
        Admin user = adminDao.findByNumber(admin.getNumber());
        if (user != null) {
            // 说明已经有了，这里我们就要提示前台不允许新增了
            throw new CustomException("该用户已存在!");
        }
        // 初始化一个密码
        if (admin.getPassword() == null) {
            admin.setPassword("123456");
        }
        adminDao.insertSelective(admin);
    }

    public void update(Admin admin) {
        // 1. 学号不可为空
        if (admin.getNumber() == null || "".equals(admin.getNumber())) {
            throw new CustomException("学号不能为空");
        }
        // 2. 进行重复性判断，学号不可重复
        Admin user = adminDao.findByNumber(admin.getNumber());
        if (user != null) {
            // 说明已经有了，这里我们就要提示前台不允许新增了
            throw new CustomException("该用户已存在!");
        }
        adminDao.updateByPrimaryKeySelective(admin);
    }

    public void delete(Integer id) {
        adminDao.deleteByPrimaryKey(id);
    }

    public Admin login(Admin admin) {
        // 1. 进行一些非空判断
        if (admin.getNumber() == null || "".equals(admin.getNumber())) {
            throw new CustomException("学工号不能为空");
        }
        if (admin.getPassword() == null || "".equals(admin.getPassword())) {
            throw new CustomException("密码不能为空");
        }
        // 2. 遍历数据库对比数据
        Admin user = adminDao.findByNumberAndPassword(admin.getNumber(), admin.getPassword());
        if (user == null) {
            // 如果查出来没有，那说明输入的学号或密码有误，提示用户，不允许登录
            throw new CustomException("学工号或密码输入错误");
        }
        // 如果查出来了有，那说明确实有这个用户并且输入的用户名和密码无误；
        // 生成该登录用户对应的token，然后跟着user一起返回到前台
        String token = JwtTokenUtils.genToken(user.getId().toString(), user.getPassword());
        user.setToken(token);
        return user;
    }

    public Admin findById(Integer id) {
        return adminDao.selectByPrimaryKey(id);
    }
}
