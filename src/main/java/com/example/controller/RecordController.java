package com.example.controller;

import com.example.common.Result;
import com.example.entity.Record;
import com.example.service.RecordService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@CrossOrigin
@RestController
@RequestMapping("/record")
public class RecordController {

    @Resource
    private RecordService recordService;

    @PostMapping("/search")
    public Result save(@RequestBody Record record) {
        if (record.getId() == null) {
            recordService.add(record);
        } else {
            recordService.update(record);
        }
        return Result.success();
    }

}
