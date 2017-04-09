package cn.pandy.service;

import cn.pandy.common.Utils;
import cn.pandy.dao.CrawlDao;
import cn.pandy.pojo.RentingInfoObj;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**
 * 项目: Crawling
 * 作者: 潘清剑(qingjian.pan@tongdun.cn)
 * 时间: 2017/3/24 上午10:19
 * 描述:
 */
@Service
public class CrawlService {

    private String baseUrl = "http://hz.58.com/chuzu/0/f1/pn{page}/?utm_source=market&spm=b-31580022738699-me-f-824.bdpz_biaoti&PGTID=0d3090a7-0004-fad0-4cde-2568a7028eb8&ClickID=4";


    private Set<String> hashIdSet = new HashSet<String>();

    @Autowired
    private CrawlDao crawlDao;

    //初始化
    @PostConstruct
    public void init() {
        int count = crawlDao.queryCount(new HashMap<String, Object>());
        int start = 0;
        int end = 1000;
        do {
            Set<String> set = crawlDao.queryHashId(start, end);
            hashIdSet.addAll(set);
            start = end;
            end += 1000;
        } while (count > end);
        //初始化的时候,加载10页的数据进来
        for(int i=1;i<=10;i++){
            save(i);
        }
    }

    private List<RentingInfoObj> processCrawl(int page) throws IOException {
        page = page < 1 ? 1 : page;
        String url = baseUrl.replace("{page}", page + "");
        List<RentingInfoObj> list = new ArrayList<RentingInfoObj>();
        Document parse = Jsoup.parse(new URL(url), 10000);
        Elements elements = parse.select("li[sortid]");
        for (Element element : elements) {
            RentingInfoObj obj = new RentingInfoObj();
            Elements els = element.select("div[class=des]");
            for (Element el : els) {
                Elements title = el.select("h2 a");
                //标题名
                String titleName = title.text();
                obj.setTitleName(titleName);
                //链接
                String href = title.attr("href");
                obj.setHref(href);
                //房屋大小
                String roomSize = el.select("p.room").text();
                obj.setRoomSize(roomSize);
                //地址
                String address = el.select("p.add").text();
                obj.setAddress(address);
                //联系人
                String geren = el.select("p.geren").text();
                obj.setGeren(geren);
            }
            //发布时间
            String sendTime = element.select("div.listliright div.sendTime").text();
            obj.setSendTime(Utils.convertSendTime(sendTime));
            //钱
            String money = element.select("div.listliright div.money").text();
            if(money.contains("元/月")){
                money = money.substring(0,money.indexOf("元/月"));
            }
            obj.setMoney(money);
            obj.setHashId(Utils.md5(obj.getTitleName()));
            if (!hashIdSet.contains(obj.getHashId())) {
                list.add(obj);
                hashIdSet.add(obj.getHashId());
            }
        }
        return list;
    }

    public void save(int page) {
        System.out.println("执行了save..."+page);
        List<RentingInfoObj> list = null;
        try {
            list = processCrawl(page);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (RentingInfoObj obj : list) {
            try {
                crawlDao.insert(obj);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void saveForQuartz(){
        System.out.println("执行了定时任务...");
        save(1);
    }

}
