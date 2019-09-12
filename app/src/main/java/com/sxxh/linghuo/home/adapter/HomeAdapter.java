package com.sxxh.linghuo.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sxxh.linghuo.R;
import com.sxxh.linghuo.frame.BaseAdapter;
import com.sxxh.linghuo.home.activity.DetailActivity;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private int HOME_OPTION = 0;
    private int HOME_TEXT = 1;
    private int HOME_CONTENT = 2;

    public HomeAdapter(Context pContext) {
        mContext = pContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup pViewGroup, int pI) {
        RecyclerView.ViewHolder viewHolder = null;
        if (pI == HOME_OPTION) {
            View mInflate = LayoutInflater.from(mContext).inflate(R.layout.item_home_option, pViewGroup,false);
            viewHolder = new ViewOption(mInflate);
        } else if (pI == HOME_TEXT) {
            View mInflate = LayoutInflater.from(mContext).inflate(R.layout.item_home_text, pViewGroup,false);
            viewHolder = new ViewText(mInflate);
        } else {
            View mInflate = LayoutInflater.from(mContext).inflate(R.layout.item_home_content, pViewGroup,false);
            viewHolder = new ViewContent(mInflate);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder pViewHolder, int pI) {
        if (getItemViewType(pI) == HOME_OPTION) {
            ViewOption mViewOption = (ViewOption) pViewHolder;
        } else if (getItemViewType(pI) == HOME_TEXT) {
            ViewText mViewText = (ViewText) pViewHolder;
        } else if (getItemViewType(pI) == HOME_CONTENT) {
            ViewContent mViewContent = (ViewContent) pViewHolder;
            mViewContent.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent mIntent = new Intent(mContext, DetailActivity.class);
                    mContext.startActivity(mIntent);
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == HOME_OPTION) {
            return HOME_OPTION;
        } else if (position == HOME_TEXT) {
            return HOME_TEXT;
        } else {
            return HOME_CONTENT;
        }
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class ViewOption extends BaseAdapter.ViewHolder {
        private LinearLayout mItemHomeAll;
        private LinearLayout mItemHomeNear;
        private LinearLayout mItemHomeCase;
        private LinearLayout mItemHomeDaily;
        public ViewOption(View itemView) {
            super(itemView);
            mItemHomeAll = itemView.findViewById(R.id.item_home_all);
            mItemHomeNear = itemView.findViewById(R.id.item_home_near);
            mItemHomeCase = itemView.findViewById(R.id.item_home_case);
            mItemHomeDaily = itemView.findViewById(R.id.item_home_daily);
        }
    }

    class ViewText extends BaseAdapter.ViewHolder {
        private TextView mItemHomeMore;

        public ViewText(View itemView) {
            super(itemView);
            mItemHomeMore = itemView.findViewById(R.id.item_home_more);
        }
    }

    class ViewContent extends BaseAdapter.ViewHolder {
        private TextView mItemHomeTop;
        private TextView mItemHomeJackaroo;
        private TextView mItemHomeHot;
        private TextView mItemHomeMoney;
        private TextView mItemHomeTime;
        private TextView mItemHomePerson;
        private TextView mItemHomeJobPlace;
        private TextView mItemInterview;
        private TextView mItemHomeJobTime;
        private TextView mItemHomeJobDate;
        private TextView mItemHomeAtonce;

        public ViewContent(View itemView) {
            super(itemView);
            mItemHomeTop = itemView.findViewById(R.id.item_home_top);
            mItemHomeJackaroo = itemView.findViewById(R.id.item_home_jackaroo);
            mItemHomeHot = itemView.findViewById(R.id.item_home_hot);
            mItemHomeMoney = itemView.findViewById(R.id.item_home_money);
            mItemHomeTime = itemView.findViewById(R.id.item_home_time);
            mItemHomePerson = itemView.findViewById(R.id.item_home_person);
            mItemHomeJobPlace = itemView.findViewById(R.id.item_home_job_place);
            mItemInterview = itemView.findViewById(R.id.item_interview);
            mItemHomeJobTime = itemView.findViewById(R.id.item_home_job_time);
            mItemHomeJobDate = itemView.findViewById(R.id.item_home_job_date);
            mItemHomeAtonce = itemView.findViewById(R.id.item_home_atonce);
        }
    }
}
