package com.studio.fanxl.fandemo.utils;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class KeyBoardUtils {
    public KeyBoardUtils() {
    }

    public static void openKeybord(EditText mEditText, Context mContext) {
        InputMethodManager imm = (InputMethodManager)mContext.getSystemService("input_method");
        imm.showSoftInput(mEditText, 2);
        imm.toggleSoftInput(2, 1);
    }

    public static void closeKeybord(EditText mEditText, Context mContext) {
        InputMethodManager imm = (InputMethodManager)mContext.getSystemService("input_method");
        imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
    }
}
