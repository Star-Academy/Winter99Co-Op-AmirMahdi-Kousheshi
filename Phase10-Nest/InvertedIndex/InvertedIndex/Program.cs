using System;
using System.Threading.Tasks;
using InvertedIndex.Library;

namespace Project
{
    class Program
    {
        static async Task Main(string[] args)
        {
            FilesDatabaseControllerImpl docsController = new FilesDatabaseControllerImpl();
            Console.WriteLine("Enter the directory name:");
            docsController.InitialiseReadAndSavingFiles(Console.ReadLine());
            var wordsController = new WordsDatabaseControllerImpl();
            wordsController.InitialiseUpdateDatabase(docsController.GetAllDocs());

            var nestController = new NestControllerImpl();
            var client = nestController.InitialiseConnectingToElasticClient();
            nestController.SaveWordsInProgramDatabaseToElastic(wordsController.AllWords);

            // foreach (var word in wordsController.AllWords)
            // {
            //     var response = await client.IndexAsync<Word>(word, descriptor => descriptor
            //         .Index("inverted-index")).ConfigureAwait(false);
            // }

            // foreach (var word in wordsController.AllWords)
            // {
            //     Console.WriteLine(word.Name);
            //     Console.WriteLine(word.Docs.Count);
            // }
        }
    }
}