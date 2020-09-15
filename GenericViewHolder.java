package com.liner.genericadapter;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GenericViewHolder extends RecyclerView.ViewHolder {
    public final View itemView;
    private SparseArray<View> viewSparseArray;

    public GenericViewHolder(ViewGroup parent, int layoutResource) {
        this(LayoutInflater.from(parent.getContext()).inflate(layoutResource, parent, false));
    }

    public GenericViewHolder(@NonNull View itemView) {
        super(itemView);
        this.itemView = itemView;
        viewSparseArray = new SparseArray<>();
    }

    private View fvb(int id) {
        return itemView.findViewById(id);
    }

    public <T extends View> T getView(int id) {
        View view = viewSparseArray.get(id);
        if (view == null) {
            view = fvb(id);
            viewSparseArray.put(id, view);
        }
        return (T) view;
    }

}
