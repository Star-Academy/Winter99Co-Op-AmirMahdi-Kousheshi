using System;
using System.Collections.Generic;
using System.Text;
using Xunit;

namespace InvertedIndex.Library.Test
{
    public class InvertedIndexTest
    {
        [Fact]
        public void Test_InvertedIndex()
        {

            string path = "..\\Source\\sample\\";
            var search = new SearchController(new FilesDatabaseController(), path);


            Assert.Equal(new List<string>() { path + "1.txt" }, search.InitialiseSearch("hello"));
        }
    }
}
