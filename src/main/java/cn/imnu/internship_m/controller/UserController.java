package cn.imnu.internship_m.controller;

import cn.imnu.internship_m.entity.User;
import cn.imnu.internship_m.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/")
    public Boolean save(@RequestBody User user) {
        return userService.saveUser(user);
    }
    @DeleteMapping("/{id}")
    public Boolean deleteById(@PathVariable Integer id) {
        return userService.removeById(id);
    }
    @GetMapping("/{id}")
    public User findOne(@PathVariable Integer id) {
        return userService.getById(id);
    }

    //分页查询
    //接口路径user/page?pageNum=1&pageSize=10
    //RequestParam接受前台传过来的第几页，每页显示数
    @GetMapping("/page")
    public IPage<User> findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam String userName){

        IPage<User> page = new Page<>(pageNum,pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name",userName);
        return userService.page(page,queryWrapper);
    }


}