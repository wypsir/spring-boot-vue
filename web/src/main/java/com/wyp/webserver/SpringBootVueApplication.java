package com.wyp.webserver;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wyp.common.entity.BeautifulPictures;
import com.wyp.common.entity.Picture;
import com.wyp.common.util.Log;
import com.wyp.common.util.SpringUtils;
import com.wyp.webserver.web.CrawlerUtil;
import com.wyp.webserver.web.service.IBeautifulPicturesService;
import com.wyp.webserver.web.service.IPictureService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
//@MapperScan("com.wyp.dao.mapper*")
@ComponentScan("com.wyp")
public class SpringBootVueApplication
        implements CommandLineRunner
{


    //资源下载之后，保存在本地的文件路径
    private static String downloadFilePath = "D://downloadImage//";


    public static void main(String[] args) {
        SpringApplication.run(SpringBootVueApplication.class, args);

        System.out.println("spring boot vue start");
    }

    //springboot运行后此方法首先被调用
    //实现CommandLineRunner抽象类中的run方法
    @Override
    public void run(String... args) throws Exception {
        //返回值
        int result = 1;
        //访问页码
        Integer page = 1;
        //启动爬虫
        System.out.println("爬虫开始工作！");
        while(result==1){
            result = crawler(page.toString());
            page+=1;
            if(result==0){
                System.out.println("爬虫运行结束！！");
            }
        }
    }



    public int crawler(String page){
         IBeautifulPicturesService beautifulPicturesService = SpringUtils.getBean(IBeautifulPicturesService.class);

         IPictureService pictureService = SpringUtils.getBean(IPictureService.class);

        //初始化返回值
        int result = 1;
        //网站首页地址
        String homeUrl = "http://www.87g.com/";
        //接口地址
        String url = "http://www.87g.com/index.php?m=content&c=content_ajax&a=picture_page&siteid=1&catid=35&page="+page;
        System.out.println("当前爬取第"+ page +"页数据");
        //访问接口，
        JSONObject resultjson = CrawlerUtil.getReturnJson(url);
        if(resultjson!=null){
            //获取其value值
            Collection<Object> jsonList = resultjson.values();
            for(Object obj : jsonList){
                BeautifulPictures beautifulPictures = JSON.parseObject(obj.toString(), BeautifulPictures.class);
                String Keywords = beautifulPictures.getKeywords();
                //按map条件查询。判断是否已经爬过，没有就入库
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("keywords", Keywords);
                int cont = beautifulPicturesService.selectByMap(map).size();
                if(cont==0){
                    //入库
                    beautifulPicturesService.insert(beautifulPictures);
                    //访问链接获取document，并保存里面的图片
                    List<Picture> listPicture = CrawlerUtil.getArticleInfo(homeUrl+beautifulPictures.getUrl(),beautifulPictures);
                    for(Picture picture : listPicture){
                        //入库
                        pictureService.insert(picture);

                        downImages(downloadFilePath,picture.getUrl());
                    }
                }else{
                    System.out.println(homeUrl+beautifulPictures.getUrl()+"页面数据已经爬过了！！");
                }
            }
        }else{
            System.out.println("爬取到"+page+"页时没有数据了！！");
            result = 0;
        }
        return result;
    }


    /**
     *
     * 根据图片的外网地址下载图片到本地硬盘的filePath
     * @param filePath 本地保存图片的文件路径
     * @param imgUrl 图片的外网地址
     * @throws UnsupportedEncodingException
     *
     */
    public static void downImages(String filePath,String imgUrl)  {

        try {
        //图片url中的前面部分：例如"http://images.csdn.net/"
        String beforeUrl = imgUrl.substring(0,imgUrl.lastIndexOf("/")+1);
        //图片url中的后面部分：例如“20150529/PP6A7429_副本1.jpg”
        String fileName = imgUrl.substring(imgUrl.lastIndexOf("/")+1);
        //编码之后的fileName，空格会变成字符"+"
        String newFileName = URLEncoder.encode(fileName, "UTF-8");
        //把编码之后的fileName中的字符"+"，替换为UTF-8中的空格表示："%20"
        newFileName = newFileName.replaceAll("\\+", "\\%20");
        //编码之后的url
        imgUrl = beforeUrl + newFileName;


            //创建文件目录
            File files = new File(filePath);
            if (!files.exists()) {
                files.mkdirs();
            }
            //获取下载地址
            URL url = new URL(imgUrl);
            //链接网络地址
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            //获取链接的输出流
            InputStream is = connection.getInputStream();
            //创建文件，fileName为编码之前的文件名
            File file = new File(filePath + fileName);
            //根据输入流写入文件
            FileOutputStream out = new FileOutputStream(file);
            int i = 0;
            while((i = is.read()) != -1){
                out.write(i);
            }
            out.close();
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.error("downloadImage error");
        }
    }
}
