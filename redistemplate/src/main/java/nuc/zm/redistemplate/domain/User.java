package nuc.zm.redistemplate.domain;


import java.io.Serializable;

public class User  {
    private int sno;
    private String name;
    private int age;

    public User() {
    }

    public User(int sno, String name, int age) {
        this.sno = sno;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "sno=" + sno +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

