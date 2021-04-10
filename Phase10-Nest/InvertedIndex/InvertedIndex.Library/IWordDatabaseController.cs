using System.Collections.Generic;


namespace InvertedIndex.Library
{
    public interface IWordDatabaseController
    {
        void InitialiseUpdateDatabase(List<string> allDocs);

        List<string> GetWordsNormalizeFromAllTextInDoc(string text);

        void AddNormalizeWordsFromADocToList(List<string> words, string docName);

        void AddDocForWordsInDatabase(Word word, string docsName);
    }
}