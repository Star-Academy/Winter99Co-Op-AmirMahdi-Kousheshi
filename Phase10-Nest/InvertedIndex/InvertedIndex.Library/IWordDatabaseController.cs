using System.Collections.Generic;


namespace InvertedIndex.Library
{
    public interface IWordDatabaseController
    {
        Dictionary<string, List<string>> _WordLocations { get; set; }

        void InitialiseUpdateDatabase(List<string> allDocs);

        List<string> GetWordsNormalizeFromAllTextInDoc(string text);

        void AddNormalizeWordsInADocToDictionary(List<string> words);

        void AddDocForWordsInDatabase(string word, string docsName);

        Dictionary<string, List<string>> GetWordLocations();
    }
}