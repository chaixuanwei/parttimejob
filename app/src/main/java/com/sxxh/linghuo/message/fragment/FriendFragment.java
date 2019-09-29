package com.sxxh.linghuo.message.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sxxh.linghuo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FriendFragment extends Fragment {
    static FriendFragment fragment;

    public static FriendFragment newInstance() {
        if (fragment == null) fragment = new FriendFragment();
        return fragment;
    }

    public FriendFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_friend, container, false);
    }

}
