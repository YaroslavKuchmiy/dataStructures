package module1.lesson.lesson1;

import java.util.Iterator;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class Group implements Iterable<Student> {

    private Student[] students;

    public Group(final Student[] students) {
        this.students = students;
    }

    public Iterator<Student> iterator() {
        return new StudentsIterator(students);
    }

    private static class StudentsIterator implements Iterator<Student> {

        private int index = 0;
        private Student[] students;

        public StudentsIterator (Student[] students) {
            this.students = students;
        }

        @Override
        public boolean hasNext() {
            return this.students.length > index;
        }

        @Override
        public Student next() throws NoSuchElementException {
            if (!hasNext()){
                throw new NoSuchElementException();
            }
            return this.students[index++];
        }

    }

}
