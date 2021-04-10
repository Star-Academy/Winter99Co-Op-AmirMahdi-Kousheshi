using System;
using InvertedIndex.Library;

namespace Project
{
    class Program
    {
        static void Main(string[] args)
        {
            FilesDatabaseControllerImpl docsController = new FilesDatabaseControllerImpl();
            Console.WriteLine("Enter the directory name:");
            docsController.InitialiseReadAndSavingFiles(Console.ReadLine());
            var wordsController = new WordsDatabaseControllerImpl();
            wordsController.InitialiseUpdateDatabase(docsController.GetAllDocs());
            foreach (var word in wordsController.AllWords)
            {
                Console.WriteLine(word.Name);
                Console.WriteLine(word.Docs.Count);
            }
        }
    }
}