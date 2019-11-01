package com.bawei.xutao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.xutao.R;
import com.bawei.xutao.bean.Commodity;
import com.bumptech.glide.Glide;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @describe(描述)：com.bawei.goshopdemo.model.adapter
 * @data（日期）: 14:2019/10/17
 * @time（时间）: 14:13
 * @author（作者）: 徐涛
 **/
public class LastAdapter extends RecyclerView.Adapter<LastAdapter.TopViewHolder> {

    List<Commodity> list;
    Context context;

    public LastAdapter(List<Commodity> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public TopViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.last_list, viewGroup,false);
        return new TopViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull TopViewHolder holder, int i) {
        Commodity commodity = list.get(i);
        Glide.with(context).load(commodity.masterPic).into(holder.iv);
        holder.tv1.setText(commodity.commodityName);
        holder.tv2.setText("￥"+commodity.price);
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
            iv = itemView.findViewById(R.id.last_image);
            tv1 = itemView.findViewById(R.id.last_textOne);
            tv2 = itemView.findViewById(R.id.last_textTwo);
        }
    }
}


