package cn.zysheep.springboot.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;

/**
 * @version v1.0.0
 * @ProjectName: springboot-learning-examples
 * @ClassName: ElasticSearchConfig
 * @Author: 三月三
 */
public class ElasticSearchConfig {
    @Bean
    public RestHighLevelClient restHighLevelClient() {
        RestHighLevelClient restClient = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("172.16.0.192", 9200, "http")));
        return restClient;
    }
}