package image.manual.android.demotasks.adapters;

import android.content.Context;
import android.graphics.Color;
import android.provider.CalendarContract;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import image.manual.android.demotasks.R;
import image.manual.android.demotasks.networks.GetTasksResponse;

/**
 * Created by EDGY on 5/30/2017.
 */

public class TaskPagerAdapter extends RecyclerView.Adapter<TaskPagerAdapter.TaskViewHolder>{
    private Context context;
    private List<GetTasksResponse> getTasksResponses;

    public TaskPagerAdapter(List<GetTasksResponse> getTasksResponses, Context context) {
        this.context = context;
        this.getTasksResponses = getTasksResponses;
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_task_view, parent, false);
        return new TaskViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        holder.itemView.setBackgroundColor(Color.parseColor(getTasksResponses.get(position).getColor()));
        holder.setData(getTasksResponses.get(position));
    }

    @Override
    public int getItemCount() {
        return getTasksResponses.size();
    }


    public class TaskViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_payment)
        TextView tvPayment;
        @BindView(R.id.tv_due_date)
        TextView tvDueDate;
        @BindView(R.id.cb_done)
        CheckBox cbDone;

        public TaskViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(GetTasksResponse getTasksResponse){
            tvName.setText(getTasksResponse.getName());
            tvPayment.setText("Payment per hour : "+ String.valueOf(getTasksResponse.getPaymentPerHour()));
            tvDueDate.setText(getTasksResponse.getDueDate().toString());
            if(getTasksResponse.isDone()){
                cbDone.setChecked(true);
            }
        }
    }
}
