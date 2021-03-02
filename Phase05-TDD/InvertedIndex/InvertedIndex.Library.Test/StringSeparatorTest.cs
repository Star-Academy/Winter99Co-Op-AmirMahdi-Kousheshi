using System;
using System.Collections.Generic;
using Xunit;

namespace InvertedIndex.Library.Test
{
    public class StringSeparatorTest
    {
        [Fact]
        public void Test_SearchInput_By_Regex()
        {
            var separator = new StringSeparator();
            string searchInput = "ok no +heh -bye";
            string regex = "+";
            List<string> noSignWord = new List<string>(separator.SeparateWordsBySign(searchInput, regex));
            List<string> expectedValue = new List<string>() { "heh" };
            Assert.Equal(expectedValue, noSignWord);
        }

        [Fact]
        public void Test_Normalize_Word()
        {
            var separator = new StringSeparator();
            string test = "o!k";
            string expectedValue = "ok";
            Assert.Equal(expectedValue, separator.NormalizeWord(test));
        }
    }
}
