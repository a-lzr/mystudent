package service;

import entity.Faculty;
import entity.Group;
import entity.Student;

import java.io.*;
import java.util.HashSet;
import java.util.Iterator;

public class StudentService {

    private static String ffaculties = "faculties.fl";
    private static String fgroups = "groups.fl";
    private static String fstudents = "students.fl";
    private HashSet<Faculty> faculties = new HashSet<>();
    private HashSet<Group> groups = new HashSet<>();
    public HashSet<Student> students = new HashSet<>();

    public StudentService() {
        try {
            load_faculties();
            load_groups();
            load_students();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Faculty get_faculty (String name)  {
        for (Faculty faculty : faculties) {
            if (faculty.getName().equals(name))
                return faculty;
        }
        return null;
    }

    public Group get_group (String name)  {
        for (Group group : groups) {
            if (group.getName().equals(name))
                return group;
        }
        return null;
    }

    public Student get_student (String surname, String name, String fathername)  {
        for (Student student : students) {
            if (student.getSurname().equals(surname) &&
                    student.getName().equals(name) &&
                    student.getFathername().equals(fathername))
                return student;
        }
        return null;
    }

    public Faculty add_faculty (String name) {
        Faculty faculty = new Faculty(name);
        faculties.add(faculty);
        try {
            save_faculties();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return faculty;
    }

    public Group add_group (String groupname, Faculty faculty) {
        Group group = new Group(groupname, faculty);
        groups.add(group);
        try {
            save_groups();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return group;
    }

    public Student add_student (String surname, String name, String fathername, Group group) {
        Student student = new Student(surname, name, fathername, group);
        students.add(student);
        try {
            save_students();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return student;
    }

    private void load_faculties() throws IOException {
        faculties.clear();
        File file = new File(ffaculties);
        if (file.exists()) {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
            while(true)
            {
                try {
                    faculties.add((Faculty) objectInputStream.readObject());
                }
                catch(EOFException e) {
                    break;
                }
                catch(Exception e) {
                    e.printStackTrace();
                    break;
                }
            }
            objectInputStream.close();
        }
    }

    private void load_groups() throws IOException {
        groups.clear();
        File file = new File(fgroups);
        if (file.exists()) {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
            while(true)
            {
                try {
                    groups.add((Group) objectInputStream.readObject());
                }
                catch(EOFException e) {
                    break;
                }
                catch(Exception e) {
                    e.printStackTrace();
                    break;
                }
            }
            objectInputStream.close();
        }
    }

    private void load_students() throws IOException {
        students.clear();
        File file = new File(fstudents);
        if (file.exists()) {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
            while(true)
            {
                try {
                    students.add((Student) objectInputStream.readObject());
                }
                catch(EOFException e) {
                    break;
                }
                catch(Exception e) {
                    e.printStackTrace();
                    break;
                }
            }
            objectInputStream.close();
        }
    }

    private void save_faculties() throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(ffaculties));
        for (Faculty faculty : faculties) {
            objectOutputStream.writeObject(faculty);
        }
        objectOutputStream.close();
    }

    private void save_groups() throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fgroups));
        for (Group group : groups) {
            objectOutputStream.writeObject(group);
        }
        objectOutputStream.close();
    }

    private void save_students() throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fstudents));
        for (Student student : students) {
            objectOutputStream.writeObject(student);
        }
        objectOutputStream.close();
    }
}