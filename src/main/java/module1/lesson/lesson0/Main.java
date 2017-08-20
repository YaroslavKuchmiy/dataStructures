package module1.lesson.lesson0;

import java.util.Iterator;

public class Main {

    public static void main(final String... args) {
        final int studentsCount = 10_000;
        final Student[] students = new Student[studentsCount];
        for (int i = 0; i < studentsCount; i++) {
            students[i] = new Student();
        }

        final University univ = new University(students);
        final Iterator<Student> stundentIterator = univ.iterator();

        int y = 0;
        for (Student stud: univ) {
            if (students[y] != stud) {
                throw new RuntimeException(String.format("Iterator return incorrect element with index: %d", y));
            }
            y++;
        }
    }

}
