using System;
using Xunit;
using Average;
using System.Collections.Generic;

namespace xunit_test
{
    public class StudentTest
    {
       [Fact]
        public void TestStudentNumber()
        {
            Assert.Equal(1, new Student(1, "Amir", "Kouesheshi").StudentNumber);
        }

        [Fact]
        public void TestFirstName()
        {
            Assert.Equal("Amir", new Student(1, "Amir", "Kouesheshi").FirstName);
        }

        [Fact]
        public void TestLastName()
        {
            Assert.Equal("Kousheshi", new Student(1, "Amir", "Kousheshi").LastName);
        }

        [Fact]
        public void TestCourses()
        {
            var student = new Student(2, "Bagher", "Kousheshi");
            new Course(2, "AP", (float)19.6);
            Assert.Equal("AP", student.Courses[0].Lesson);

        }

        [Fact]
        public void TestAverage()
        {
            var student = new Student(3, "Arya", "Kousheshi");
            new Course(3, "AP", (float)19);
            new Course(3, "BP", (float)17);
            Assert.Equal(18, student.Average);

        }

        [Fact]
        public void TestTopStudents()
        {
            var student = new Student(4, "mmd", "mmdii");
            new Course(4, "AP", (float)-1);

            new Student(5, "amir", "amiri");
            new Course(5, "AP", (float)19);
            new Course(5, "BP", (float)17);
            List<Student> topStudents = Student.GetTopThreeStudents();
            Assert.Equal(student, topStudents[0]);
        }

        private void GetStudentByStudentNumberTest()
        {
            var student = new Student(10, "Albert", "tesla");
            Assert.Equal(student,Student.GetStudentByStudentNumber(10));
        }
    }
}
