package com.wyp.web;

import com.yaping.common.util.FileOutUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RunWith(SpringRunner.class)
//@SpringBootTest
public class SpringBootAWebApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void jsoupPic() {
		ExecutorService executor = Executors.newFixedThreadPool(5);
		Document doc = null;
		FileWriter writer = null;
		String rui="index";
		List<String> alist = new ArrayList<String>();
		//int keyword = 4;
		for(int keyword=4;keyword<100;keyword++){
			try {
				//创建页面对象
				doc = Jsoup.connect("http://pic.yesky.com/c/6_20491_"+keyword+".shtml").userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.95 Safari/537.36").timeout(10000).get();
				//根据标签和class id获取元素
				Elements div = doc.select("div.lb_box");
				//根据标签获取元素
				Elements dl = div.select("dl");
				Elements dd = div.select("dd");
				Elements pages = dd.select("a");
				for(Element e : pages){
					System.out.println(e.text());
					System.out.println(e.attr("href"));
					Document imgdoc = Jsoup.connect(e.attr("href")).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.95 Safari/537.36").timeout(10000).get();
					Elements scroll = imgdoc.select("div.effect_scroll");
					Elements li = scroll.select("li");
					Elements urls = li.select("a");
					int i=0;
					for(Element ipage : urls){
						Document imgpage = Jsoup.connect(ipage.attr("href")).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.95 Safari/537.36").timeout(10000).get();
						Elements imgediv = imgpage.select("div.l_effect_img_mid");
						Element img = imgediv.select("img").first();
						FileOutUtils fo =new FileOutUtils(img, e.text());
						fo.start();
						System.out.println(i);
						i++;
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}



}
