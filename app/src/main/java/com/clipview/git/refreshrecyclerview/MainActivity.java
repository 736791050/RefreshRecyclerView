package com.clipview.git.refreshrecyclerview;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RefreshRecyclerView.LoadLinsteners{

    private RefreshRecyclerView mRecyclerView;
    private TestAdapter mAdapter;
    private ArrayList<String> list;
    private static final int FIRST = 1;
    private static final int REFRESH = 2;
    private static final int LOADMORE = 3;

    private int state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RefreshRecyclerView)$(R.id.mRefreshRecyclerView);

        list = new ArrayList<>();

        mAdapter = new TestAdapter(this, list);

        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setLoadLinsteners(this);

        mAdapter.setItemOnClickLinsteners(new BaseAdapter.ItemOnclickLinstener() {
            @Override
            public void setItemOnclickLinsteners(View v, int postion, int type) {
                //设置item的点击事件

            }
        });

        state = FIRST;

        doActionGetContent();

    }

    private void doActionGetContent() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        final boolean requestOk = true;

        //请求结果进行进一步操作
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(requestOk) {//请求成功
                    if (state == FIRST || state == REFRESH) {
                        list.clear();
                        mRecyclerView.onStopRefresh();
                        //根据需要来写是否有加载更多
                        mRecyclerView.setHasMore(true);
                    } else if (state == LOADMORE) {
                        mRecyclerView.onStopMore();
                        mRecyclerView.setHasMore(false);
                    }
                    addContents();
                    mAdapter.notifyDataSetChanged();
                }else {//请求失败
                    Toast.makeText(MainActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                    if(state == LOADMORE){
                        mRecyclerView.onStopMore();
                    }else if(state == REFRESH){
                        mRecyclerView.onStopRefresh();
                    }
                }
            }
        });
    }

    private void addContents() {
        int size = list.size();
        for(int i = 0; i < 15; i ++){
            list.add("测试内容" + (size + i));
        }
    }

    private View $(int resource){
        return findViewById(resource);
    }

    @Override
    public void onLoadMore() {
        state = LOADMORE;
        doActionGetContent();
    }

    @Override
    public void onRefresh() {
        state = REFRESH;
        doActionGetContent();
    }
}
