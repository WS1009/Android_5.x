package com.wangshun.test.activity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.wangshun.test.R;
import com.wangshun.test.adapter.CommonAdapter;
import com.wangshun.test.adapter.ViewHolder;
import com.wangshun.test.test.MyNestedScrollTestActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity
{

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<String> list = new ArrayList<String>();
        list.add("StickyNavLayout_listview");
        list.add("MyNestedScrollActivity");
        list.add("CustomTitleViewActivity");
        list.add("MyNestedScrollTestActivity");
        list.add("SimpleDraweeViewActivity");
        list.add("RecyclerViewActivity");
        list.add("StateViewActivity");


        mListView = findViewById(R.id.lv_main);

        mListView.setAdapter(new CommonAdapter<String>(MainActivity.this, list, R.layout.main_list)
        {
            @Override
            public void convert(ViewHolder helper, String item)
            {
                helper.setText(R.id.tv, item);
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                switch (i) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, StickyNavLayout_listview.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, MyNestedScrollActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, CustomTitleViewActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(MainActivity.this, MyNestedScrollTestActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(MainActivity.this, SimpleDraweeViewActivity.class));
                        break;
                    case 5:
                        startActivity(new Intent(MainActivity.this, RecyclerViewActivity.class));
                        break;
                    case 6:
                        startActivity(new Intent(MainActivity.this, StateViewActivity.class));
                        break;

                }
            }
        });
    }
}