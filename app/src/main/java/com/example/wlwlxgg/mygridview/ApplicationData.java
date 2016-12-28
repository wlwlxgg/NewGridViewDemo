package com.example.wlwlxgg.mygridview;

import java.io.Serializable;

/**
 * Created by wlwlxgg on 2016/12/27.
 */

public class ApplicationData implements Serializable {
    /*哪一类*/
    String type;
    /*tag = 1,非管理， tag = 2,管理*/
    int tag;
    /*flag = 1,常用服务类点击web事件
    * flag = 2,其他类点击web事件
    * flag = 3,常用服务类删除事件
    * flag = 4,常用服务类增加事件*/
    int flag;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}