package cn.imnu.internship_m.mapper;

import cn.imnu.internship_m.pojo.Ute;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UteMapper {

    //查询成绩
    @Select("select * from dept")
    List<Ute> list();


}
