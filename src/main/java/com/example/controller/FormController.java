package com.example.controller;

import com.example.common.AutoLog;
import com.example.common.Result;
import com.example.entity.Form;
import com.example.entity.Params;
import com.example.service.FormService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@CrossOrigin
@RestController
@RequestMapping("/form")
public class FormController {

    @Resource
    private FormService formService;

    @GetMapping("/search")
    public Result findBySearch(Params params) {
        PageInfo<Form> info = formService.findBySearch(params);
        return Result.success(info);
    }

    @PostMapping
    @AutoLog("作业提交")
    public Result save(@RequestBody Form form) {
        if (form.getId() == null) {
            formService.add(form);
        } else {
            formService.update(form);
        }
        return Result.success();
    }

    @DeleteMapping("/{id}")
    @AutoLog("删除作业")
    public Result delete(@PathVariable Integer id) {
        formService.delete(id);
        return Result.success();
    }

}
