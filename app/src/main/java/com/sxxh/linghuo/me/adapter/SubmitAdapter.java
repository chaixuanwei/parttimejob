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
    public void onBindViewHolder(@NonNull ViewHolder pViewHolder, final int pI) {
        ViewHolder mViewHolder = pViewHolder;
        if (mList.size() > 0) {
            if (pI != mList.size()) {
                mViewHolder.mPhoto.setVisibility(View.GONE);
                Glide.with(mContext).load(mList.get(pI)).into(mViewHolder.mWorkingPhoto);
                mViewHolder.mDelete.setVisibility(View.VISIBLE);
            } else {
                Glide.with(mContext).load("").into(mViewHolder.mWorkingPhoto);
                mViewHolder.mPhoto.setVisibility(View.VISIBLE);
                mViewHolder.mDelete.setVisibility(View.GONE);
            }
        } else {
            Glide.with(mContext).load("").into(mViewHolder.mWorkingPhoto);
            mViewHolder.mPhoto.setVisibility(View.VISIBLE);
            mViewHolder.mDelete.setVisibility(View.GONE);
        }
        mViewHolder.mWorkingPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mToJumpPhoto.getPhoto(pI);
            }
        });
        mViewHolder.mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDeletePhoto.goPhoto(pI);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size() + 1;
    }

    private ToJumpPhoto mToJumpPhoto;

    public void setToJumpPhoto(ToJumpPhoto pToJumpPhoto) {
        mToJumpPhoto = pToJumpPhoto;
    }

    public void setDeletePhoto(DeletePhoto pDeletePhoto) {
        mDeletePhoto = pDeletePhoto;
    }

    public interface ToJumpPhoto{
        void getPhoto(int position);
    };

    private DeletePhoto mDeletePhoto;

    public interface DeletePhoto{
        void goPhoto(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mWorkingPhoto;
        private ImageView mPhoto;
        private ImageView mDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mWorkingPhoto = itemView.findViewById(R.id.working_photo);
            mDelete = itemView.findViewById(R.id.delete);
            mPhoto = itemView.findViewById(R.id.photo);
        }
    }
}
