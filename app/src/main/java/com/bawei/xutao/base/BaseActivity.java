package com.bawei.xutao.base;

import android.os.Bundle;

import com.bawei.xutao.db.DaoMaster;
import com.bawei.xutao.db.DaoSession;
import com.bawei.xutao.db.UserDao;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author: 徐涛
 * data: 2019/10/16 14:14:29
 * function:
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected UserDao userDao;
    private Unbinder bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        bind = ButterKnife.bind(this);
        DaoSession daoSession = DaoMaster.newDevSession(this, UserDao.TABLENAME);
        userDao = daoSession.getUserDao();
        initView(savedInstanceState);
    }

    protected abstract int getLayoutID();

    protected abstract void initView(Bundle savedInstanceState);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
