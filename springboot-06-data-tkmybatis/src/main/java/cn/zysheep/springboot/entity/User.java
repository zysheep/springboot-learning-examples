package cn.zysheep.springboot.entity;

import javax.persistence.*;

@Table(name = "mybatis.user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;


    private Integer age;

    private String email;


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public Integer getAge() {
        return age;
    }


    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }
}