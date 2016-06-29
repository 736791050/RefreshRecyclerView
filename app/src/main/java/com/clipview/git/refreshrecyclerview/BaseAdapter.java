package com.clipview.git.refreshrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by thinkpad on 2016/3/4.
 */
public abstract class BaseAdapter<T,K extends ViewHolder> extends Adapter<K> {

    protected final LayoutInflater layoutInflater;
    public List<T> mMessages;
    private static final int TYPE_FOOTER = -2;
    public boolean hasMore;
    public Context context;
    private int  mResources;
    private ItemOnclickLinstener itemOnclickLinstener;

    public BaseAdapter(Context context, List<T> mMessages, int mResources) {
        this.mMessages = mMessages;
        this.context = context;
        this.mResources = mResources;
        layoutInflater = LayoutInflater.from(context);
        hasMore = false;
    }

    @Override
    public K onCreateViewHolder(ViewGroup parent, int viewType) {
        // type == TYPE_FOOTER 返回footerView
        if (viewType == TYPE_FOOTER) {
            View view = layoutInflater.inflate(R.layout.item_footer, null);
            view.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            return (K)new FooterViewHolder(view);
        }else {
            final K vh = createViewHolder(viewType, parent);
            vh.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(itemOnclickLinstener != null) {
                        itemOnclickLinstener.setItemOnclickLinsteners(v, vh.getLayoutPosition(), vh.getItemViewType());
                    }
                }
            });
            return vh;
        }
    }

    @Override
    public void onBindViewHolder(final K viewHolder, final int position) {
        if(!(hasMore && position == getItemCount() -1)){
            setOnBindViewHolder(viewHolder, position);
        }
    }

    @Override
    public int getItemCount() {
        int size = 0;
        if(mMessages != null){
            size = mMessages.size();
        }
        return hasMore ? size + 1 : size;
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount() && hasMore) {
            return TYPE_FOOTER;
        } else {
            return setItemViewType(position);
        }
    }

    /**
     * footer ViewHolder
     */
    class FooterViewHolder extends ViewHolder {
        public FooterViewHolder(View view) {
            super(view);
        }
    }

    /**
     * 是否显示加载更多
     * @param hasMore
     */
    public void setHasMore(boolean hasMore){
        this.hasMore = hasMore;
    }

    /**
     * 创建ViewHolder
     * @param viewType
     * @return
     */
    protected abstract K createViewHolder(int viewType, ViewGroup parent);

    /**
     * 设置ViewHolder类型
     * @param position
     * @return
     */
    protected abstract int setItemViewType(int position);

    /**
     * 数据填充
     */
    protected abstract void setOnBindViewHolder(K viewHolder, int position);

    /**
     * Item点击事件
     * @param
     * @return
     */
    public void setItemOnClickLinsteners(ItemOnclickLinstener itemOnclickLinstener){
        this.itemOnclickLinstener = itemOnclickLinstener;
    }

    /**
     * item点击接口
     */
    public interface ItemOnclickLinstener{
        void setItemOnclickLinsteners(View v, int postion, int type);
    }

    protected View getView(int resource){
        View view = layoutInflater.inflate(resource, null);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        return view;
    }

    protected View getView(){
        View view = layoutInflater.inflate(mResources, null);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        return view;
    }

    protected View getView(ViewGroup parent){
        View view = layoutInflater.inflate(mResources, parent, false);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        return view;
    }

}
