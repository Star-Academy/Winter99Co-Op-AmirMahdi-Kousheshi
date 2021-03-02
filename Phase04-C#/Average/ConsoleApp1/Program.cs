using System;
using Newtonsoft.Json;
using System.Collections.Generic;
using System.Linq;
using System.IO;

namespace Average
{
    class Program
    {
        public static string StudentsJsonFileAddres = "../Source/Students.json";
        public static string GradesJsonFileAddres = "..Source//Grades.json";
        static void Main(string[] args)
        {
            var fileReader = new FileReaderImpl();
            var students = fileReader.GetStudentsFromJsonFile(StudentsJsonFileAddres);
            var grades = fileReader.GetGradesFromJsonFile(GradesJsonFileAddres);

            var controller = new StudentController();
            students.ForEach(student => controller.AddStudent(student));
            grades.ForEach(course => controller.AddCourseForStudent(course));


            foreach (var student in controller.GetTopThreeStudents())
            {
                Console.WriteLine(student.FirstName);
                Console.WriteLine(student.LastName);
                Console.WriteLine(student.Average);
                Console.WriteLine();
            }
        }
    }
}
