using System;
using System.Collections.Generic;
using System.IO;
using System.Text.RegularExpressions;

namespace InvertedIndex.Library
{
    public class WordsDatabaseControllerImpl : IWordDatabaseController
    {
        public List<Word> AllWords { get; }

        public WordsDatabaseControllerImpl()
        {
            this.AllWords = new List<Word>();
        }

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
                AllWords.Add(new Word(word));
            }
        }

        public void AddDocForWordsInDatabase(string wordName, string docName)
        {
            var word = GetWordByName(wordName);
            if (AllWords.Contains(word))
            {
                if (!word.Docs.Contains(docName))
                {
                    word.AddDoc(docName);
                }
            }
        }

        public Word GetWordByName(string wordName)
        {
            foreach (var word in AllWords)
            {
                if (word.Name.Equals(wordName))
                {
                    return word;
                }
            }

            return null;
        }
    }
}