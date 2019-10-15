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
import com.sxxh.linghuo.home.bean.HomeData;
import com.sxxh.linghuo.local_utils.DateUtil;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private int HOME_OPTION = 0;
    private int HOME_TEXT = 1;
    private int HOME_CONTENT = 2;
    ArrayList<HomeData.DataBean> mList = new ArrayList<>();

    public HomeAdapter(Context pContext, ArrayList<HomeData.DataBean> pList) {
        mContext = pContext;
        mList = pList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup pViewGroup, int pI) {
        RecyclerView.ViewHolder viewHolder = null;
        if (pI == HOME_OPTION) {
            View mInflate = LayoutInflater.from(mContext).inflate(R.layout.item_home_option, pViewGroup, false);
            viewHolder = new ViewOption(mInflate);
        } else if (pI == HOME_TEXT) {
            View mInflate = LayoutInflater.from(mContext).inflate(R.layout.item_home_text, pViewGroup, false);
            viewHolder = new ViewText(mInflate);
        } else {
            View mInflate = LayoutInflater.from(mContext).inflate(R.layout.item_home_content, pViewGroup, false);
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
            final HomeData.DataBean mDataBean = mList.get(pI - 2);
            ViewContent mViewContent = (ViewContent) pViewHolder;
            mViewContent.mHomeJobName.setText(mDataBean.getName());
            DateUtil mDateUtil = new DateUtil();
            int mStart_time = mDataBean.getStart_time();
            int mEnd_time = mDataBean.getEnd_time();
            if (mStart_time != 0 && mEnd_time != 0) {
                String mStartToString = mDateUtil.getDateToString(mStart_time, "yyyy-MM-dd");
                String mEndToString = mDateUtil.getDateToString(mEnd_time, "yyyy-MM-dd");
                mViewContent.mItemHomeJobDate.setText(mStartToString + "至" + mEndToString);
            }
            if (mDataBean.getIs_muster() == 1) {
                int mMuster_time = mDataBean.getMuster_time();
                if (mMuster_time != 0) {
                    String mDateToString = mDateUtil.getDateToString(mMuster_time, "yy-MM-dd");
                    mViewContent.mItemHomeJobTime.setText(mDateToString);
                }
            } else {
                mViewContent.mItemHomeJobTime.setText("无需集合");
            }
            mViewContent.mItemHomePerson.setText(mDataBean.getZp_num() + "");
            mViewContent.mItemHomeJobPlace.setText(mDataBean.getWork_location());
            if (mDataBean.getPay().equals("") || mDataBean.getPay() == null) {
//                mViewContent.mItemHomeTime.setText("薪资面议");
            } else {
                mViewContent.mItemHomeMoney.setText(mDataBean.getPay() + "");
            }
            mViewContent.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent mIntent = new Intent(mContext, DetailActivity.class);
                    mIntent.putExtra("tid", mDataBean.getId());
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
        return mList.size() + 2;
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
        private TextView mHomeJobName;
        private TextView mItemHomeJackaroo;
        private TextView mItemHomeHot;
        private TextView mItemHomeMoney;
        private TextView mItemHomePerson;
        private TextView mItemHomeJobPlace;
//        private TextView mItemInterview;
        private TextView mItemHomeJobTime;
        private TextView mItemHomeJobDate;
        private TextView mItemHomeAtonce;

        public ViewContent(View itemView) {
            super(itemView);
            mItemHomeTop = itemView.findViewById(R.id.item_home_top);
            mHomeJobName = itemView.findViewById(R.id.home_job_name);
            mItemHomeJackaroo = itemView.findViewById(R.id.item_home_jackaroo);
            mItemHomeHot = itemView.findViewById(R.id.item_home_hot);
            mItemHomeMoney = itemView.findViewById(R.id.item_home_money);
            mItemHomePerson = itemView.findViewById(R.id.item_home_person);
            mItemHomeJobPlace = itemView.findViewById(R.id.item_home_job_place);
//            mItemInterview = itemView.findViewById(R.id.item_interview);
            mItemHomeJobTime = itemView.findViewById(R.id.item_home_job_time);
            mItemHomeJobDate = itemView.findViewById(R.id.item_home_job_date);
            mItemHomeAtonce = itemView.findViewById(R.id.item_home_atonce);
        }
    }
}
