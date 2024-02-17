package com.example.controller;

import com.example.common.Result;
import com.example.entity.Record;
import com.example.entity.Params;
import com.example.service.RecordService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@CrossOrigin
@RestController
@RequestMapping("/record")
public class RecordController {

    @Resource
    private RecordService recordService;

    @GetMapping("/search")
    public Result findBySearch(Params params) {
        PageInfo<Record> info = recordService.findBySearch(params);
        return Result.success(info);
    }

    @PostMapping
    public Result save(@RequestBody Record Record) {
        if (Record.getId() == null) {
            recordService.add(Record);
        } else {
            recordService.update(Record);
        }
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        recordService.delete(id);
        return Result.success();
    }

}
