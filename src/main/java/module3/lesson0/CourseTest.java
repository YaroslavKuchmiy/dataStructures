package module3.lesson0;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

public class CourseTest {

    @Test
    public void testCourseEqWithNotCourse() throws Exception {
        final Course testCourse = new Course(1l, "101", new ArrayList());
        assertFalse("Equals with Strign return true 0_0", testCourse.equals("test"));
    }

    @Test
    public void testCourseEqWithCourseWithDifferentUuid() throws Exception {
        final Course testCourse = new Course(1l, "101", new ArrayList());
        final Course testCourse2 = new Course(2l, "101", new ArrayList());
        assertFalse("2 Courses with different uuids returns true 0_0", testCourse.equals(testCourse2));
    }

    @Test
    public void testCourseEqWithCourseWithSameUuid() throws Exception {
        final Course testCourse = new Course(1l, "102", new ArrayList());
        final Course testCourse2 = new Course(1l, "101", new ArrayList());
        assertTrue("2 Courses with same uuids but different names returns false 0_0", testCourse.equals(testCourse2));
    }

    @Test
    public void testSessionEqWithString() throws Exception {
        final Course testCourse = new Course(1l, "102", new ArrayList());
        final Course testCourse2 = new Course(1l, "101", new ArrayList());
        final Course.Session session = testCourse.new Session(new Date());
        assertFalse(session.equals(testCourse2));
    }

    @Test
    public void testSessionEqWithDifferentDateAndSession() throws Exception {
        final Course testCourse = new Course(1l, "102", new ArrayList());
        final Course testCourse2 = new Course(1l, "101", new ArrayList());
        final Course.Session session = testCourse.new Session(new Date());
        final Course.Session session2 = testCourse2.new Session(new Date(0));
        assertFalse(session.equals(session2));
    }

    @Test
    public void testSessionEqWithSameDateButDifferentCourseSession() throws Exception {
        final Course testCourse = new Course(1l, "102", new ArrayList());
        final Course testCourse2 = new Course(1l, "101", new ArrayList());
        final Date d = new Date();
        final Course.Session session = testCourse.new Session(d);
        final Course.Session session2 = testCourse2.new Session(d);
        assertFalse(session.equals(session2));
    }

    @Test
    public void testSessionEqWithSameSession() throws Exception {
        final Course testCourse = new Course(1l, "102", new ArrayList());
        final Course testCourse2 = new Course(1l, "101", new ArrayList());
        final Date d = new Date();
        final Course.Session session = testCourse.new Session(d);
        final Course.Session session2 = testCourse.new Session(d);
        assertTrue(session.equals(session2));
    }

}
