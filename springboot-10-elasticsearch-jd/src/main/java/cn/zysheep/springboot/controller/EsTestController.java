package cn.zysheep.springboot.controller;

import cn.zysheep.springboot.service.BulkAndSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 * @version v1.0.0
 * @ProjectName: springboot-examples
 * @ClassName: EsTestController
 * @Author: 三月三
 */
@RestController
public class EsTestController {
    @Autowired
    private BulkAndSearchService bulkService;
    //去京东搜索页面抓取关键字数据信息，存入ES中
    @GetMapping("/parse/{keyword}")
    public Boolean BulkIntoEs(@PathVariable("keyword") String keyword) throws IOException {
        return bulkService.BulkGoods(keyword);
    }
    //关键及搜索
    @GetMapping("/search/{keyword}/{pageNum}/{pageSize}")
    public List<Map<String, Object>> SearchGoods(
            @PathVariable("keyword") String keyword,
            @PathVariable("pageNum") int pageNum,
            @PathVariable("pageSize") int pageSize
    ) throws Exception {
        return bulkService.SearchGoods(keyword,pageNum,pageSize);
    }
}
