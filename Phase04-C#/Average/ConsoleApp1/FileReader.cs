﻿using System;
using System.Collections.Generic;
using System.Text;

namespace Average
{
    interface FileReader
    {
        string path { get; set; }
        string path1 { get; set; }

        List<Student> GetStudentsFromJsonFile();

        List<Course> GetGradesFromJsonFile();

    }
}
