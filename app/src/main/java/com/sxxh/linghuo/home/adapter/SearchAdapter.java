package com.sxxh.linghuo.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sxxh.linghuo.R;
import com.sxxh.linghuo.home.activity.DetailActivity;
import com.sxxh.linghuo.home.bean.SearchDataBean;
import com.sxxh.linghuo.local_utils.DateUtil;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<SearchDataBean.DataBean> mList;

    public SearchAdapter(Context pContext, ArrayList<SearchDataBean.DataBean> pList) {
        mContext = pContext;
        mList = pList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup pViewGroup, int pI) {
        View mView = LayoutInflater.from(mContext).inflate(R.layout.item_search, pViewGroup, false);
        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder pViewHolder, int pI) {
        if (mList.size() > 0) {
            final SearchDataBean.DataBean mDataBean = mList.get(pI);
            pViewHolder.mItemSearchName.setText(mDataBean.getName());
            if (!mDataBean.getPay().equals("")) {
                pViewHolder.mItemSearchPay.setText(mDataBean.getPay());
            } else {
                pViewHolder.mItemSearchPay.setText("薪资面议");
            }
            pViewHolder.mItemSearchPlace.setText(mDataBean.getWork_location());
            int mStart_time = mDataBean.getStart_time();
            int mEnd_time = mDataBean.getEnd_time();
            String mStartToString = DateUtil.getDateToString(mStart_time, "yyyy-MM-dd");
            String mEndToString = DateUtil.getDateToString(mEnd_time, "yyyy-MM-dd");
            pViewHolder.mItemSearchTime.setText(mStartToString + "至" + mEndToString);
            pViewHolder.mItemSearchPeoper.setText(mDataBean.getZp_num()+"");
            pViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
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
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mItemSearchName;
        private TextView mItemSearchPay;
        private TextView mItemSearchPlace;
        private TextView mItemSearchTime;
        private TextView mItemSearchPeoper;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mItemSearchName = itemView.findViewById(R.id.item_search_name);
            mItemSearchPay = itemView.findViewById(R.id.item_search_pay);
            mItemSearchPlace = itemView.findViewById(R.id.item_search_place);
            mItemSearchTime = itemView.findViewById(R.id.item_search_time);
            mItemSearchPeoper = itemView.findViewById(R.id.item_search_peoper);
        }
    }
}
