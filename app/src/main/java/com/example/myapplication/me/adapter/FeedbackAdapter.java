package com.example.myapplication.me.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.example.myapplication.R;

import java.util.ArrayList;

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.ViewHolder> {
    Context mContext;
    ArrayList<String> mList;
    ArrayList<Boolean> mBooleans = new ArrayList<>();

    public FeedbackAdapter(Context pContext, ArrayList<String> pList) {
        mContext = pContext;
        mList = pList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup pViewGroup, int pI) {
        View mView = LayoutInflater.from(mContext).inflate(R.layout.item_feedback, null);
        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder pViewHolder, final int pI) {
        if (mBooleans.size() > 0) {
        } else {
            for (int i = 0; i < mList.size(); i++) {
                mBooleans.add(false);
            }
        }
        if (mList.size() > 0) {
            final ViewHolder mViewHolder = pViewHolder;
            mViewHolder.mItemFeedbackRb.setText(mList.get(pI));
            mViewHolder.mItemFeedbackRb.setChecked(mBooleans.get(pI));
            if (mViewHolder.mItemFeedbackRb.isChecked()) {
                mViewHolder.mItemFeedbackRb.setTextColor(mContext.getResources().getColor(R.color.blue_theme));
            } else {
                mViewHolder.mItemFeedbackRb.setTextColor(mContext.getResources().getColor(R.color.black_theme));
            }
            mViewHolder.mItemFeedbackRb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < mList.size(); i++) {
                        mBooleans.set(i,false);
                    }
                    mBooleans.set(pI, true);
                    mGetFeedbackTitle.title(mList.get(pI));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    private getFeedbackTitle mGetFeedbackTitle;

    public void setFeedbackTitle(getFeedbackTitle pGetFeedbackTitle) {
        mGetFeedbackTitle = pGetFeedbackTitle;
    }

    public interface getFeedbackTitle {
        void title(String title);
    }

    ;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private RadioButton mItemFeedbackRb;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mItemFeedbackRb = itemView.findViewById(R.id.item_feedback_rb);
        }
    }
}
