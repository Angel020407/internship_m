package cn.imnu.internship_m.controller;

import cn.imnu.internship_m.pojo.Result;
import cn.imnu.internship_m.pojo.Ute;
import cn.imnu.internship_m.service.UteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequestMapping("/depts")
@RestController
public class UteController {

    @Autowired
    private UteService uteService;

    @GetMapping
    public Result list() {
        log.info("查询全部部门数据");
        //调用service查询部门数据
        List<Ute> uteList = uteService.list();
        return Result.success(uteList);
    }
}
