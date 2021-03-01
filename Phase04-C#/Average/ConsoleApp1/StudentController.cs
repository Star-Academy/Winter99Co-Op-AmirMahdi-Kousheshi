using System;
using System.Collections.Generic;
using System.Text;
using System.Linq;

namespace Average
{
    class StudentController
    {
        private List<Student> AllStudents = new List<Student>();

        public void AddStudent(Student student)
        {
            this.AllStudents.Add(student);
        }

        public void AddCourseForStudent( Course course)
        {
            var student = GetStudentByStudentNumber(course.StudentNumber);
            student.AddToCourses(course);
        }

        public Student GetStudentByStudentNumber(int studentNumber)
        {
            return this.AllStudents.Where(s => s.StudentNumber == studentNumber).First();
        }

        public List<Student> GetTopThreeStudents()
        {
            return this.AllStudents.OrderBy(s => s.Average).ToList<Student>();
        }
    }
}
