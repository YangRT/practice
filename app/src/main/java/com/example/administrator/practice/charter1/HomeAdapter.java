package com.example.administrator.practice.charter1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.practice.R;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> implements View.OnClickListener,View.OnLongClickListener{
    private List<String> mList;
    private Context mContext;
    private onItemClickListener mListener;

    HomeAdapter(List<String> list, Context context){
        mList = list;
        mContext = context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.charter1_recyclerview_item,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        return viewHolder;
    }
    public void removeData(int position){
        mList.remove(position);
        notifyItemRemoved(position);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.itemView.setTag(position);
        holder.textView.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onClick(View v) {
        if(mListener != null){
            mListener.onItemClick(v,(int)v.getTag());
        }

    }

    @Override
    public boolean onLongClick(View v) {
        if (mListener != null){
            mListener.onItemLongClick(v,(int)v.getTag());
        }
        return true;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.charter1_item_text);
        }
    }

    public void setItemClickListener(onItemClickListener listener){
        mListener = listener;
    }

    public interface onItemClickListener{
        void onItemClick(View view,int position);
        void onItemLongClick(View view,int position);
    }
}
