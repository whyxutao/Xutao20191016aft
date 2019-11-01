package com.bawei.xutao20191018aft;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.bawei.xutao20191018aft.bean.Data;
import com.bawei.xutao20191018aft.core.DataCall;
import com.bawei.xutao20191018aft.presenter.WeaPresenter;

public class MainActivity extends AppCompatActivity implements DataCall<Data> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WeaPresenter weaPresenter = new WeaPresenter(this);

        weaPresenter.reqeustData("北京");

    }

    @Override
    public void onSuccess(Data result) {



    }

    @Override
    public void onError(Data data) {

        Toast.makeText(this, ""+data.reason+" "+data.error_code, Toast.LENGTH_SHORT).show();

    }
}
