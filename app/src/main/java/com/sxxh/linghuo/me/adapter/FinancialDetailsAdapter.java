package com.sxxh.linghuo.me.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sxxh.linghuo.R;
import com.sxxh.linghuo.local_utils.DateUtil;
import com.sxxh.linghuo.me.bean.FinancialDetailsBean;

import java.util.ArrayList;

public class FinancialDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context mContext;
    ArrayList<FinancialDetailsBean.DataBean> mList;
//    int DAY = 2;
    int SERVICE_CHARGE = 1;
    int WITHDRAW = 0;

    public FinancialDetailsAdapter(Context pContext, ArrayList<FinancialDetailsBean.DataBean> pList) {
        mContext = pContext;
        mList = pList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup pViewGroup, int pI) {
        RecyclerView.ViewHolder mViewHolder = null;
        if (pI == WITHDRAW) {
            View mView = LayoutInflater.from(mContext).inflate(R.layout.item_withdraw, pViewGroup, false);
            mViewHolder = new WithdrawView(mView);
        } else if (pI == SERVICE_CHARGE) {
            View mView = LayoutInflater.from(mContext).inflate(R.layout.item_service, pViewGroup, false);
            mViewHolder = new ServiceView(mView);
        }
        /* else if (pI == DAY) {
            View mView = LayoutInflater.from(mContext).inflate(R.layout.item_day, pViewGroup, false);
            mViewHolder = new DayView(mView);
        }*/
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder pViewHolder, int pI) {
        FinancialDetailsBean.DataBean mDataBean = mList.get(pI);
        int mCreate_time = mDataBean.getCreate_time();
        String mDate = DateUtil.getDateToString(mCreate_time, "yyyy-MM-dd");
        if (pI == WITHDRAW) {
            WithdrawView mWithdrawView = (WithdrawView) pViewHolder;
            mWithdrawView.mWithdrawName.setText(mDataBean.getTitle());
            mWithdrawView.mWithdrawNum.setText(mDataBean.getChange());
            mWithdrawView.mWithdrawPlace.setText(mDataBean.getDescription());
            mWithdrawView.mWithdrawTime.setText(mDate);
        } else if (pI == SERVICE_CHARGE) {
            ServiceView mServiceView = (ServiceView) pViewHolder;
            mServiceView.mServiceName.setText(mDataBean.getTitle());
            mServiceView.mServiceNum.setText(mDataBean.getChange());
            mServiceView.mServicePlace.setText(mDataBean.getDescription());
            mServiceView.mServiceTime.setText(mDate);
        }
        /* else if (pI == DAY) {
            DayView mDayView = (DayView) pViewHolder;
        }*/
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        /*if (position == DAY) {
            return DAY;
        } else */
        if (mList.get(position).getType() == SERVICE_CHARGE) {
            return SERVICE_CHARGE;
        } else if (mList.get(position).getType() == WITHDRAW) {
            return WITHDRAW;
        } else {
            return 3;
        }
    }

    private class WithdrawView extends RecyclerView.ViewHolder {
        private TextView mWithdrawName;
        private TextView mWithdrawPlace;
        private TextView mWithdrawTime;
        private TextView mWithdrawNum;
        public WithdrawView(@NonNull View itemView) {
            super(itemView);
            mWithdrawName = itemView.findViewById(R.id.withdraw_name);
            mWithdrawPlace = itemView.findViewById(R.id.withdraw_place);
            mWithdrawTime = itemView.findViewById(R.id.withdraw_time);
            mWithdrawNum = itemView.findViewById(R.id.withdraw_num);
        }
    }

    private class ServiceView extends RecyclerView.ViewHolder {
        private TextView mServiceName;
        private TextView mServicePlace;
        private TextView mServiceTime;
        private TextView mServiceNum;

        public ServiceView(@NonNull View itemView) {
            super(itemView);
            mServiceName = itemView.findViewById(R.id.service_name);
            mServicePlace = itemView.findViewById(R.id.service_place);
            mServiceTime = itemView.findViewById(R.id.service_time);
            mServiceNum = itemView.findViewById(R.id.service_num);
        }
    }

    /*private class DayView extends RecyclerView.ViewHolder {
        private TextView mItemDay;
        private TextView mItemNum;

        public DayView(@NonNull View itemView) {
            super(itemView);
            mItemDay = itemView.findViewById(R.id.item_day);
            mItemNum = itemView.findViewById(R.id.item_num);
        }
    }*/
}
