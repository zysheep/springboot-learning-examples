package cn.zysheep.springboot.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @version v1.0.0
 * @ProjectName: springboot-learning-examples
 * @ClassName: Person
 * @Author: 三月三
 */

@Component
@Data
public class Person {
    private String id;
    private String name;
    private int age;

    public Person() {
    }

    public Person(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

