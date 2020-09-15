package com.liner.genericadapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class GenericAdapter<T> extends RecyclerView.Adapter<GenericViewHolder> {

    public int layoutID;
    public List<T> data;

    public GenericAdapter(int layoutID) {
        this(layoutID, new ArrayList<T>());
    }

    public GenericAdapter(int layoutID, List<T> data) {
        this.layoutID = layoutID;
        this.data = data;
    }

    @NonNull
    @Override
    public GenericViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GenericViewHolder(parent, layoutID);
    }

    @Override
    public void onBindViewHolder(@NonNull GenericViewHolder holder, final int position) {
        bindAdapter(holder, position, getItem(position));
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public abstract void bindAdapter(GenericViewHolder holder, int position, T item);


    public void init(List<T> data) {
        if (data == null)
            return;
        this.data = data;
        notifyDataSetChanged();
    }

    public void clear() {
        if (data == null)
            return;
        data.clear();
        notifyDataSetChanged();
    }

    public void add(List<T> data) {
        if (this.data == null)
            this.data = new ArrayList<>();
        int position = this.data.size();
        this.data.addAll(data);
        notifyItemRangeChanged(position, data.size());
    }

    public void add(T item) {
        if (data == null)
            data = new ArrayList<>();
        data.add(item);
        notifyItemInserted(data.size());
    }

    public void add(int position, T item) {
        if (data == null)
            data = new ArrayList<>();
        data.add(position, item);
        notifyItemInserted(position);
    }

    public void set(int position, T item) {
        if (data.size() < position)
            return;
        data.set(position, item);
        notifyItemChanged(position);
    }

    public void remove(T item) {
        if (data == null || !data.contains(item))
            return;
        remove(data.indexOf(item));
    }

    public void remove(int position) {
        if (data == null || data.size() < position)
            return;
        data.remove(position);
        notifyItemRemoved(position);
    }

    public List<T> getData() {
        return data;
    }

    @Nullable
    public T getItem(int position) {
        if (data == null || data.size() < position)
            return null;
        return data.get(position);
    }
}
