using System;
using System.Collections.Generic;
using System.Linq;

namespace Average
{
    public class Student
    {
        public static List<Student> AllStudents = new List<Student>();
        public Student(int studentNumber, string firstName, string lastName)
        {
            StudentNumber = studentNumber;
            FirstName = firstName;
            LastName = lastName;
            this.Courses = new List<Course>();
            AllStudents.Add(this);
        }

        public int StudentNumber
        {
            get;
        }

        public string FirstName
        {
            get;
        }

        public string LastName
        {
            get;
        }

        public List<Course> Courses
        {
            get;
        }

        public float Average
        {
            get; set;
        }

        public void AddToCourses(Course course)
        {
            this.Courses.Add(course);
            this.CalculateAverage();
        }

        public static void AddCourseForStudent(int sudentID, Course course)
        {
            var student = GetStudentByStudentNumber(sudentID);
            student.AddToCourses(course);
        }

        public static Student GetStudentByStudentNumber(int studentNumber)
        {
            return AllStudents.Where(s => s.StudentNumber == studentNumber).First();
        }

        public static List<Student> GetTopThreeStudents()
        {
            return AllStudents.OrderBy(s => s.Average).ToList<Student>();
        }

        private void CalculateAverage()
        {
            float sum = 0;
            int counter = 0;
            foreach (var course in this.Courses)
            {
                sum += course.Score;
                counter++;
            }
            this.Average = (sum / counter);
        }
    }
}
