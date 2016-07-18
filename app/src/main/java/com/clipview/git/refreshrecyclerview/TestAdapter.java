package com.clipview.git.refreshrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by likuan on 16/6/29.
 */
public class TestAdapter extends BaseAdapter<String, TestAdapter.TestVH> {

    public TestAdapter(Context context, List<String> mMessages) {
        this(context, mMessages, R.layout.item_test);
    }
    public TestAdapter(Context context, List<String> mMessages, int mResources) {
        super(context, mMessages, mResources);
    }

    @Override
    protected TestVH createViewHolder(int viewType, ViewGroup parent) {
        return new TestVH(getView());
    }

    @Override
    protected int setItemViewType(int position) {
        return 0;
    }

    @Override
    protected void setOnBindViewHolder(TestVH viewHolder, int position) {
        if(viewHolder instanceof TestVH){
            viewHolder.setContents(position);
        }
    }

    protected class TestVH extends RecyclerView.ViewHolder{
        private final TextView tv_text;
        private final ImageView iv_img;
        public TestVH(View itemView) {
            super(itemView);
            iv_img = (ImageView)itemView.findViewById(R.id.iv_img);
            tv_text = (TextView)itemView.findViewById(R.id.tv_text);
        }

        private void setContents(int position){
            String content = mMessages.get(position);
            if(scroll){//滚动不加载图片
                iv_img.setImageResource(R.mipmap.ic_launcher);
                tv_text.setText("加载中...");
            }else {//加载图片
                iv_img.setImageResource(R.mipmap.ic_twitter);
                tv_text.setText(content + " - " + position);
            }
        }
    }
}
