package com.example.myapplication.me.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.me.activity.FinancialDetailsActivity;

public class FinancialDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context mContext;
    int DAY = 0;
    int SERVICE_CHARGE = 1;
    int WITHDRAW = 2;

    public FinancialDetailsAdapter(Context pContext) {
        mContext = pContext;
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
        } else if (pI == DAY) {
            View mView = LayoutInflater.from(mContext).inflate(R.layout.item_day, pViewGroup, false);
            mViewHolder = new DayView(mView);
        }
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder pViewHolder, int pI) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == WITHDRAW) {
            return WITHDRAW;
        } else if (position == SERVICE_CHARGE){
            return SERVICE_CHARGE;
        } else {
            return DAY;
        }
    }

    private class WithdrawView extends RecyclerView.ViewHolder {
        public WithdrawView(@NonNull View itemView) {
            super(itemView);
        }
    }

    private class ServiceView extends RecyclerView.ViewHolder {
        public ServiceView(@NonNull View itemView) {
            super(itemView);
        }
    }

    private class DayView extends RecyclerView.ViewHolder {
        public DayView(@NonNull View itemView) {
            super(itemView);
        }
    }
}
