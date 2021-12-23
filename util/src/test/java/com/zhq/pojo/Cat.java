package com.zhq.pojo;

public class Cat {
    private String name;
    private Integer age;
    private String character;

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", character='" + character + '\'' +
                '}';
    }
}
