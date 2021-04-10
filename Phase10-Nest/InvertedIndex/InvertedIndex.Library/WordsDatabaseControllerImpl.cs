using System;
using System.Collections.Generic;
using System.IO;
using System.Text.RegularExpressions;

namespace InvertedIndex.Library
{
    public class WordsDatabaseControllerImpl : IWordDatabaseController
    {
        public Dictionary<string, List<string>> _WordLocations { get; set; }

        public void InitialiseUpdateDatabase(List<string> allDocs)
        {
            foreach (var doc in allDocs)
            {
                AddNormalizeWordsInADocToDictionary(GetWordsNormalizeFromAllTextInDoc(File.ReadAllText(doc)));
            }
        }

        public List<string> GetWordsNormalizeFromAllTextInDoc(string text)
        {
            List<string> normalizedWords = new List<string>();
            string[] splitedWords = Regex.Split(text, "\\s+");
            foreach (var word in splitedWords)
            {
                normalizedWords.Add(Regex.Replace(word, "[\\W _]+", ""));
            }

            return normalizedWords;
        }

        public void AddNormalizeWordsInADocToDictionary(List<string> words)
        {
            foreach (var word in words)
            {
                _WordLocations.Add(word, new List<string>());
            }
        }

        public void AddDocForWordsInDatabase(string word, string docsName)
        {
            _WordLocations[word].Add(docsName);
        }

        public Dictionary<string, List<string>> GetWordLocations()
        {
            return _WordLocations;
        }
    }
}