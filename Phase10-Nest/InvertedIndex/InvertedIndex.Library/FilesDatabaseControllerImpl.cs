using System;
using System.Collections.Generic;
using System.IO;
using System.Text;

namespace InvertedIndex.Library
{
    public class FilesDatabaseControllerImpl : IFileDatabaseController
    {
        public List<string> _AllDocs { get; }
        public string _Path { get; set; }

        public FilesDatabaseControllerImpl()
        {
            _AllDocs = new List<string>();
        }

        public void InitialiseReadAndSavingFiles(string path)
        {
            ReadAllDocsFromDirectory(path);
        }

        public void ReadAllDocsFromDirectory(string path)
        {
            AddDocToDatabase(Directory.GetFiles(path));
        }

        public void AddDocToDatabase(string[] paths)
        {
            _AllDocs.AddRange(paths);
        }

        public List<string> GetAllDocs()
        {
            return _AllDocs;
        }
    }
}