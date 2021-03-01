using System;
using Newtonsoft.Json;
using System.Collections.Generic;
using System.Linq;
using System.IO;

namespace Average
{
    class Program
    {
        public static string path = "../Source/Students.json";
        public static string path1 = "..Source//Grades.json";
        static void Main(string[] args)
        {
            var fileReader = new FileReaderImpl(path, path1);
            fileReader.GetStudentsFromJsonFile();
            fileReader.GetGradesFromJsonFile();
            

            foreach (var student in Student.GetTopThreeStudents())
            {
                Console.WriteLine(student.FirstName);
                Console.WriteLine(student.LastName);
                Console.WriteLine(student.Average);
                Console.WriteLine();
            }
        }
    }
}
