package com.example.myapplication.me.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.myapplication.R;
import com.example.myapplication.me.activity.ProjectReviewActivity;

public class InspectAdapter extends RecyclerView.Adapter<InspectAdapter.ViewHolder> {
    Context mContext;

    public InspectAdapter(Context pContext) {
        mContext = pContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup pViewGroup, int pI) {
        View mView = LayoutInflater.from(mContext).inflate(R.layout.item_inspect, pViewGroup, false);
        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder pViewHolder, int pI) {
        ViewHolder mViewHolder = pViewHolder;
        mViewHolder.mItemInspectLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mProjectReviewIntent = new Intent(mContext, ProjectReviewActivity.class);
                mContext.startActivity(mProjectReviewIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout mItemInspectLl;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mItemInspectLl = itemView.findViewById(R.id.item_inspect_ll);
        }
    }
}
