using System.Collections.Generic;

namespace InvertedIndex.Library
{
    public interface IFileDatabaseController
    {
        List<string> _AllDocs { get; }
        string _Path { get; set; }

        void InitialiseReadAndSavingFiles(string path);

        void AddDocToDatabase(string[] paths);
        List<string> GetAllDocs();

        void ReadAllDocsFromDirectory(string path);
    }
}