package cn.imnu.internship_m.mapper;

import cn.imnu.internship_m.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
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
    @Select("select * from user limit #{pageNum},#{pageSize}")
    List<User> selectPage(Integer pageNum, Integer pageSize);
    //总记录数
    @Select("select count(*) from user")
    Integer selectTotal();

}
