using System;
using System.Collections.Generic;
using Xunit;
using System.Text;

namespace InvertedIndex.Library.Test
{
    public class FilesDatabaseControllerTest
    {

        [Fact]
        public void Test_Get_All_Files()
        {
            var saver = new FilesDatabaseController();
            string path = "..\\Source\\sample\\";
            saver.ReadAllFiles(path);

            List<string> files = new List<string>() { path+"1.txt",path+"2.txt" };
            Assert.Equal(files, saver.GetAllFiles());
        }

    }
}
