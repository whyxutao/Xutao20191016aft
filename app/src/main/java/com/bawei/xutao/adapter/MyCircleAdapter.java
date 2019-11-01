package com.bawei.xutao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bawei.xutao.R;
import com.bawei.xutao.bean.Cricle;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * author: 徐涛
 * data: 2019/10/24 20:20:31
 * function:
 */
public class MyCircleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Cricle> result;
    Context context;

    public MyCircleAdapter(Context context){

        this.result = new ArrayList<>();
        this.context = context;

    }

    public void addAll(List<Cricle> all){
        if (all!=null){

//            all.addAll(result);
            result.addAll(all);

        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.circle_image_item, parent, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

         if (holder instanceof MyViewHolder){

             Glide.with(context).load(result.get(position).headPic).into(((MyViewHolder) holder).imageView);

         }

    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{


        private final ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.circle_image);

        }
    }

}
