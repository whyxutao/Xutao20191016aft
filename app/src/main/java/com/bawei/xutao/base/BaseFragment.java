package com.bawei.xutao.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bawei.xutao.db.DaoMaster;
import com.bawei.xutao.db.DaoSession;
import com.bawei.xutao.db.UserDao;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author: 徐涛
 * data: 2019/10/16 20:20:13
 * function:
 */
public abstract class BaseFragment extends Fragment {

    private Unbinder bind;
    protected UserDao userDao;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(),getLayoutID(),null);

        bind = ButterKnife.bind(this,view);
        DaoSession daoSession = DaoMaster.newDevSession(getContext(), UserDao.TABLENAME);
        userDao = daoSession.getUserDao();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView(savedInstanceState);
    }

    protected abstract void initView(Bundle savedInstanceState);

    protected abstract int getLayoutID();



    @Override
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
