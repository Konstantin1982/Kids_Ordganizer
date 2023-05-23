package com.goodapps.kids.kids_organizer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.goodapps.kids.kids_organizer.R;

import java.text.ParseException;

public class ListTaskChildAdapter extends TaskListAdapter<ListTaskAdapterViewHolder> {

    public ListTaskChildAdapter(Context context) throws ParseException {
        super(context);
    }

    @NonNull
    @Override
    public ListTaskAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_list_task_child, parent, false);
        return new ListTaskAdapterViewHolder(view);
    }

}
