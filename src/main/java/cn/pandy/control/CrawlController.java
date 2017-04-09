package cn.pandy.control;

import cn.pandy.dao.CrawlDao;
import cn.pandy.pojo.RentingInfoObj;
import cn.pandy.service.CrawlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目: Crawling
 * 作者: 潘清剑(qingjian.pan@tongdun.cn)
 * 时间: 2017/3/24 下午5:03
 * 描述:
 */
@Controller
public class CrawlController extends BaseController {

    @Autowired
    private CrawlService crawlService;

    @Autowired
    private CrawlDao crawlDao;

    @RequestMapping("index.htm")
    public String index() {
        return "index";
    }

    @RequestMapping("queryContent.htm")
    public String queryContent(Integer page, boolean sendTimeOrder, boolean moneyOrder, String address, String moneyStart,
                               String moneyEnd, String sendTimeStart, String sendTimeEnd, Model model) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("address", address);
        map.put("moneyStart", moneyStart);
        map.put("moneyEnd", moneyEnd);
        map.put("sendTimeStart", sendTimeStart);
        map.put("sendTimeEnd", sendTimeEnd);

        StringBuffer orderSql = new StringBuffer();
        if (moneyOrder || sendTimeOrder) {
            orderSql.append(" order by ");
            if (moneyOrder) {
                orderSql.append("money,");
            }
            if (sendTimeOrder) {
                orderSql.append("sendTime,");
            }
            orderSql.deleteCharAt(orderSql.lastIndexOf(","));
            orderSql.append(" desc ");
        }
        map.put("orderSql",orderSql.toString());
        page = page != null && page > 0 ? page : 1;
        //每页50条
        int pageCount = 50;
        //总条数
        int count = crawlDao.queryCount(map);
        //总页数
        int pages = count % pageCount == 0 ? count / pageCount : count / pageCount + 1;
        int start = (page - 1) * pageCount;
        int end = start + pageCount;
        map.put("start", start);
        map.put("end", end);
        List<RentingInfoObj> list = crawlDao.query(map);
        model.addAttribute("count", count);
        model.addAttribute("pages", pages);
        model.addAttribute("page", page);
        model.addAttribute("list", list);
        return "queryContent";
    }


    @RequestMapping("run.htm")
    public void run(int page, HttpServletResponse response) throws IOException {
        crawlService.save(page);
        print(response, page + "页,执行完毕!");
    }
}
