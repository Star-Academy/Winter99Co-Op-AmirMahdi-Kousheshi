using System;
using Nest;

namespace InvertedIndex.Library
{
    public class NestControllerImpl
    {
        public IElasticClient InitialiseConnectingToElasticClient()
        {
            var uri = new Uri("http://localhost:9200");
            var connectionSetting = new ConnectionSettings(uri);
            connectionSetting.EnableDebugMode();
            var client = new ElasticClient(connectionSetting);
            return client;
        }
    }
}