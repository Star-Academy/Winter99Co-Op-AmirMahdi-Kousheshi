using System;
using System.Collections.Generic;
using System.Text;

namespace Average
{
    interface IFileReader
    {
        List<Student> GetStudentsFromJsonFile(string fileAddres);

        List<Course> GetGradesFromJsonFile(string fileAddres);

    }
}
