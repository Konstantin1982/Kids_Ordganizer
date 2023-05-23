package com.goodapps.kids.kids_organizer.adapters;
import android.content.Context;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.goodapps.kids.kids_organizer.helpers.data.OrganizerTask;
import com.goodapps.kids.kids_organizer.helpers.utils.OrganizerTasksHelper;

import java.text.ParseException;

public abstract class TaskListAdapter<L extends ListTaskAdapterViewHolder> extends RecyclerView.Adapter<ListTaskAdapterViewHolder>  {

    protected Context mContext;
    protected OrganizerTasksHelper mTaskHelper;
    protected OrganizerTask mCurrentTask;

    public TaskListAdapter(Context context) throws ParseException {
        mContext = context;
        mTaskHelper = new OrganizerTasksHelper(mContext);
    }

    @NonNull
    public abstract  ListTaskAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(@NonNull ListTaskAdapterViewHolder holder, int position) {
        mCurrentTask = mTaskHelper.getTaskFromList(position);
        holder.taskTitleTextView.setText(mCurrentTask.name);
        holder.taskDescriptionTextView.setText(mCurrentTask.description);
        holder.taskEndTextView.setText(mCurrentTask.toDate + " " + mCurrentTask.toTime);
    }

    @Override
    public int getItemCount() {
        return mTaskHelper.getCount();
    }

    public void updateListValues(int showCompleted, String dateFilter) throws ParseException {
        mTaskHelper.UpdateListOfTasks(showCompleted, dateFilter);
        notifyDataSetChanged();
    }

}
