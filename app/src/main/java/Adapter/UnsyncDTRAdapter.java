package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.survey.R;

import java.util.List;

import Model.DTRModel;
import Model.GetItineraryModel;

public class UnsyncDTRAdapter extends RecyclerView.Adapter<UnsyncDTRAdapter.GetViewHolder> {
    Context context;
    List<Model.DTRModel> list;


    public UnsyncDTRAdapter(Context context, List<Model.DTRModel> list) {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public UnsyncDTRAdapter.GetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rv_layout_dtr, parent, false);

        return new GetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UnsyncDTRAdapter.GetViewHolder holder, int position) {

        Model.DTRModel data = list.get(position);

        holder.tv_date.setText(data.getDate());

        String timeout = data.getTimeout().toString();

        if(timeout.equals("null null"))
        {
            holder.tv_timeIN.setText(data.getTimein());
            holder.tv_timeOUT.setText("Waiting for your time out...");
        }
        else
        {
            holder.tv_timeIN.setText(data.getTimein());
            holder.tv_timeOUT.setText(data.getTimeout());
        }



    }

    @Override
    public int getItemCount() {
            return list.size();
    }

    public class GetViewHolder extends RecyclerView.ViewHolder {
        TextView tv_timeIN, tv_timeOUT, tv_date;
        public GetViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_date = itemView.findViewById(R.id.tv_rvDate);
            tv_timeIN = itemView.findViewById(R.id.tv_rvTimein);
            tv_timeOUT = itemView.findViewById(R.id.tv_rvTimeout);
        }
    }
}
