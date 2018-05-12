package com.wangshun.test.test;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wangshun.test.R;
import com.wangshun.test.recyclerview.CommonAdapter;
import com.wangshun.test.recyclerview.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MyNestedScrollTestActivity extends Activity {
    private List<String> mDatas = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_nested_scroll_test);

        RecyclerView mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        for (int i = 0; i < 50; i++) {
            mDatas.add("value" + " -> " + i);
        }

        mRecyclerView.setAdapter(new CommonAdapter<String>(this, R.layout.item, mDatas) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {
                holder.setText(R.id.id_info, s);
            }
        });


    }
}

