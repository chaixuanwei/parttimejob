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
import com.example.myapplication.me.activity.InsightsUserActivity;

public class ApplyPeopleAdapter extends RecyclerView.Adapter<ApplyPeopleAdapter.ViewHolder> {
    Context mContext;

    public ApplyPeopleAdapter(Context pContext) {
        mContext = pContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup pViewGroup, int pI) {
        View mView = LayoutInflater.from(mContext).inflate(R.layout.item_apply_people, pViewGroup, false);
        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder pViewHolder, int pI) {
        ViewHolder mViewHolder = pViewHolder;
        mViewHolder.mApplyPeopleLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mInsightsUserIntent = new Intent(mContext, InsightsUserActivity.class);
                mContext.startActivity(mInsightsUserIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout mApplyPeopleLl;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mApplyPeopleLl = itemView.findViewById(R.id.apply_people_ll);
        }
    }
}
