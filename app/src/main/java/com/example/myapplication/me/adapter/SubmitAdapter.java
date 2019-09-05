package com.example.myapplication.me.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class SubmitAdapter extends RecyclerView.Adapter<SubmitAdapter.ViewHolder> {
    Context mContext;
    private ArrayList<String> mList;

    public SubmitAdapter(Context pContext, ArrayList<String> pList) {
        mContext = pContext;
        mList = pList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup pViewGroup, int pI) {
        View mView = LayoutInflater.from(mContext).inflate(R.layout.item_submit, null);
        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder pViewHolder, int pI) {
        ViewHolder mViewHolder = pViewHolder;
        if (mList.size() > 0) {
            if (pI != mList.size()) {
                mViewHolder.mPhoto.setVisibility(View.GONE);
            } else {
                mViewHolder.mPhoto.setVisibility(View.VISIBLE);
            }
        } else {
            mViewHolder.mPhoto.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size() + 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mWorkingPhoto;
        private ImageView mPhoto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mWorkingPhoto = itemView.findViewById(R.id.working_photo);
            mPhoto = itemView.findViewById(R.id.photo);
        }
    }
}
