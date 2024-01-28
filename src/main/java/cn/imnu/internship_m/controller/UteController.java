package cn.imnu.internship_m.controller;

import cn.imnu.internship_m.pojo.Result;
import cn.imnu.internship_m.pojo.Ute;
import cn.imnu.internship_m.service.UteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    //删除信息
    @DeleteMapping("/depts/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("根据id删除数据:{}", id);
        uteService.delete(id);
        return Result.success();
    }

    //新增信息
    @PostMapping("/depts")
    public Result add(@RequestBody Ute ute) {
        log.info("新增部门数据:{}", ute);
        uteService.add(ute);
        return Result.success();
    }
}
