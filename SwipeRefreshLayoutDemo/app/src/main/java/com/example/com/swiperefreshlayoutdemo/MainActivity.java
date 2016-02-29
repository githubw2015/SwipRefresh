package com.example.com.swiperefreshlayoutdemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout mSwipeLayout;
    private ListView mListView;
    private ArrayList<String> list = new ArrayList<String>();
    ArrayAdapter adapter;

    //1. setOnRefreshListener()触发刷新回调方法
    // 2. setRefreshing(false)设置刷新完成，进度圆圈圈停止转动。
    // 3. setEnabled(false)可以禁止下拉刷新
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        //findview控件
        mListView = (ListView) findViewById(R.id.listview);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getData());
        //listview设置数据适配器
        mListView.setAdapter(adapter);
        mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        //触发刷新回调函数
        mSwipeLayout.setOnRefreshListener(MainActivity.this);
        //设置加载圈圈的颜色
        mSwipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        //设置加载圈的背景颜色
        mSwipeLayout.setProgressBackgroundColorSchemeResource(R.color.link_text_material_light);
    }

    //返回list数据
    private ArrayList<String> getData() {
        for (int i = 0; i < 10; i++) {
            list.add("计算机" + i);
        }
        return list;
    }

    //设置刷新完成，进度圆圈圈停止转动。
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                list.add(0, "111111");
                list.add(0, "222");
                adapter.notifyDataSetChanged();
                mSwipeLayout.setRefreshing(false);
            }
        }, 3000);
    }

}
