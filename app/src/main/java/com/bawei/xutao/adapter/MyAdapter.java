package com.bawei.xutao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;


import com.bawei.xutao.R;
import com.bawei.xutao.bean.Goods;
import com.bawei.xutao.bean.Shop;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 *@describe(描述)：MyAdapter
 *@data（日期）: 2019/10/16
 *@time（时间）: 21:06
 *@author（作者）: 徐涛
 **/
public class MyAdapter extends BaseExpandableListAdapter implements View.OnClickListener {

    private List<Shop> list;
    private Context context;

    public MyAdapter(Context context) {
        this.list = new ArrayList<>();
        this.context = context;
    }

    public void addAll(List<Shop> data){
        if (data != null) {
            list.addAll(data);
        }
    }

    public void addItem(Shop shop){
        list.add(shop);
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list.get(groupPosition).shoppingCartList.size();
    }
    @Override
    public Object getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list.get(groupPosition).shoppingCartList.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ShopHolder shopHolder;
        if (convertView==null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_shop, parent, false);
            shopHolder = new ShopHolder();
            shopHolder.shopCheck = convertView.findViewById(R.id.shop_text);
            convertView.setTag(shopHolder);
        }else{
            shopHolder = (ShopHolder) convertView.getTag();
        }

        Shop shop = list.get(groupPosition);
        shopHolder.shopCheck.setText(shop.categoryName);
        shopHolder.shopCheck.setChecked(shop.check);
        shopHolder.shopCheck.setTag(groupPosition);
        shopHolder.shopCheck.setOnClickListener(this);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        GoodsHolder goodsHolder;
        if (convertView==null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_goods, parent, false);
            goodsHolder = new GoodsHolder();
            goodsHolder.name = convertView.findViewById(R.id.goods_name);
            goodsHolder.check = convertView.findViewById(R.id.goods_box);
            goodsHolder.image = convertView.findViewById(R.id.goods_image);
            goodsHolder.price = convertView.findViewById(R.id.goods_price);
            convertView.setTag(goodsHolder);
        }else{
            goodsHolder = (GoodsHolder) convertView.getTag();
        }

        Goods goods = list.get(groupPosition).shoppingCartList.get(childPosition);
        goodsHolder.name.setText(goods.commodityName);
        goodsHolder.price.setText("￥"+String.valueOf(goods.price));
        Glide.with(context).load(goods.pic).into(goodsHolder.image);
        goodsHolder.check.setChecked(goods.check);
        goodsHolder.check.setTag(goods);
        goodsHolder.check.setOnClickListener(this);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.shop_text:
                int groupPosition = (int) v.getTag();
                CheckBox shopBox = (CheckBox) v;
                Shop shop = list.get(groupPosition);
                shop.check = shopBox.isChecked();
                for (int i = 0; i < shop.shoppingCartList.size(); i++) {
                    Goods goods = shop.shoppingCartList.get(i);
                    goods.check = shopBox.isChecked();
                }
                notifyDataSetChanged();
                calculatePrice();
                break;
            case R.id.goods_box:
                Goods goods = (Goods) v.getTag();
                CheckBox goodsBox = (CheckBox) v;
                goods.check = goodsBox.isChecked();
                goodsToShop();
                calculatePrice();
                break;
        }
    }


    public void allCheck(boolean isAllCheck){
        for (int i = 0; i < list.size(); i++) {
            Shop shop = list.get(i);
            shop.check = isAllCheck;
            for (int j = 0; j < shop.shoppingCartList.size(); j++) {
                shop.shoppingCartList.get(j).check = isAllCheck;
            }
        }
        notifyDataSetChanged();
        calculatePrice();
    }

    public void goodsToShop(){
        for (int i = 0; i < list.size(); i++) {
            Shop shop = list.get(i);
            boolean isCheck = true;
            for (int j = 0; j < shop.shoppingCartList.size(); j++) {
                Goods goods = shop.shoppingCartList.get(j);
                isCheck = isCheck&&goods.check;
            }
            shop.check = isCheck;
        }
        notifyDataSetChanged();
    }

    public void calculatePrice(){
        double sum = 0;
        boolean ac = true;
        for (int i = 0; i < list.size(); i++) {
            Shop shop = list.get(i);
            ac = ac&&shop.check;
            for (int j = 0; j <shop.shoppingCartList.size() ; j++) {
                Goods goods = shop.shoppingCartList.get(j);
                if (goods.check)
                    sum+=(goods.price*goods.count);
            }
        }
        if (priceLisenter!=null)
            priceLisenter.price(sum,ac);
    }

    private PriceLisenter priceLisenter;

    public void setPriceLisenter(PriceLisenter priceLisenter) {
        this.priceLisenter = priceLisenter;
    }

    public interface PriceLisenter{
        void price(double price, boolean ac);
    }

    class ShopHolder{
        CheckBox shopCheck;
    }
    class GoodsHolder{
        CheckBox check;
        ImageView image;
        TextView price;
        TextView name;
    }
}
