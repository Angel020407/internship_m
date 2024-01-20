package cn.imnu.internship_m.mapper;

import cn.imnu.internship_m.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

//@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from user")
    List<User> findAll();
    @Insert("insert into user(name,password,sex,age) " +
            "VALUES(#{name},#{password},#{sex},#{age};")
//这里只是做测试使用
    int insert(User user);
    @Update("update user set username=#{username},password=#{password}," +
            "sex=#{sex},age=#{age} where id=#{id}")
    int update(User user);
    @Delete ("delete from user where id=#{id}")
    int deleteById(@Param("id") Integer id);
    //分页查询
    @Select("select * from user where name like #{userName} limit #{pageNum},#{pageSize}")
    List<User> selectPage(Integer pageNum, Integer pageSize, String userName);
    //总记录数
    @Select("select count(*) from user where name like #{userName}")
    Integer selectTotal(String userName);

}