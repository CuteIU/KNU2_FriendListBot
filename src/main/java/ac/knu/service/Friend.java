package ac.knu.service;

import lombok.Getter;

enum Gender{
    Male, Female;
}
@Getter
public class Friend {
    private String name;
    private int age;
    private Gender gender;
    public Friend(String name, int age, Gender gender)
    {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }
}