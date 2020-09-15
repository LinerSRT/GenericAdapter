package com.liner.genericadapter;

import android.util.SparseArray;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;

public abstract class Binder<T> {
    @NonNull
    private View itemView;
    private SparseArray<View> viewSparseArray = new SparseArray<>();

    void setItemView(@NonNull View itemView) {
        this.itemView = itemView;
    }

    public abstract void init();

    public abstract void bind(@NonNull T model);

    protected <V> V find(@IdRes int id) {
        View view = viewSparseArray.get(id);
        if (view == null) {
            view = f(id);
            viewSparseArray.put(id, view);
        }
        return (V) view;
    }

    private View f(@IdRes int id){
        return itemView.findViewById(id);
    }
}
