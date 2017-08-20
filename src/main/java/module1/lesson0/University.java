package module1.lesson0;

import java.util.Iterator;
import java.util.Arrays;
import java.util.List;

public class University implements Iterable<Student> {

    private final Student[] students;

    public University(final Student[] students) {
        this.students = students;
    }

    public Iterator iterator() {
        List<Student> studentsList = Arrays.asList(students);
        return studentsList.iterator();
    }

}
