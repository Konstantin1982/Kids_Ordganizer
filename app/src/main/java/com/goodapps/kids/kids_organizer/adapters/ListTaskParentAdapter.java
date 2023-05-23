package com.goodapps.kids.kids_organizer.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.goodapps.kids.kids_organizer.R;

import java.text.ParseException;

public class ListTaskParentAdapter extends TaskListAdapter<ListTaskParentAdapter.ListTaskParentAdapterViewHolder> {

    public ListTaskParentAdapter(Context context) throws ParseException {
        super(context);
    }

    @NonNull
    @Override
    public ListTaskParentAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_list_task_parent, parent, false);
        return new ListTaskParentAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListTaskAdapterViewHolder holder, int position) {
        Log.e("0000", "I'm here");
        super.onBindViewHolder(holder, position);
        ListTaskParentAdapterViewHolder holder1 = (ListTaskParentAdapterViewHolder) holder;
        holder1.assigneeTextView.setText(mCurrentTask.assignee);
    }

    class ListTaskParentAdapterViewHolder extends ListTaskAdapterViewHolder {
        public TextView assigneeTextView;

        public ListTaskParentAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            assigneeTextView = itemView.findViewById(R.id.childNameInList);
        }
    }

}

