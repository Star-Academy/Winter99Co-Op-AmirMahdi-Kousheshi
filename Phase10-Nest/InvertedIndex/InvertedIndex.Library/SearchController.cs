using System;
using System.Collections.Generic;

namespace InvertedIndex.Library
{
    public class SearchController
    {
        StringSeparator separator = new StringSeparator();

        public List<string> InitialiseSearch(string searchInput)
        {
            List<string> noSignWords = new List<string>(separator.SeparateWordsBySign(searchInput, ""));
            List<string> plusSignWords = new List<string>(separator.SeparateWordsBySign(searchInput, "+"));
            List<string> minusSignWords = new List<string>(separator.SeparateWordsBySign(searchInput, "-"));

            return FindDocsOfSearchWords(noSignWords, plusSignWords, minusSignWords);
        }

        private List<string> FindDocsOfSearchWords(List<string> noSignWords, List<string> plusSignWords,
            List<string> minusSignWords)
        {
            List<string> noSignWordsDocs = new List<string>(FindDocsOfNoSignWords(noSignWords));
            List<string> plusSignWordsDocs = new List<string>(FindDocsOfSignWords(plusSignWords));
            List<string> minusSignWordsDocs = new List<string>(FindDocsOfNoSignWords(minusSignWords));

            return FindFinalDocs(noSignWordsDocs, plusSignWordsDocs, minusSignWordsDocs, minusSignWords);
        }

        private List<string> FindDocsOfSignWords(List<string> signWords)
        {
            List<string> checker = new List<string>();
            foreach (var word in signWords)
            {
                checker.AddRange(FindDocsOfAWord(word));
            }

            return checker;
        }

        private List<string> FindDocsOfNoSignWords(List<string> noSignWords)
        {
            List<string> checker = new List<string>();
            foreach (var nothingWord in noSignWords)
            {
                if (FindDocsOfAWord(nothingWord).Count != 0)
                {
                    checker.AddRange(FindDocsOfAWord(nothingWord));
                    break;
                }
            }

            Dictionary<string, bool> hashMap = MarkSameDocsOfNoSignWords(checker, noSignWords);
            List<string> docs = new List<string>();
            foreach (var doc in hashMap.Keys)
            {
                if (hashMap.ContainsKey(doc))
                {
                    docs.Add(doc);
                }
            }

            return docs;
        }

        private Dictionary<string, bool> MarkSameDocsOfNoSignWords(List<string> checker, List<string> noSignWords)
        {
            Dictionary<string, bool> hashMap = new Dictionary<string, bool>();
            checker.ForEach(doc => hashMap.Add(doc, false));
            foreach (var nothingWord in noSignWords)
            {
                foreach (var docID in FindDocsOfAWord(nothingWord))
                {
                    if (hashMap.ContainsKey(docID))
                    {
                        hashMap[docID] = true;
                    }
                }
            }

            return hashMap;
        }

        private List<string> FindDocsOfAWord(string word)
        {
            List<string> docIDs = new List<string>();
            // if (wordsController.GetWordLocations().ContainsKey(word.ToLower()))
            // {
            //     docIDs.AddRange(wordsController.GetWordLocations()[word.ToLower()]);
            // }
            return docIDs;
        }

        private List<string> FindFinalDocs(List<string> noSignWordsDocs, List<string> plusSignWordsDocs,
            List<string> minusSignWordsDocs, List<string> minusSignWords)
        {
            List<string> signWordsDocs =
                new List<string>(FindFinalDocsOfSignWords(plusSignWordsDocs, minusSignWordsDocs, minusSignWords));
            if (noSignWordsDocs.Count != 0)
            {
                List<string> finalDocs = new List<string>();
                if (signWordsDocs.Count != 0)
                {
                    noSignWordsDocs.ForEach((doc) =>
                    {
                        if (signWordsDocs.Contains(doc))
                        {
                            finalDocs.Add(doc);
                        }
                    });
                }
                else
                {
                    finalDocs.AddRange(noSignWordsDocs);
                }

                return finalDocs;
            }
            else
            {
                return signWordsDocs;
            }
        }

        private List<string> FindFinalDocsOfSignWords(List<string> plusSignWordsDocs, List<string> minusSignWordsDocs,
            List<string> minusSignWords)
        {
            List<string> docs = new List<string>();
            if (plusSignWordsDocs.Count != 0)
            {
                docs.AddRange(plusSignWordsDocs);
                if (minusSignWords.Count != 0)
                {
                    minusSignWordsDocs.ForEach(doc => docs.Remove(doc));
                }
            }
            else
            {
                if (minusSignWords.Count != 0)
                {
                    // this.filesController.GetAllFiles().ForEach((file) => docs.Add(file));
                    minusSignWordsDocs.ForEach(doc => docs.Remove(doc));
                }
            }

            return docs;
        }
    }
}