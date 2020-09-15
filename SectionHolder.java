package com.liner.genericadapter;

import java.util.Collections;
import java.util.List;

public abstract class SectionHolder<T> implements Section<T> {
    public int type;
    public List data;
    private SectionChangeListener listener;

    public SectionHolder(int type, List data) {
        this.type = type;
        this.data = data;
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public boolean isSameType(int type) {
        return type == this.type;
    }

    @Override
    public void clear() {
        data.clear();
        if (listener != null)
            listener.onSectionChanged(data);
    }

    @Override
    public void add(List data) {
        this.data.addAll(data);
        if (listener != null)
            listener.onSectionChanged(data);
    }

    @Override
    public void add(Object item) {
        data.add(item);
        if (listener != null)
            listener.onSectionChanged(data);
    }

    @Override
    public void add(int position, Object item) {
        data.add(position, item);
        if (listener != null)
            listener.onSectionChanged(data);
    }

    @Override
    public void set(int position, Object item) {
        data.set(position, item);
        if (listener != null)
            listener.onSectionChanged(data);
    }

    @Override
    public void remove(Object item) {
        data.remove(item);
        if (listener != null)
            listener.onSectionChanged(data);
    }

    @Override
    public void remove(int position) {
        data.remove(position);
        if (listener != null)
            listener.onSectionChanged(data);
    }

    @Override
    public void swap(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++)
                Collections.swap(data, i, i + 1);
        } else {
            for (int i = fromPosition; i > toPosition; i--)
                Collections.swap(data, i, i - 1);
        }
        if (listener != null)
            listener.onSectionChanged(data);
    }

    @Override
    public void move(int fromPosition, int toPosition) {
        Object item = getItem(fromPosition);
        remove(fromPosition);
        add(toPosition, item);
        if (listener != null)
            listener.onSectionChanged(data);
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    public void setListener(SectionChangeListener listener) {
        this.listener = listener;
    }
}
