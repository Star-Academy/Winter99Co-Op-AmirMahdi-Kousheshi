using System.Collections.Generic;

namespace InvertedIndex.Library
{
    public class Word
    {
        public string Name { get; }
        public List<string> Docs { get; }

        public Word(string name)
        {
            this.Name = name;
            this.Docs = new List<string>();
        }

        public void AddDoc(string docName)
        {
            this.Docs.Add(docName);
        }
    }
}