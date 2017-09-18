package com.wyp.webserver.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wyp.common.entity.BeautifulPictures;
import com.wyp.common.entity.Picture;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @aurhor wangyaping 【yapingw@dingtalk.com】
 * @date 2017/9/18 16:24 爬虫程序
 * @description
 */
public class CrawlerUtil {

    public static JSONObject getReturnJson(String url) {
        JSONObject json = null;
        try {
            Connection connect = Jsoup.connect(url);
            Connection.Response response = connect.execute();
            String body = response.body();
            json = JSON.parseObject(body);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;
    }

    public static List<Picture> getArticleInfo(String url, BeautifulPictures beautifulPictures) {
        if (Objects.isNull(beautifulPictures)) {

            return Collections.emptyList();
        }
        List<Picture> list = new ArrayList<>();
        try {
            Connection connect = Jsoup.connect(url);
            Document doc = connect.get();
            Elements elements = doc.select("img[src]");
            for (Element element : elements) {
                String src = element.attr("src");
                if (src.indexOf("upload") > -1) {
                    Picture picture = new Picture();
                    picture.setPicturesId(beautifulPictures.getId());
                    picture.setUrl(src);
                    list.add(picture);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }

        return list;

    }
}
