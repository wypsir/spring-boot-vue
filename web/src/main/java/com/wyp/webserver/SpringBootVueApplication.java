package com.wyp.webserver;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wyp.common.entity.BeautifulPictures;
import com.wyp.common.entity.Picture;
import com.wyp.common.util.SpringUtils;
import com.wyp.webserver.web.CrawlerUtil;
import com.wyp.webserver.web.service.IBeautifulPicturesService;
import com.wyp.webserver.web.service.IPictureService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@MapperScan("com.wyp.dao.mapper*")
@ComponentScan(value = "com.wyp"
//        ,excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = ShiroConfig.class)}
)
@SpringBootApplication
@ServletComponentScan
public class SpringBootVueApplication
//        implements CommandLineRunner
{


    public static void main(String[] args) {
        SpringApplication.run(SpringBootVueApplication.class, args);

        System.out.println("spring boot vue start");
    }

    //springboot运行后此方法首先被调用
    //实现CommandLineRunner抽象类中的run方法
//    @Override
    public void run(String... args) throws Exception {
        //返回值
        int result = 1;
        //访问页码
        Integer page = 1;
        //启动爬虫
        System.out.println("爬虫开始工作！");
        while (result == 1) {
            result = crawler(page.toString());
            page += 1;
            if (result == 0) {
                System.out.println("爬虫运行结束！！");
            }
        }
    }


    public int crawler(String page) {
        IBeautifulPicturesService beautifulPicturesService = SpringUtils.getBean(IBeautifulPicturesService.class);

        IPictureService pictureService = SpringUtils.getBean(IPictureService.class);

        //初始化返回值
        int result = 1;
        //网站首页地址
        String homeUrl = "http://www.87g.com/";
        //接口地址
        String url = "http://www.87g.com/index.php?m=content&c=content_ajax&a=picture_page&siteid=1&catid=35&page=" + page;
        System.out.println("当前爬取第" + page + "页数据");
        //访问接口，
        JSONObject resultjson = CrawlerUtil.getReturnJson(url);
        if (resultjson != null) {
            //获取其value值
            Collection<Object> jsonList = resultjson.values();
            for (Object obj : jsonList) {
                BeautifulPictures beautifulPictures = JSON.parseObject(obj.toString(), BeautifulPictures.class);
                String Keywords = beautifulPictures.getKeywords();
                //按map条件查询。判断是否已经爬过，没有就入库
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("keywords", Keywords);
                int cont = beautifulPicturesService.selectByMap(map).size();
                if (cont == 0) {
                    //入库
                    beautifulPicturesService.insert(beautifulPictures);
                    //访问链接获取document，并保存里面的图片
                    List<Picture> listPicture = CrawlerUtil.getArticleInfo(homeUrl + beautifulPictures.getUrl(), beautifulPictures);
                    pictureService.insertBatch(listPicture);
//                    for (Picture picture : listPicture) {
//                        //入库
//                        pictureService.insert(picture);
//                    }
                } else {
                    System.out.println(homeUrl + beautifulPictures.getUrl() + "页面数据已经爬过了！！");
                }
            }
        } else {
            System.out.println("爬取到" + page + "页时没有数据了！！");
            result = 0;
        }
        return result;
    }
}
