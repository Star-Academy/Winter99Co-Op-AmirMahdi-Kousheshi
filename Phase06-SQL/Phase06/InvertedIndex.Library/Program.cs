using System;
using InvertedIndex.Library;
namespace InvertedIndex
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Hello World!");
            var databaseController = new DatabaseController();
            databaseController.Database.EnsureCreated();
            
           
        }
    }
}
