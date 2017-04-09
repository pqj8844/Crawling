package cn.pandy.pojo;

import java.io.Serializable;

/**
 * 项目: Crawling
 * 作者: 潘清剑(qingjian.pan@tongdun.cn)
 * 时间: 2017/3/24 上午10:37
 * 描述:
 */
public class RentingInfoObj extends UpdateTime implements Serializable{

    private String hashId;
    //标题名
    private String titleName;
    //链接
    private String href;
    //房屋大小
    private String roomSize;
    //地址
    private String address;
    //联系人
    private String geren;
    //发布时间
    private String sendTime;
    //钱
    private String money;

    public String getHashId() {
        return hashId;
    }

    public void setHashId(String hashId) {
        this.hashId = hashId;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(String roomSize) {
        this.roomSize = roomSize;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGeren() {
        return geren;
    }

    public void setGeren(String geren) {
        this.geren = geren;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
