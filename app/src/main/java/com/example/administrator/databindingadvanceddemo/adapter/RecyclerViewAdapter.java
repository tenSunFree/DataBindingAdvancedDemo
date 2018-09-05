package com.example.administrator.databindingadvanceddemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.databindingadvanceddemo.bean.Content;
import com.example.administrator.databindingadvanceddemo.databinding.RecyclerViewContentItemBinding;
import com.example.administrator.databindingadvanceddemo.databinding.RecyclerViewLastItemBinding;
import com.example.administrator.databindingadvanceddemo.databinding.RecyclerViewTitleItemBinding;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ContentViewHolder> {

    private List<Content> contentList;
    private final LayoutInflater layoutInflater;
    private OnItemClickListener mOnItemClickListener;
    private RecyclerViewTitleItemBinding recyclerViewTitleItemBinding;
    private RecyclerViewContentItemBinding recyclerViewContentItemBinding;
    private RecyclerViewLastItemBinding recyclerViewLastItemBinding;

    public enum ITEM_TYPE {
        ITEM_TYPE_TITLE,
        ITEM_TYPE_CONTENT,
        ITEM_TYPE_LAST
    }

    @Override
    public int getItemViewType(int position) {
        if (position < contentList.size() + 1) {
            return position == 0 ? ITEM_TYPE.ITEM_TYPE_TITLE.ordinal() : ITEM_TYPE.ITEM_TYPE_CONTENT.ordinal();
        } else {
            return ITEM_TYPE.ITEM_TYPE_LAST.ordinal();
        }
    }

    public RecyclerViewAdapter(Context context, List<Content> contentList) {
        this.layoutInflater = LayoutInflater.from(context);
        this.contentList = contentList;
    }

    @Override
    public ContentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        recyclerViewTitleItemBinding = RecyclerViewTitleItemBinding.inflate(layoutInflater, parent, false);
        recyclerViewContentItemBinding = RecyclerViewContentItemBinding.inflate(layoutInflater, parent, false);
        recyclerViewLastItemBinding = RecyclerViewLastItemBinding.inflate(layoutInflater, parent, false);
        if (viewType == ITEM_TYPE.ITEM_TYPE_TITLE.ordinal()) {
            return new ContentViewHolder(recyclerViewTitleItemBinding);
        } else if (viewType == ITEM_TYPE.ITEM_TYPE_CONTENT.ordinal()) {
            return new ContentViewHolder(recyclerViewContentItemBinding);
        } else {
            return new ContentViewHolder(recyclerViewLastItemBinding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final ContentViewHolder holder, final int position) {
        if (!(position == 0) && !(position == contentList.size() + 1)) {
            Content content = contentList.get(position-1);
            holder.bind(content);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onItemClick(holder.itemView, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contentList.size() + 2;
    }

    /**
     * Content類型的ViewHolder
     */
    static class ContentViewHolder extends RecyclerView.ViewHolder {
        private RecyclerViewTitleItemBinding recyclerViewTitleItemBinding;
        private RecyclerViewContentItemBinding recyclerViewContentItemBinding;
        private RecyclerViewLastItemBinding recyclerViewLastItemBinding;
        public ContentViewHolder(RecyclerViewTitleItemBinding recyclerViewTitleItemBinding) {
            super(recyclerViewTitleItemBinding.getRoot());
            this.recyclerViewTitleItemBinding = recyclerViewTitleItemBinding;
        }
        public ContentViewHolder(RecyclerViewContentItemBinding recyclerViewContentItemBinding) {
            super(recyclerViewContentItemBinding.getRoot());
            this.recyclerViewContentItemBinding = recyclerViewContentItemBinding;
        }
        public ContentViewHolder(RecyclerViewLastItemBinding recyclerViewLastItemBinding) {
            super(recyclerViewLastItemBinding.getRoot());
            this.recyclerViewLastItemBinding = recyclerViewLastItemBinding;
        }
        void bind(Content content) {
            if (recyclerViewTitleItemBinding != null) {
                recyclerViewTitleItemBinding.setContent(content);
                recyclerViewTitleItemBinding.executePendingBindings();
            }
            if (recyclerViewContentItemBinding != null) {
                recyclerViewContentItemBinding.setContent(content);
                recyclerViewContentItemBinding.executePendingBindings();
            }
            if (recyclerViewLastItemBinding != null) {
                recyclerViewLastItemBinding.setContent(content);
                recyclerViewLastItemBinding.executePendingBindings();
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}
