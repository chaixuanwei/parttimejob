package com.sxxh.linghuo.me.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sxxh.linghuo.R;
import com.sxxh.linghuo.me.bean.ApplyPeopleBean;

import java.util.ArrayList;

public class ApplyPeopleAdapter extends RecyclerView.Adapter<ApplyPeopleAdapter.ViewHolder> {
    Context mContext;
    ArrayList<ApplyPeopleBean.DataBean> mList;

    public ApplyPeopleAdapter(Context pContext, ArrayList<ApplyPeopleBean.DataBean> pList) {
        mContext = pContext;
        mList = pList;
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
        final ApplyPeopleBean.DataBean mDataBean = mList.get(pI);
        mViewHolder.mItemPeopleName.setText(mDataBean.getName());
        mViewHolder.mItemPeopleProject.setText(mDataBean.getReal_name());
        Glide.with(mContext).load(mDataBean.getAvatar()).into(mViewHolder.mItemPeopleImg);
        mViewHolder.mLookPeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mJump.people("look",mDataBean.getU_id());
            }
        });
        mViewHolder.mCompaintPeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mJump.people("compaint",mDataBean.getU_id());
            }
        });
    }

    private ToUser mJump;

    public void setJump(ToUser pJump) {
        mJump = pJump;
    }

    public interface ToUser{
        void people(String type,int id);
    };

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout mApplyPeopleLl;
        private ImageView mItemPeopleImg;
        private TextView mItemPeopleName;
        private TextView mItemPeopleProject;
        private TextView mCompaintPeople;
        private TextView mLookPeople;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mApplyPeopleLl = itemView.findViewById(R.id.apply_people_ll);
            mItemPeopleImg = itemView.findViewById(R.id.item_people_img);
            mItemPeopleName = itemView.findViewById(R.id.item_people_name);
            mItemPeopleProject = itemView.findViewById(R.id.item_people_project);
            mLookPeople = itemView.findViewById(R.id.look_people);
            mCompaintPeople = itemView.findViewById(R.id.compaint_people);
        }
    }
}
