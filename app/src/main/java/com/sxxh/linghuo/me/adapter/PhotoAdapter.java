package com.sxxh.linghuo.me.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sxxh.linghuo.R;

import java.util.ArrayList;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {
    Context mContext;
    ArrayList<String> mSelectPath = new ArrayList<>();

    public PhotoAdapter(Context pContext, ArrayList<String> pSelectPath) {
        mContext = pContext;
        mSelectPath = pSelectPath;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup pViewGroup, int pI) {
        View mView = LayoutInflater.from(mContext).inflate(R.layout.item_photo, null);
        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder pViewHolder, final int pI) {
        ViewHolder mViewHolder = pViewHolder;
        if (pI == 0) {
            Glide.with(mContext).load(R.mipmap.photo).into(mViewHolder.mItemPhoto);
        } else {
            Glide.with(mContext).load(mSelectPath.get(pI-1)).into(mViewHolder.mItemPhoto);
        }
        mViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onPhoto(pI);
            }
        });
    }

    public void setListener(OnPhotoClick pListener) {
        listener = pListener;
    }

    private OnPhotoClick listener;

    public interface OnPhotoClick{
        void onPhoto(int pI);
    }

    @Override
    public int getItemCount() {
        return mSelectPath.size()+1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mItemPhoto;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mItemPhoto = itemView.findViewById(R.id.item_photo);
        }
    }
}
