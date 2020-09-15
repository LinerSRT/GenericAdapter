package com.liner.genericadapter;

import java.util.List;

public interface Section<T>{
    int getType();
    boolean isSameType(int type);
    void bind(GenericViewHolder viewHolder, int position, T model);
    void clear();
    void add(List data);
    void add(Object item);
    void add(int position, Object item);
    void set(int position, Object item);
    void remove(Object item);
    void remove(int position);
    void swap(int fromPosition, int toPosition);
    void move(int fromPosition, int toPosition);
    Object getItem(int position);
}
