using System.Collections.Generic;


namespace InvertedIndex.Library
{
    public interface IWordDatabaseController
    {
        void InitialiseUpdateDatabase(List<string> allDocs);

        List<string> GetWordsNormalizeFromAllTextInDoc(string text);

        void AddNormalizeWordsInADocToDictionary(List<string> words);

        void AddDocForWordsInDatabase(string word, string docsName);
    }
}