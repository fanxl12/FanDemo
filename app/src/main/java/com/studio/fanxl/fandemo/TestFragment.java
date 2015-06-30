package com.studio.fanxl.fandemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by fanxl on 2015/6/30.
 */
public class TestFragment extends Fragment {

    private static final String TITLE_NAME = "TITLE_NAME";
    private String title = "默认标题";

    TextView fragemnt_text_title;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_test, container, false);
        Bundle bundle = getArguments();
        if (bundle!=null){
            title = bundle.getString(TITLE_NAME);
        }
        fragemnt_text_title = (TextView)view.findViewById(R.id.fragemnt_text_title);
        if(title != null){
            fragemnt_text_title.setText(title);
        }
        return view;
    }



    public static TestFragment getInstance(String title){
        Bundle bundle = new Bundle();
        bundle.putString(TITLE_NAME, title);
        TestFragment testFragment = new TestFragment();
        testFragment.setArguments(bundle);
        return testFragment;
    }
}
