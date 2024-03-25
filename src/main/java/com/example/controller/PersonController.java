package com.example.controller;

import com.example.common.AutoLog;
import com.example.common.Result;
import com.example.entity.Admin;
import com.example.entity.Params;
import com.example.entity.Person;
import com.example.entity.Student;
import com.example.service.PersonService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin
@RestController
@RequestMapping("/person")
public class PersonController {

    @Resource
    private PersonService personService;

    @GetMapping("/search")
    public Result findBySearch(Params params) {
        PageInfo<Person> info = personService.findBySearch(params);
        return Result.success(info);
    }

    @PostMapping
    @AutoLog("编辑个人信息")
    public Result save(@RequestBody Person person) {
            personService.update(person);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    @AutoLog("删除个人信息")
    public Result delete(@PathVariable Integer id) {
        personService.delete(id);
        return Result.success();
    }
}
