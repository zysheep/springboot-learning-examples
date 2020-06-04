package cn.zysheep.springboot.utils;

import cn.zysheep.springboot.entity.Goods;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @version v1.0.0
 * @ProjectName: springboot-examples
 * @ClassName: jsoupUtils
 * @Author: 三月三
 */
public class jsoupUtils {
    public static List<Goods> getTargetGoods(String keywords) throws IOException {
        // 获取请求 https://search.jd.com/Search?keyword=java
        // 需要联网
        String url = "https://search.jd.com/Search?keyword="+keywords;
        // 解析网页(Jsoup返回Document就是浏览器Document对象)
        Document document = Jsoup.parse(new URL(url), 30000);
        // js中可以使用的方法这里都可以使用
        Element list = document.getElementById("J_goodsList");
       // System.out.println(list.html());
        // 获取所有的li元素
        Elements li = list.getElementsByTag("li");
        // 获取元素中的内容封装到实体类Goods中
        List<Goods> goodsArrayList = new ArrayList<>();
        for (Element element : li) {
            // 图片特别多的网站,所有图片都是延迟加载
            // 图片放在source-data-lazy-img中
            String img = element.getElementsByTag("img").eq(0).attr("src");

            System.out.println(img);
            String name = element.getElementsByClass("p-name").eq(0).text();
            String price = element.getElementsByClass("p-price").eq(0).text();
            Goods goods = new Goods();
            goods.setImg(img);
            goods.setName(name);
            goods.setPrice(price);
            goodsArrayList.add(goods);
        }
        return goodsArrayList;
    }
    //测试
    public static void main(String[] args) throws IOException {
        //jsoupUtils.getTargetGoods("java");

        jsoupUtils.getTargetGoods("vue").forEach(System.out::println);

    }
}
