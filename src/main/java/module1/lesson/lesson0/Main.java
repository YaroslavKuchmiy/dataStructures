package module1.lesson.lesson0;
/*
"Интерфейс Iterable"

В этом практическом задании Вам дано два класса:

Student - класс пустышка
University - класс, который хранит в себе массив студентов
Задача! Сделать класс University "перечисляемым" (Iterable) по типу Student (Iterable), чтобы объекты этого класса можно было использовать в цикле for-each. Например:

final University univ = new University(students);
//...
for (Student student: university) {
	// ...
}
 */
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
