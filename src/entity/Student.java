package entity;

import java.io.Serializable;
import java.util.Objects;

public class Student implements Serializable {
    private String surname;
    private String name;
    private String fathername;
    private Group group;

    public Student(String surname, String name, String fathername, Group group) {
        this.surname = surname;
        this.name = name;
        this.fathername = fathername;
        this.group = group;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFathername() {
        return fathername;
    }

    public void setFathername(String fathername) {
        this.fathername = fathername;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return surname.equals(student.surname) &&
                name.equals(student.name) &&
                fathername.equals(student.fathername) &&
                group.equals(student.group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(surname, name, fathername, group);
    }
}