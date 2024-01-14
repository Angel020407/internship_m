package cn.imnu.internship_m.controller;

import cn.imnu.internship_m.entity.User;
import cn.imnu.internship_m.mapper.UserMapper;
import cn.imnu.internship_m.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @GetMapping ("/")
    public List<User> index() {
        return userMapper.findAll();
    }

    @Autowired
    private UserService userService;
    @PostMapping("/")
    public Integer save(@RequestBody User user) {
        return userService.save(user);
    }
    @DeleteMapping("/{id}")
    public Integer deleteById(@PathVariable Integer id) {
        return userMapper.deleteById(id);
    }

    //分页查询
    //接口路径user/page?pageNum=1&pageSize=10
    //RequestParam接受前台传过来的第几页，每页显示数
    @GetMapping("/page")
    public Map<String,Object> findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam String userName){
        pageNum=(pageNum-1)*pageSize;
        userName = "%" + userName + "%";
        List<User> data=userService.selectPage(pageNum,pageSize,userName);
        Integer total=userMapper.selectTotal(userName);
        Map<String,Object> res=new HashMap<>();
        res.put("data",data);
        res.put("total",total);
        return res;
    }


}