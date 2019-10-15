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
import com.sxxh.linghuo.home.bean.IssuerGeneralEvaluationBean;
import com.sxxh.linghuo.local_utils.DateUtil;
import com.sxxh.linghuo.view.RoundImage;

import java.util.ArrayList;
import java.util.List;

public class CommpanyAppraiseAdapter extends RecyclerView.Adapter<CommpanyAppraiseAdapter.ViewHolder> {
    Context mContext;
    private List<IssuerGeneralEvaluationBean.DatasBean> mDatasList = new ArrayList<>();

    public CommpanyAppraiseAdapter(Context pContext, List<IssuerGeneralEvaluationBean.DatasBean> mDatasList) {
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
            mViewHolder.mCompanyNewImg.setVisibility(View.VISIBLE);
        } else {
            mViewHolder.mCompanyNewImg.setVisibility(View.GONE);
        }
        String mAvatar = mDatasList.get(pI).getAvatar();
        int mAdd_time = mDatasList.get(pI).getAdd_time();
        if (mAvatar.equals("")) {

        } else {
            Glide.with(mContext).load(mAvatar).into(mViewHolder.mCompanyPeoplePhoto);
        }
        mViewHolder.mCompanyPeopleName.setText("姓名：" + mDatasList.get(pI).getUser_nickname());
        mViewHolder.mCompanyDegreeOfMatch.setText("岗位与实际相符度:" + mDatasList.get(pI).getStationcomment() + "");
        mViewHolder.mCompanyWageLevel.setText("工资水平:" + mDatasList.get(pI).getPaycomment() + "");
        mViewHolder.mCompanyPersonnelAttitude.setText("人员态度:" + mDatasList.get(pI).getServicecomment() + "");
        if (mAdd_time == 0) {

        } else {
            String mDate = DateUtil.getDateToString(mAdd_time, "yy-MM-dd HH:mm:ss");
            mViewHolder.mCompanyTime.setText(mDate);
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
