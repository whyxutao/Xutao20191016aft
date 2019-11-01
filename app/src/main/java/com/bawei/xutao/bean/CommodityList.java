package com.bawei.xutao.bean;

import java.util.List;

/**
 * @describe(描述)：com.bawei.goshopdemo.model.bean
 * @data（日期）: 14:2019/10/17
 * @time（时间）: 14:25
 * @author（作者）: 盖磊
 **/
public class CommodityList {

    public List<Commodity> commodityList;
    public String name;
    public int id;


    public List<Commodity> getCommodityList() {
        return commodityList;
    }

    public void setCommodityList(List<Commodity> commodityList) {
        this.commodityList = commodityList;
    }
}
