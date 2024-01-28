package cn.imnu.internship_m.mapper;

import cn.imnu.internship_m.pojo.Ute;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UteMapper {

    //指导老师查询全部学生信息
    @Select("select * from dept")
    List<Ute> list();


    //根据id删除
    @Delete("delete from dept where id = #{id}")
    void delete(Integer id);

    //新增
    @Insert("insert into dept(name,createTime,updateTime) values(#{name},#{createTime},#{updateTime})")
    void insert(Ute ute);
}
