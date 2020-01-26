package entity;

import java.io.Serializable;
import java.util.Objects;

public class Group implements Serializable {
    private String name;
    private Faculty faculty;

    public Group(String name, Faculty faculty) {
        this.name = name;
        this.faculty = faculty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return name.equals(group.name) &&
                faculty.equals(group.faculty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, faculty);
    }
}
