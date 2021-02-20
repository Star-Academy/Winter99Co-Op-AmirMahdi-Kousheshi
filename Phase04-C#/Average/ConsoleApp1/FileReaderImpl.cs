using System;
using System.Collections.Generic;
using Newtonsoft.Json;
using System.IO;
using System.Text;

namespace Average
{
    class FileReaderImpl : IFileReader
    {

        public FileReaderImpl(string path, string path1)
        {
            this.path = path;
            this.path1 = path1;
        }

        public string path { get; set; }
        public string path1 { get; set; }

        public List<Student> GetStudentsFromJsonFile()
        {
            var fileInput = File.ReadAllText(path);
            return JsonConvert.DeserializeObject<List<Student>>(fileInput);
        }   

        public List<Course> GetGradesFromJsonFile()
        {
            var fileInput = File.ReadAllText(path1);
            return JsonConvert.DeserializeObject<List<Course>>(fileInput);
        }
    }
}
