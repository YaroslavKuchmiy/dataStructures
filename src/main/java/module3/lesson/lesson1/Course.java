package module3.lesson.lesson1;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Date;

/*
"hashCode"

И вновь Вашему вниманию предлагаем довольно простое задание. Но это последнее "халявное" задание в этом модуле ;)

Итак, как думаю Вы уже догадались, сегодня Вам предстоит реализовать "hashCode" метод для нашего примера из прошлого
 задания.

Единственное, мы немного поменяли модель Course, а точнее поменяли тип поля uuid на String.

Метод hashCode должен работать следующим образом:

если uuid равно null - возвращает 0;
если uuid пустая строка - возвращает 0;
в других случаях нужно суммировать все characters в uuid (напомню из курса 101, что тип char - это не более чем число ;)
) и возвращать сумму.
Что касается класса Session, то в его случае мы должны в качестве hashCode возвращать результат работы метода hashCode
 у поля startDate(тип Date имеет свой hashCode).

Удачи Вам и до встречи в следующем уроке!
 */
public class Course {

    private final String uuid;

    private final String name;

    private final List<Session> sessions;

    public Course(final String uuid, final String name, final List<Session> sessions) {
        this.uuid = uuid;
        this.name = name;
        this.sessions = sessions;
    }

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    @Override
    public boolean equals(final Object object) {
        if (!(object instanceof Course))
            return false;

        final Course that = (Course) object;
        if (!that.getUuid().equals(this.getUuid()))
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        // BEGIN (write your solution here)
        int res = 0;
        if (getUuid() == null || getUuid().equals("")) {
            return res;
        }
        final String str = getUuid();
        for (int i = 0; i < str.length(); i++) {
            res += str.charAt(i);
        }
        return res;
        // END
    }

    public class Session {

        private final Date startDate;

        public Session(final Date startDate) {
            this.startDate = startDate;
        }

        public Date getStartDate() {
            return this.startDate;
        }

        public Course getCourse() {
            return Course.this;
        }

        @Override
        public boolean equals(final Object object) {
            if (!(object instanceof Session)) return false;

            final Session that = (Session) object;
            if (!that.getStartDate().equals(this.getStartDate())) return false;

            if (!that.getCourse().equals(this.getCourse())) return false;

            return true;
        }

        @Override
        public int hashCode() {
            // BEGIN (write your solution here)
            return getStartDate().hashCode();
            // END
        }
    }
}

