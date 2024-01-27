package cn.imnu.internship_m.service.impl;

import cn.imnu.internship_m.mapper.UteMapper;
import cn.imnu.internship_m.pojo.Ute;
import cn.imnu.internship_m.service.UteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UteServiceImpl implements UteService {

    @Autowired
    private UteMapper uteMapper;

    @Override
    public List<Ute> list() {
        return uteMapper.list();
    }

}