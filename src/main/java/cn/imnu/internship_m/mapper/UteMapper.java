package cn.imnu.internship_m.mapper;

import cn.imnu.internship_m.pojo.Ute;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UteMapper {

    //指导老师查询全部学生信息
    @Select("select * from dept")
    List<Ute> list();


}
