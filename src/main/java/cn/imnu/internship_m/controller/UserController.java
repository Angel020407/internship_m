package cn.imnu.internship_m.controller;

import cn.imnu.internship_m.entity.User;
import cn.imnu.internship_m.mapper.UserMapper;
import cn.imnu.internship_m.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}