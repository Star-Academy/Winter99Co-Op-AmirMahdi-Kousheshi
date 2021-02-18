using System;
using System.Collections.Generic;
using Newtonsoft.Json;
using System.IO;
using System.Text;

namespace Average
{
    class FileReaderImpl : FileReader
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
            var input = File.ReadAllText(path);
            return JsonConvert.DeserializeObject<List<Student>>(input);
        }

        public List<Course> GetGradesFromJsonFile()
        {
            var input1 = File.ReadAllText(path1);
            return JsonConvert.DeserializeObject<List<Course>>(input1);
        }
    }
}
