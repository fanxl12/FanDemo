package com.studio.fanxl.fandemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * Created by fanxl on 2015/6/30.
 */
@EFragment(R.layout.fragment_main_test)
public class TestFragment extends Fragment {

    private static final String TITLE_NAME = "TITLE_NAME";
    private String title = "默认标题";

    @ViewById
    TextView fragemnt_text_title;

    @AfterViews
    void init(){
        Bundle bundle = getArguments();
        if (bundle!=null){
            title = bundle.getString(TITLE_NAME);
        }
        if(title != null){
            fragemnt_text_title.setText(title);
        }
    }


    public static TestFragment getInstance(String title){
        Bundle bundle = new Bundle();
        bundle.putString(TITLE_NAME, title);
        TestFragment testFragment = new TestFragment_();
        testFragment.setArguments(bundle);
        return testFragment;
    }
}
