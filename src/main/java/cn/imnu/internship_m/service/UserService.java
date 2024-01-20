package cn.imnu.internship_m.service;

import cn.imnu.internship_m.entity.User;
import cn.imnu.internship_m.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {
    public Boolean saveUser(User user) {
        /*if (user.getId() == null) {
            return userMapper.insert(user);
        }
        else {
            return userMapper.update(user);
        }*/
        return saveOrUpdate(user);
    }
    /*public Integer deleteById(Integer id) {
        return userMapper.deleteById(id);
    }

    public List selectPage(Integer pageNum, Integer pageSize, String userName) {
        return userMapper.selectPage(pageNum,pageSize,userName);
    }

    public Integer selectTotal(String userName) {
        return userMapper.selectTotal(userName);
    }*/
}
