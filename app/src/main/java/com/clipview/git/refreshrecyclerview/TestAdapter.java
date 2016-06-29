package com.clipview.git.refreshrecyclerview;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
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
        private TextView itemView;
        public TestVH(View itemView) {
            super(itemView);
            this.itemView = (TextView) itemView;
            ViewGroup.LayoutParams layoutParams = itemView.getLayoutParams();
            layoutParams.height = getHeight() / 8;
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        }

        private void setContents(int position){
            String content = mMessages.get(position);
            itemView.setText(content + " - " + position);
        }
    }

    private int getWidth(){
        Resources resources = context.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        int widthPx = displayMetrics.widthPixels;
        return widthPx;
    }

    private int getHeight(){
        Resources resources = context.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        int heightPx = displayMetrics.heightPixels;
        return heightPx;
    }
}
