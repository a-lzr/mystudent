package action;

import entity.Faculty;
import entity.Group;
import entity.Student;
import service.StudentService;
import service.Reader;
import service.Writer;

import java.util.Iterator;

class StudentAction {
    private static StudentService service = new StudentService();

    static void new_student(Writer out, Reader in) {
        out.WriteString("Введите фамилию");
        String surname = in.ReadString();
        out.WriteString("Введите имя");
        String name = in.ReadString();
        out.WriteString("Введите отчество");
        String fathername = in.ReadString();
        out.WriteString("Введите номер группы");
        String groupname = in.ReadString();
        Group group = service.get_group(groupname);
        if (group == null) {
            out.WriteString("Введите факультет");
            String facultyname = in.ReadString();
            Faculty faculty = service.get_faculty(facultyname);
            if (faculty == null) {
                faculty = service.add_faculty(facultyname);
            }
            group = service.add_group(groupname, faculty);
        }
        service.add_student(surname, name, fathername, group);
    }

    static void print_group(Writer out, String group) {
        for (Student student : service.students) {
            if ((student.getGroup() != null) && student.getGroup().getName().equals(group)) {
                out.WriteString(student.getSurname() + " " + student.getName() + " " + student.getFathername());
            }
        }
    }
}