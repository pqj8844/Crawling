package cn.pandy.dao;

import cn.pandy.pojo.RentingInfoObj;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 项目: Crawling
 * 作者: 潘清剑(qingjian.pan@tongdun.cn)
 * 时间: 2017/3/24 下午3:20
 * 描述:
 */
public interface CrawlDao {

    public void insert(RentingInfoObj obj);

    public List<RentingInfoObj> query(Map<String,Object> map);

    public int queryCount(Map<String,Object> map);

    public Set<String> queryHashId(@Param("start") int start, @Param("end") int end);

}
