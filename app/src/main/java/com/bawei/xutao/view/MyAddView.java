package com.bawei.xutao.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bawei.xutao.R;

/**
 * @describe(描述)：com.bawei.goshopdemo.view.iview
 * @data（日期）: 19:2019/10/18
 * @time（时间）: 19:00
 * @author（作者）: 徐涛
 **/
public class MyAddView extends FrameLayout implements View.OnClickListener {

    TextView textView,subtract;

    public MyAddView( Context context) {
        super(context);
        initView();
    }

    public MyAddView( Context context,  AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MyAddView( Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        View view = View.inflate(getContext(), R.layout.add_view, this);
        textView = view.findViewById(R.id.view_text);
        subtract = view.findViewById(R.id.view_subtract);
        view.findViewById(R.id.view_add).setOnClickListener(this);
        view.findViewById(R.id.view_subtract).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int count = Integer.parseInt(textView.getText().toString());
        switch (v.getId()){
            case R.id.view_add:
                count++;
                if (count == 2)
                    subtract.setTextColor(Color.BLACK);
            break;
            case R.id.view_subtract:
                if (count == 1){
                    subtract.setTextColor(Color.GRAY);
                    return;
                }
                count--;
                break;
        }
        textView.setText(String.valueOf(count));
        if (addReduceListener != null)
            addReduceListener.addReduce(this,count);
    }

    public void setCount(int count) {
        textView.setText(String.valueOf(count));
    }

    private AddReduceListener addReduceListener;

    public void setAddReduceListener(AddReduceListener addReduceListener) {
        this.addReduceListener = addReduceListener;
    }

    public interface AddReduceListener {
        void addReduce(MyAddView myAddView, int count);
    }
}
