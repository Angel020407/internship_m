package cn.imnu.internship_m.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ute {
    private Integer id; //ID
    private String name; //姓名
    private String lrscore; //听课记录成绩
    private String ldscore; //教学设计成绩
    private String tcscore; //主题班会成绩
}