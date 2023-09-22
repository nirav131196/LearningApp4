package com.example.loginapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter2 extends RecyclerView.Adapter<RecyclerAdapter2.MyViewHolder> {

    // creating variables for context and list.
    private Context mContext;
    private List<String> mDatas;

    // creating constructor.
    public RecyclerAdapter2(Context context, List<String> datas) {
        mContext = context;
        mDatas = datas;
    }

    @Override
    public RecyclerAdapter2.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating layout on below line.
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_main, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter2.MyViewHolder holder, int position) {
        // setting data to text view on below line.
        holder.tv.setText(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        // returning the size of array list.
        return mDatas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // creating variables for text view.
        TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            // initializing the text view.
            tv = (TextView) itemView.findViewById(R.id.tv_num);
        }
    }
}
