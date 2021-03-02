using System;
using System.Collections.Generic;
using System.Text.RegularExpressions;
using System.IO;

namespace InvertedIndex.Library
{
    public class WordsDatabaseController
    {

        private Dictionary<string, List<string>> WordLocations = new Dictionary<string, List<string>>();

        FilesDatabaseController controller;
        public WordsDatabaseController(FilesDatabaseController fileController)
        {
            this.controller = fileController;
            this.InitialiseUpdateDatabase();
        }

        private void InitialiseUpdateDatabase()
        {
            foreach (var file in this.controller.GetAllFiles())
            {
                AddNormalizeWordsInADocToDictionary(GetWordsNormalizeFromAllTextInDoc(File.ReadAllText(file)), file);
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

        public void AddNormalizeWordsInADocToDictionary(List<string> words, string doc)
        {
            foreach (var word in words)
            {
                this.AddDocForWordsInDatabase(word, doc);
            }
        }

        private void AddDocForWordsInDatabase(string word, string docsName)
        {
            List<string> wordDocs = new List<string>();
            if (this.WordLocations.ContainsKey(word))
            {
                if (this.WordLocations[word].Contains(docsName))
                {
                    return;
                }
                wordDocs.AddRange(this.WordLocations[word]);
            }
            wordDocs.Add(docsName);
            this.WordLocations[word] = wordDocs;
        }

        public Dictionary<string, List<string>> GetWordLocations()
        {
            return this.WordLocations;
        }
    }
}
