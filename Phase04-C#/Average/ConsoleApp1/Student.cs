using System;
using System.Collections.Generic;
using System.Linq;

namespace Average
{
    public class Student
    {
       

        public Student(int studentNumber, string firstName, string lastName)
        {
            StudentNumber = studentNumber;
            FirstName = firstName;
            LastName = lastName;
            this.Courses = new List<Course>();
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
