using System;
using InvertedIndex.Library;

namespace Project
{
    class Program
    {
        static void Main(string[] args)
        {
            FilesDatabaseControllerImpl controller = new FilesDatabaseControllerImpl();
            Console.WriteLine("Enter the directory name:");
            controller.InitialiseReadAndSavingFiles(Console.ReadLine());
            //
            // while (true)
            // {
            //     Console.WriteLine("search input:");
            //     foreach (var doc in search.InitialiseSearch(Console.ReadLine()))
            //     {
            //         Console.WriteLine(doc);
            //     }
            // }
        }
    }
}