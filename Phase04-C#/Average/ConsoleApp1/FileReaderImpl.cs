using System;
using System.Collections.Generic;
using Newtonsoft.Json;
using System.IO;
using System.Text;

namespace Average
{
    class FileReaderImpl : IFileReader
    {

        public List<Student> GetStudentsFromJsonFile(string fildeAddres)
        {
            var fileInput = File.ReadAllText(fildeAddres);
            return JsonConvert.DeserializeObject<List<Student>>(fileInput);
        }   

        public List<Course> GetGradesFromJsonFile(string fildeAddres)
        {
            var fileInput = File.ReadAllText(fildeAddres);
            return JsonConvert.DeserializeObject<List<Course>>(fileInput);
        }
    }
}
