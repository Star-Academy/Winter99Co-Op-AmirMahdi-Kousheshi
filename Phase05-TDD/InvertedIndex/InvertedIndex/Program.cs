using System;
using InvertedIndex.Library;

namespace Project
{
    class Program
    {
        static void Main(string[] args)
        {

            SearchController search = new SearchController(new FilesDatabaseController(), "C://Users/Amkam/Desktop/sample/");

            while (true)
            {
                Console.WriteLine("search input:");
                foreach (var doc in search.InitialiseSearch(Console.ReadLine()))
                {
                    Console.WriteLine(doc);
                }
            }
        }
    }
}
