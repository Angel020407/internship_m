package cn.imnu.internship_m.service;

import cn.imnu.internship_m.pojo.Ute;

import java.util.List;

public interface UteService {
    /**
     * 查询全部部门数据
     *
     * @return
     */
    List<Ute> list();

    //根据id删除
    void delete(Integer id);

    //新增
    void add(Ute ute);
}
