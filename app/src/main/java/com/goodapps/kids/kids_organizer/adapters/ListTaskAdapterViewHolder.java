package com.goodapps.kids.kids_organizer.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.goodapps.kids.kids_organizer.R;

class ListTaskAdapterViewHolder extends RecyclerView.ViewHolder {
    protected TextView taskTitleTextView;
    protected TextView taskDescriptionTextView;
    protected TextView taskEndTextView;

    public ListTaskAdapterViewHolder(@NonNull View itemView) {
        super(itemView);
        taskTitleTextView = itemView.findViewById(R.id.taskTitleTextView);
        taskDescriptionTextView = itemView.findViewById(R.id.taskDescriptionTextView);
        taskEndTextView = itemView.findViewById(R.id.taskEndTextView);
    }
}
