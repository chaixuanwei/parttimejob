package com.sxxh.linghuo.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sxxh.linghuo.R;
import com.sxxh.linghuo.view.RoundImage;

public class CommpanyAppraiseAdapter extends RecyclerView.Adapter<CommpanyAppraiseAdapter.ViewHolder> {
    Context mContext;

    public CommpanyAppraiseAdapter(Context pContext) {
        mContext = pContext;
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

        } else {
            mViewHolder.mCompanyNewImg.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mCompanyNewImg;
        private RoundImage mCompanyPeoplePhoto;
        private TextView mCompanyPeopleName;
        private TextView mCompanyDegreeOfMatch;
        private TextView mCompanyWageLevel;
        private TextView mCompanyWageRate;
        private TextView mCompanyTrainingBenefits;
        private TextView mCompanyPersonnelAttitude;
        private TextView mCompanyTime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mCompanyNewImg = itemView.findViewById(R.id.company_new_img);
            mCompanyPeoplePhoto = itemView.findViewById(R.id.company_people_photo);
            mCompanyPeopleName = itemView.findViewById(R.id.company_people_name);
            mCompanyDegreeOfMatch = itemView.findViewById(R.id.company_degree_of_match);
            mCompanyWageLevel = itemView.findViewById(R.id.company_wage_level);
            mCompanyWageRate = itemView.findViewById(R.id.company_wage_rate);
            mCompanyTrainingBenefits = itemView.findViewById(R.id.company_training_benefits);
            mCompanyPersonnelAttitude = itemView.findViewById(R.id.company_personnel_attitude);
            mCompanyTime = itemView.findViewById(R.id.company_time);
        }
    }
}
