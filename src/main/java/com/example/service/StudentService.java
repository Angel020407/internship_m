package com.example.service;

import com.example.dao.StudentDao;
import com.example.entity.Params;
import com.example.entity.Student;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class StudentService {

    private static final Logger log = LoggerFactory.getLogger(StudentService.class);

    @Resource
    private StudentDao studentDao;


    public PageInfo<Student> findBySearch(Params params ) {
        // 开启分页查询
        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        // 接下来的查询会自动按照当前开启的分页设置来查询
        List<Student> list = studentDao.findBySearch(params);
        return PageInfo.of(list);
    }

}
