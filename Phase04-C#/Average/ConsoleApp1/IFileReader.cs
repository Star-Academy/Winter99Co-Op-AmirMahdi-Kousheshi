using System;
using System.Collections.Generic;
using System.Text;

namespace Average
{
    interface IFileReader
    {
        string StudentsFilePath { get; set; }
        string GradesFilepath { get; set; }

        List<Student> GetStudentsFromJsonFile();

        List<Course> GetGradesFromJsonFile();

    }
}
