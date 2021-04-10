using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using Nest;

namespace InvertedIndex.Library
{
    public class NestControllerImpl
    {
        private ElasticClient _client;

        public IElasticClient InitialiseConnectingToElasticClient()
        {
            var uri = new Uri("http://localhost:9200");
            var connectionSetting = new ConnectionSettings(uri);
            connectionSetting.EnableDebugMode();
            var client = new ElasticClient(connectionSetting);
            _client = client;
            return client;
        }

        public async Task SaveWordsInProgramDatabaseToElastic(List<Word> words)
        {
            foreach (var word in words)
            {
                var response = await _client.IndexAsync<Word>(word, descriptor => descriptor
                    .Index("inverted-index")).ConfigureAwait(false);
            }
        }
    }
}