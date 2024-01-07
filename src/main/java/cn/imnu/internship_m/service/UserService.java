package cn.imnu.internship_m.service;

import cn.imnu.internship_m.entity.User;
import org.springframework.stereotype.Service;
import cn.imnu.internship_m.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    public int save(User user) {
        if (user.getId() == null) {
            return userMapper.insert(user);
        }
        else {
            return userMapper.update(user);
        }
    }
    public Integer deleteById(Integer id) {
        return userMapper.deleteById(id);
    }
}
