package com.bawei.xutao.frag.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.xutao.R;
import com.bawei.xutao.frag.bean.Dshop;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * author: 徐涛
 * data: 2019/10/24 14:14:34
 * function:
 */
public class MyRecAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Dshop> orderList;

    Context context;

    public MyRecAdapter(Context context){
        this.orderList = new ArrayList<>();
        this.context = context;
    }

    public void addAll(List<Dshop> all){
        if (all!=null){

            orderList.addAll(all);

        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.order_item, parent, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof MyViewHolder){

            String[] split1 = orderList.get(position).detailList.get(position).commodityPic.split(",");
            Glide.with(context).load(split1[position]).into(((MyViewHolder) holder).shoes);
            ((MyViewHolder) holder).orderId.setText(orderList.get(position).orderId);
            ((MyViewHolder) holder).commodityName.setText(orderList.get(position).detailList.get(position).commodityName);
            ((MyViewHolder) holder).commodityPrice.setText(""+orderList.get(position).detailList.get(position).commodityPrice);

        }

    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{


        private final TextView orderId,commodityName,commodityPrice;
        private final ImageView shoes;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            orderId = itemView.findViewById(R.id.orderId);
            shoes = itemView.findViewById(R.id.shoes);

            commodityName = itemView.findViewById(R.id.commodityName);
            commodityPrice = itemView.findViewById(R.id.commodityPrice);


        }
    }

}
