package com.studio.fanxl.fandemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.studio.fanxl.fandemo.utils.KeyBoardUtils;
import com.studio.fanxl.fandemo.utils.SharePreferenceUtil;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.login)
public class Login extends AppCompatActivity {

	@ViewById
	TextInputLayout login_username;
	@ViewById
	TextInputLayout login_password;
	@ViewById
	AppCompatCheckBox re_password;
	@ViewById
	LinearLayout login_main;

	private final String LOGIN_PW_NAME = "LOGIN_PW_NAME";
	private final String LOGIN_UN_NAME = "LOGIN_UN_NAME";
	private final String IS_RM_PW = "IS_RM_PW";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@AfterViews
	void init() {
		re_password.setChecked(SharePreferenceUtil.getBoolean(Login.this, IS_RM_PW, true));
		if(re_password.isChecked()){
			login_username.getEditText().setText(SharePreferenceUtil.getString(Login.this, LOGIN_UN_NAME, ""));
			login_password.getEditText().setText(SharePreferenceUtil.getString(Login.this, LOGIN_PW_NAME, ""));
		}

		//监听键盘的回车事件
		login_password.getEditText().setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
				KeyBoardUtils.closeKeybord(login_username.getEditText(), Login.this);
				toLogin();
				return true;
			}
		});

		//单击空白地方，关闭键盘
		login_main.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				KeyBoardUtils.closeKeybord(login_username.getEditText(), Login.this);
			}
		});
	}

	@Click(R.id.bt_login)
	void login(){
		toLogin();
	}

	/**
	 * 登录方法
	 */
	private void toLogin(){
		login_username.setError("");
		login_password.setError("");

		String userName = login_username.getEditText().getText().toString().trim();
		String passWord = login_password.getEditText().getText().toString().trim();

		View focusView = null;
		boolean toLogin = true;
		if(TextUtils.isEmpty(userName)){
			login_username.setError("用户名不能为空");
			focusView = login_username;
			toLogin = false;
		}else if(TextUtils.isEmpty(passWord)){
			login_password.setError("密码不能为空");
			focusView = login_password;
			toLogin = false;
		}

		if(toLogin){
			if(re_password.isChecked()){
				SharePreferenceUtil.setValue(Login.this, LOGIN_UN_NAME, userName);
				SharePreferenceUtil.setValue(Login.this, LOGIN_PW_NAME, passWord);
				SharePreferenceUtil.setValue(Login.this, IS_RM_PW, true);
				startActivity(new Intent(Login.this, MainActivity_.class));
			}else{
				SharePreferenceUtil.clear(Login.this);
			}
		}else{
			//使无效的输入框获取焦点
			focusView.requestFocus();
		}
	}
}
