using System;
using System.Collections.Generic;
using System.IO;
using System.Text;

namespace InvertedIndex.Library
{
    public class FilesDatabaseController
    {
        private List<string> AllFiles = new List<string>();

        public void AddDocToDatabase(string[] paths)
        {
            this.AllFiles.AddRange(paths);
        }

        public List<string> GetAllFiles()
        {
            return this.AllFiles;
        }

        public void ReadAllFiles(string path)
        {
            this.AddDocToDatabase(Directory.GetFiles(path));
        }
    }
}
