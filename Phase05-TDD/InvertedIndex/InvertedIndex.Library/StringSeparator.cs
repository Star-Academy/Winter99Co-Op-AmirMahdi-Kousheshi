using System;
using System.Collections.Generic;
using System.Text.RegularExpressions;

namespace InvertedIndex.Library
{
    public class StringSeparator
    {
        public List<string> SeparateWordsBySign(string searchInput, string regex)
        {
            string[] splitedWords = searchInput.Split(' ');
            List<string> strings = new List<string>();
            foreach (var word in splitedWords)
            {
                if (regex.Equals(""))
                {
                    if (!word.StartsWith("+") && !word.StartsWith("-"))
                    {
                        strings.Add(word);
                    }
                }
                else if (word.StartsWith(regex))
                {
                    strings.Add(word.Replace(regex, ""));
                }
            }

            return strings;
        }

        public string NormalizeWord(string word)
        {
            return Regex.Replace(word, "\\W+", "");
        }
    }
}
