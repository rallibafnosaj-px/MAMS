package Adapter;

import android.content.Context;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.survey.R;

import java.util.ArrayList;
import java.util.List;

import Model.GetItineraryModel;

import static android.content.ContentValues.TAG;

public class ItineraryAdapter extends RecyclerView.Adapter<ItineraryAdapter.GetViewHolder> {
    Context context;
    List<Model.GetItineraryModel> list;
    private ItemClickListener mItemListener;

    public ItineraryAdapter(Context context, List<Model.GetItineraryModel> list, ItemClickListener itemClickListener) {
        this.context = context;
        this.list = list;
        this.mItemListener = itemClickListener;

    }

    @NonNull
    @Override
    public ItineraryAdapter.GetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rv_layout_itinerary, parent, false);

        return new ItineraryAdapter.GetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItineraryAdapter.GetViewHolder holder, int position) {

        GetItineraryModel data = list.get(position);

//        holder.id.setText(data.getId());
        holder.name.setText(data.getAgent());
//        holder.groupx.setText(data.getGroupX());
        holder.mall.setText(data.getMall());
        holder.address.setText(data.getAddress());
//        holder.status.setText(data.getStatus());
//        holder.agentid.setText(data.getAgentID());

        Log.d(TAG, "onBindViewHolder: " + data.getAgentID());

        holder.itemView.setOnClickListener(view -> {
            mItemListener.onItemClick(list.get(position));
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface ItemClickListener {
        void onItemClick(GetItineraryModel details);
    }

    public class GetViewHolder extends RecyclerView.ViewHolder {
        TextView id, name, groupx, mall, address, status, agentid;

        public GetViewHolder(@NonNull View itemView) {
            super(itemView);

            mall = itemView.findViewById(R.id.tv_rvMall);
            name = itemView.findViewById(R.id.tv_rvName);
            address = itemView.findViewById(R.id.tv_rvAddress);

        }

    }
}
