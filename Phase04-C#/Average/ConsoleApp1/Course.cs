using System;
using System.Collections.Generic;
using System.Text;

namespace Average
{
    public class Course
    {
        public Course(int studentNumber, string lesson, float score)
        {
            StudentNumber = studentNumber;
            Lesson = lesson;
            Score = score;
            Student.AddCourseForStudent(studentNumber, this);
        }

        public int StudentNumber
        {
            get;
        }

        public string Lesson
        {
            get;
        }

        public float Score
        {
            get;
        }
    }
}
