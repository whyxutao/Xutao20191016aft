package com.bawei.xutao.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.bawei.xutao.R;
import com.bawei.xutao.ShopActivity;
import com.bawei.xutao.base.BaseActivity;
import com.bawei.xutao.bean.User;
import com.bawei.xutao.core.DataCall;
import com.bawei.xutao.presenter.LoginPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements DataCall<User> {


    @BindView(R.id.edit_phone)
    EditText editPhone;
    @BindView(R.id.edit_pwd)
    EditText editPwd;
    @BindView(R.id.jizhu)
    CheckBox jizhu;
    @BindView(R.id.login)
    Button login;
    private LoginPresenter loginPresenter;
    private SharedPreferences sp;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;

    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        loginPresenter = new LoginPresenter(this);

        sp = getSharedPreferences("dl", MODE_PRIVATE);

        editPhone.setText(sp.getString("phone",""));
        editPwd.setText(sp.getString("pwd",""));

    }


    @Override
    public void onSuccess(User result) {

        result.check = true;
        userDao.insertOrReplace(result);
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(MainActivity.this, ShopActivity.class));

    }

    @Override
    public void onError(String data) {



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.login)
    public void onViewClicked() {

        String phone = editPhone.getText().toString().trim();
        String pwd = editPwd.getText().toString().trim();

        SharedPreferences.Editor edit = sp.edit();

        edit.putString("phone",phone);
        edit.putString("pwd",pwd);

        edit.commit();

        loginPresenter.requestData(phone,pwd);

    }
}
