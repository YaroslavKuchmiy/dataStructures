package module1.lesson.lesson1;

import java.util.Iterator;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class Main {

    public static void main(final String... args) {
        final int studentsCount = 10_000;
        final Student[] students = new Student[studentsCount];
        for (int i = 0; i < studentsCount; i++) {
            students[i] = new Student();
        }

        final University univ = new University(students);
        final Group group = new Group(students);
        checkThaIteratorReturCorrectResults(univ, students);
        checkThaIteratorReturCorrectResults(group, students);

        checkThatIteratorThrowsExceptions(univ.iterator());
        checkThatIteratorThrowsExceptions(group.iterator());
    }

    private static void checkThaIteratorReturCorrectResults(final Iterable<Student> students, final Student[] studentsArray) {
        int y = 0;
        for (Student stud: students) {
            if (studentsArray[y] != stud) {
                throw new RuntimeException(String.format("Iterator return incorrect element with index: %d", y));
            }
            y++;
        }
        if (y != studentsArray.length) {
            throw new RuntimeException(String.format("Iterator returns incorrect amount of Stundets: %d instead of: %d", y, studentsArray.length));
        }
    }

    private static void checkThatIteratorThrowsExceptions(final Iterator<Student> students) {
        while(students.hasNext()) students.next();
        try {
            students.next();
            throw new RuntimeException("Iterator.next() do not throw the Exception when hasNext returns false");
        } catch(final NoSuchElementException e) {}
    }

}
