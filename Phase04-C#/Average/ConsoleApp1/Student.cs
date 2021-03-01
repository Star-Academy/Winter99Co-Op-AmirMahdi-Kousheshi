using System;
using System.Collections.Generic;
using System.Linq;

namespace Average
{
    public class Student
    {
        static StudentController controller = new StudentController();

        public Student(int studentNumber, string firstName, string lastName)
        {
            StudentNumber = studentNumber;
            FirstName = firstName;
            LastName = lastName;
            this.Courses = new List<Course>();
            controller.AddStudent(this);
        }

        public static void AddCourseForStudent(int studentNumber, Course course)
        {
            controller.AddCourseForStudent(studentNumber, course);
        }

        public static List<Student> GetTopThreeStudents()
        {
            return controller.GetTopThreeStudents();
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
