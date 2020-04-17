package com.example.leaderboard;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;

public class Adapter extends RecyclerView.Adapter<Adapter.mViewHolder> {
    private ArrayList<items> mmList;


    public static class mViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;
        public TextView mTextView3;
        public CardView CVid;
        public RelativeLayout RLid;

//set items and colors

        public mViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mTextView1 = itemView.findViewById(R.id.textView1);
            mTextView2 = itemView.findViewById(R.id.textView2);
            mTextView3 = itemView.findViewById(R.id.textView3);
            mTextView1.setTextColor(Color.parseColor("#8a2be2"));
            mTextView2.setTextColor(Color.parseColor("#8a2be2"));
            mTextView3.setTextColor(Color.parseColor("BLACK"));

            CVid = itemView.findViewById(R.id.CV);
            CVid.setBackgroundColor(Color.parseColor("WHITE"));

            RLid = itemView.findViewById(R.id.Rid);
            RLid.setBackgroundColor(Color.parseColor("WHITE"));
        }
    }

    public Adapter(ArrayList<items> mList) {
        mmList = mList;
    }


    @NonNull
    @Override
    public mViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.items, parent, false);
        mViewHolder mvh = new mViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull mViewHolder holder, int position) {
        items cItem = mmList.get(position);

        holder.mImageView.setImageResource(cItem.getmImage());
        holder.mTextView1.setText(cItem.getName());
        holder.mTextView2.setText(cItem.getScores());
        holder.mTextView3.setText(cItem.getRank());

    }

    @Override
    public int getItemCount() {

        return mmList.size();
    }




}
