package com.example.entity;

import javax.persistence.*;

@Table(name = "form")
public class Form {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;
    @Column(name = "num")
    private String num;
    @Column(name = "userId")
    private Integer userId;
    @Column(name = "img")
    private String img;
    @Column(name = "score")
    private String score;
    @Column(name = "appraise")
    private String appraise;
    @Column(name = "evaluate")
    private String evaluate;
    @Column(name = "userEschool")
    private String userEschool;
    @Transient
    private String userName;
    @Transient
    private String userNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getImg() {
        return img;
    }

    public void setFile(String img) {
        this.img = img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getAppraise() {
        return appraise;
    }

    public void setAppraise(String appraise) {
        this.appraise = appraise;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }

    public String getUserEschool() {
        return userEschool;
    }

    public void setUserEschool(String userEschool) {
        this.userEschool = userEschool;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }
}