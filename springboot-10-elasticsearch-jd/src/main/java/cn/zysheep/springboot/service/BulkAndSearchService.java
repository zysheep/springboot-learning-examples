package cn.zysheep.springboot.service;

import cn.zysheep.springboot.entity.Goods;
import cn.zysheep.springboot.utils.jsoupUtils;
import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @version v1.0.0
 * @ProjectName: springboot-examples
 * @ClassName: BulkAndSearchService
 * @Author: 三月三
 */
@Service
public class BulkAndSearchService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    //1.解析数据放入es中
    public Boolean BulkGoods(String keywords) throws IOException {
        List<Goods> goodsList = jsoupUtils.getTargetGoods(keywords);
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("10s");
        //es页面查询
        //http://172.16.0.192:9200/jd_goods/_doc/_search
        for (int i = 0; i < goodsList.size(); i++) {
            bulkRequest.add(new IndexRequest("jd_goods").source(JSON.toJSONString(goodsList.get(i)), XContentType.JSON));
        }
        BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        return !bulk.hasFailures();
    }

    //2.搜索
    public List<Map<String, Object>> SearchGoods(String keywords,int pageNum,int pageSize) throws Exception{
        if (pageNum<=0){
            pageNum=1;
        }
        SearchRequest jd_goods = new SearchRequest("jd_goods");
        //构建搜索条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        //高亮
        highlightBuilder.field("name");
        highlightBuilder.requireFieldMatch(false);
        highlightBuilder.preTags("<span style='color:red'>");
        highlightBuilder.postTags("</span>");
        searchSourceBuilder.highlighter(highlightBuilder);

        //分页
        searchSourceBuilder.from(pageNum);
        searchSourceBuilder.size(pageSize);
        TermQueryBuilder queryBuilder = QueryBuilders.termQuery("name", keywords);
        searchSourceBuilder.query(queryBuilder);
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        jd_goods.source(searchSourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(jd_goods, RequestOptions.DEFAULT);
        ArrayList<Map<String, Object>> goodlist = new ArrayList<>();
        for (SearchHit hit : searchResponse.getHits().getHits()) {
            //原来不高亮的查询结果
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            HighlightField name = highlightFields.get("name");
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            //解析出高亮字段，将原来不高亮的字段替换掉
            if (name!=null){
                Text[] fragments = name.fragments();
                String title="";
                for (Text fragment : fragments) {
                    title+=fragment;
                }
                //替换
                sourceAsMap.put("name",title);
            }
            goodlist.add(sourceAsMap);

        }
        return goodlist;
    }
}
