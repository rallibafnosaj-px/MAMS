package Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.survey.GlobalVariables;
import com.example.survey.R;
import com.example.survey.SurveyFormActivity;

import java.util.List;

import Model.DTRModel;
import Model.MerchantModel;


public class MerchantAdapter extends RecyclerView.Adapter<MerchantAdapter.GetViewHolder> {
    Context context;
    List<Model.MerchantModel> list;
    LayoutInflater layoutInflater;
    AlertDialog alertDialog;
    View viewInflater;

   public String visit_date, encoded_by, name_of_mall, maya_status, dba_name, reg_business_name,
            sub_area, merc_spoc_name, merc_spoc_designation, merc_spoc_email, merc_spoc_contact,
            gcash_accept_statc, gcash_accept_qrinsidepos, terminal_avail, other_merc_visible,
            maya_visibility_hidden, maya_visibility_standee, maya_visibility_doorhanger,
            maya_visiblity_none, nonmaya_visiblity_standee, nonmaya_visiblity_doorhanger,
            nonmaya_visibility_none, qr_green_bird, qr_mayatwo, available_sqr, merc_restriction,
            accept_other_qr, maya_device_count, maya_device_sn, maya_sqr_count, store_code,
            transaction_id, complete_delivery_add, remarks, agent_id, agent_name, status, merchant_id,
            trade_assurance, tat_remarks, sync_status;

    public MerchantAdapter(Context context, List<Model.MerchantModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MerchantAdapter.GetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rv_layout_merchant, parent, false);

        return new GetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MerchantAdapter.GetViewHolder holder, int position) {
        Model.MerchantModel data = list.get(position);


        holder.tvmall.setText(data.getName_of_mall());
        holder.tvdbaname.setText(data.getDba_name());
        holder.tvspocname.setText(data.getMerc_spoc_name());
        holder.tvrvid.setText(data.getID());


    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class GetViewHolder extends RecyclerView.ViewHolder {
        TextView tvmall, tvdbaname, tvspocname, tvrvid;

        public GetViewHolder(@NonNull View itemView) {
            super(itemView);


            tvmall = itemView.findViewById(R.id.tv_rvNameOfMall);
            tvdbaname = itemView.findViewById(R.id.tv_rvDba);
            tvspocname = itemView.findViewById(R.id.tv_rvMerchantSPOCName);
            tvrvid = itemView.findViewById(R.id.tv_rvId);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    Activity activity = (Activity) context;
                    Intent intent = new Intent(view.getContext(), SurveyFormActivity.class);
                    GlobalVariables.fromSQLite = true;
                    GlobalVariables.fromDashboard = true;
                    intent.putExtra("ID", tvrvid.getText());





                    activity.startActivity(intent);


                }
            });


        }
    }
}
