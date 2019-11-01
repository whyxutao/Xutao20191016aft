package com.bawei.xutao.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.xutao.R;
import com.bawei.xutao.bean.Commodity;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @describe(描述)：com.bawei.goshopdemo.model.adapter
 * @data（日期）: 14:2019/10/17
 * @time（时间）: 14:13
 * @author（作者）: 徐涛
 **/
public class TopAdapter extends RecyclerView.Adapter<TopAdapter.TopViewHolder> {

    List<Commodity> list = new ArrayList<>();

    public void xutaotianjiashuju(List<Commodity> data) {
        if (data != null)
            this.list.addAll(data);
    }

    @NonNull
    @Override
    public TopViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.top_list, viewGroup, false);
        return new TopViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull TopViewHolder holder, int i) {
        Commodity commodity = list.get(i);
        Glide.with(holder.itemView.getContext()).load(commodity.masterPic).into(holder.iv);
        holder.tv1.setText(commodity.commodityName);
        holder.tv2.setText("￥" + commodity.price);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class TopViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tv1, tv2;

        public TopViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.top_image);
            tv1 = itemView.findViewById(R.id.top_textOne);
            tv2 = itemView.findViewById(R.id.top_textTwo);
        }
    }
}


