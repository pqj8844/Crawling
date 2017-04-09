package cn.pandy.pojo;

import cn.pandy.common.Utils;

import java.io.Serializable;

public class UpdateTime implements Serializable {
    private String updateTime = Utils.timeTrans(System.currentTimeMillis());

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}