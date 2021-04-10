using System;
using System.Collections.Generic;
using System.Text;
using Xunit;

namespace InvertedIndex.Library.Test
{
    public class WordsDatabaseControllerTest
    {
        [Fact]
        public void Test_Words_Normalize_From_All_Text_In_Doc()
        {
            var controlelr = new WordsDatabaseController(new FilesDatabaseController());
            List<string> expectedValue = new List<string>() { "lskgf", "n2idfhn", "fnj3kdjg", "tht" };
            string text = "lskgf n2_idfhn    fnj3@#kdjg th't";
            Assert.Equal(expectedValue, controlelr.GetWordsNormalizeFromAllTextInDoc(text));
        }

        [Fact]
        public void Test_Get_Word_Locations()
        {
            var controlelr = new WordsDatabaseController(new FilesDatabaseController());
            string text = "lskgf n2_idfhn    fnj3@#kdjg th't";
            Dictionary<string, List<string>> expectedValue = new Dictionary<string, List<string>>() { { "lskgf", new List<string>() { "1.txt" } }, { "n2idfhn", new List<string>() { "1.txt" } }, { "fnj3kdjg", new List<string>() { "1.txt" } }, { "tht", new List<string>() { "1.txt" } } };
            controlelr.AddNormalizeWordsInADocToDictionary(controlelr.GetWordsNormalizeFromAllTextInDoc(text), "1.txt");

            Assert.Equal(expectedValue, controlelr.GetWordLocations());
        }
    }
}
