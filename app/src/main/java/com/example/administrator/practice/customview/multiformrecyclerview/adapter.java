package com.example.administrator.practice.customview.multiformrecyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.practice.R;
import com.example.administrator.practice.charter9.BindView;

public class adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    adapter(Context context){
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class SmallHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView name;
        public SmallHolder(View itemView) {
            super(itemView);

        }
    }

    class SpecialHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView  title;
        public SpecialHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    class NormalHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView title;
        TextView desc;

        public NormalHolder(@NonNull View itemView) {
            super(itemView);
        }
    }




}
