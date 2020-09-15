package com.liner.genericadapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.liner.linerlauncher.util.Lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("unchecked")
public class GenericSectionAdapter<T> extends RecyclerView.Adapter<GenericViewHolder> implements SectionChangeListener{
    private List<SectionHolder> sectionList;
    private List<Object> dataSet;

    public GenericSectionAdapter() {
        sectionList = new ArrayList<>();
        dataSet = new ArrayList<>();
    }

    public void register(SectionHolder sectionHolder) {
        sectionHolder.setListener(this);
        sectionList.add(sectionHolder);
        dataSet.addAll(sectionHolder.data);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if(dataSet.isEmpty()){
            return sectionList.get(position).type;
        }
        for (SectionHolder sectionHolder : sectionList) {
            if (sectionHolder.data.contains(dataSet.get(position)))
                return sectionHolder.type;
        }
        return 0;
    }

    private SectionHolder getSection(int viewType) {
        for (SectionHolder sectionHolder : sectionList) {
            if (sectionHolder.type == viewType)
                return sectionHolder;
        }
        throw new RuntimeException("Cannot find section with given view type!");
    }

    @NonNull
    @Override
    public GenericViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0)
            throw new RuntimeException("Cannot bind this section with layout id 0");
        return new GenericViewHolder(parent, viewType);
    }

    private int positionFix = 0;

    @Override
    public void onBindViewHolder(@NonNull GenericViewHolder holder, int position) {
        int type = getItemViewType(position);
        if(type != 0) {
            SectionHolder sectionHolder = getSection(type);
            if(position > sectionHolder.data.size()-1 && positionFix == 0)
                positionFix += position;
            sectionHolder.bind(holder, position-positionFix, sectionHolder.getItem(position-positionFix));
        }
    }


    @Override
    public int getItemCount() {
        int count = 0;
        for(SectionHolder sectionHolder:sectionList)
            count += sectionHolder.data.size();
        return count;
    }

    @Override
    public void onSectionChanged(List data) {
        dataSet.removeAll(Lists.getRemovedItems(dataSet, data));
        dataSet.addAll(Lists.getAddedItems(dataSet, data));
        notifyDataSetChanged();
    }
}
