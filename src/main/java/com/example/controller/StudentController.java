package com.example.controller;

import com.example.common.Result;
import com.example.entity.Audit;
import com.example.entity.Params;
import com.example.entity.Student;
import com.example.service.StudentService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin
@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource
    private StudentService studentService;

    @GetMapping("/search")
    public Result findBySearch(Params params) {
        PageInfo<Student> info = studentService.findBySearch(params);
        return Result.success(info);
    }

}