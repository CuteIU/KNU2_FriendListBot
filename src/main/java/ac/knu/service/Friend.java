package ac.knu.service;

import lombok.Data;

enum Gender {
    MALE, FEMALE
}

@Data
public class Friend {
    private String name;
    private int age;
    private Gender gender;

    public Friend(String name, int age, Gender gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String toString() {
        return String.format("%-20s| %d\t| %s", name, age, gender);
    }
}