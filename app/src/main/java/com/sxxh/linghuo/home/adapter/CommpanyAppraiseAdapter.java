package com.sxxh.linghuo.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sxxh.linghuo.R;
import com.sxxh.linghuo.home.bean.IssuerGeneralEvaluation;
import com.sxxh.linghuo.view.RoundImage;

import java.util.List;

public class CommpanyAppraiseAdapter extends RecyclerView.Adapter<CommpanyAppraiseAdapter.ViewHolder> {
    Context mContext;
    private List<IssuerGeneralEvaluation.DatasBean> mDatasList;

    public CommpanyAppraiseAdapter(Context pContext, List<IssuerGeneralEvaluation.DatasBean> mDatasList) {
        mContext = pContext;
        this.mDatasList = mDatasList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup pViewGroup, int pI) {
        View mView = LayoutInflater.from(mContext).inflate(R.layout.item_company, pViewGroup, false);
        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder pViewHolder, int pI) {
        ViewHolder mViewHolder = pViewHolder;
        if (pI == 0) {
            Glide.with(mContext).load(mDatasList.get(pI))
                    .error(R.mipmap.logo)//异常时候显示的图片
                    .fallback(R.mipmap.logo) //url为空的时候,显示的图片
                    .into(mViewHolder.mCompanyPeoplePhoto);
            mViewHolder.mCompanyPeopleName.setText(mDatasList.get(pI).getUser_nickname());
            mViewHolder.mCompanyDegreeOfMatch.setText(mDatasList.get(pI).getStationcomment());
            mViewHolder.mCompanyWageLevel.setText(mDatasList.get(pI).getPaycomment());
            mViewHolder.mCompanyPersonnelAttitude.setText(mDatasList.get(pI).getServicecomment());
        } else {
            mViewHolder.mCompanyNewImg.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mDatasList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mCompanyNewImg;
        private RoundImage mCompanyPeoplePhoto;
        private TextView mCompanyPeopleName;
        private TextView mCompanyDegreeOfMatch;
        private TextView mCompanyWageLevel;
        private TextView mCompanyPersonnelAttitude;
        private TextView mCompanyTime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mCompanyNewImg = itemView.findViewById(R.id.company_new_img);
            mCompanyPeoplePhoto = itemView.findViewById(R.id.company_people_photo);
            mCompanyPeopleName = itemView.findViewById(R.id.company_people_name);
            mCompanyDegreeOfMatch = itemView.findViewById(R.id.company_degree_of_match);
            mCompanyWageLevel = itemView.findViewById(R.id.company_wage_level);
            mCompanyPersonnelAttitude = itemView.findViewById(R.id.company_personnel_attitude);
            mCompanyTime = itemView.findViewById(R.id.company_time);
        }
    }
}
