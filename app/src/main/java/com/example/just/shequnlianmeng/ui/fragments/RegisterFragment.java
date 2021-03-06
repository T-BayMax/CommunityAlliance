package com.example.just.shequnlianmeng.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.just.shequnlianmeng.R;
import com.example.just.shequnlianmeng.utils.AMUtils;
import com.example.just.shequnlianmeng.utils.LoadDialog;
import com.example.just.shequnlianmeng.utils.T;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class RegisterFragment extends Fragment {
    public interface FinishRegisterListener{
        void finishRegister();
    }
    @BindView(R.id.et_re_ph)
     EditText et_phoneNum;
    @BindView(R.id.et_re_pw)
     EditText et_pw;
    @BindView(R.id.et_re_invite_code)
    EditText et_inviteCode;
     private FinishRegisterListener listener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View containerView=inflater.inflate(R.layout.fragment_register, container, false);
        ButterKnife.bind(this,containerView);
        addEditTextListener();
        return containerView;
    }
    private void addEditTextListener() {
        et_phoneNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 11) {
                    if (!AMUtils.isMobile(et_phoneNum.getText().toString().trim())) {
                        T.showShort(getContext(), "请输入正确的手机号码");
                        return;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    @OnClick(R.id.btn_register)
    public void registerOnClick(View view){
       String password = et_pw.getText().toString();
      String  phone = et_phoneNum.getText().toString();
        String inviteCode = et_inviteCode.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            T.showShort(getContext(), "手机号不能为空");
            return;
        }
        if (TextUtils.isEmpty(password) || password.length() < 4) {
            T.showShort(getContext(), "密码不能为空且长度不能小于4");
            return;
        }
        if (TextUtils.isEmpty(inviteCode)) {
            T.showShort(getContext(), "请输入验证码");
            return;
        }
        LoadDialog.show(getContext());
        //信息提交服务器
        listener.finishRegister();
        et_pw.setText("");
        et_phoneNum.setText("");
        et_inviteCode.setText("");
    }

    public void setListener(FinishRegisterListener listener){
        this.listener=listener;
    }
    }