using System;
using System.Collections.Generic;
using System.Linq;

namespace Average
{
    class Student
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
        }

        public static void AddCourseForStudent(int sudentID, Course course)
        {
            var student = GetStudentByStudentNumber(sudentID);
            student.AddToCourses(course);
        }

        private static Student GetStudentByStudentNumber(int studentNumber)
        {
            return AllStudents.Where(s => s.StudentNumber == studentNumber).First();
        }

        public static List<Student> GetTopThreeStudents()
        {
            CalculateAverage();
            List<Student> ss = AllStudents.OrderBy(s => s.Average).ToList<Student>();
            return ss;

        }

        private static void CalculateAverage()
        {
            foreach (var student in AllStudents)
            {
                float sum = 0;
                int counter = 0;
                foreach (var course in student.Courses)
                {
                    sum += course.Score;
                    counter++;
                }
                student.Average = (sum / counter);
            }
        }
    }
}
