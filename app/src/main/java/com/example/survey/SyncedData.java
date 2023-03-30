package com.example.survey;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Adapter.ItineraryAdapter;
import Adapter.MerchantAdapter;
import Model.GetItineraryModel;
import Model.MerchantModel;

import static android.content.ContentValues.TAG;

public class SyncedData extends AppCompatActivity {
    RecyclerView rvSyncedMerchant;

    TextView tvCountSynced;

    private MerchantAdapter adapter;
    private List<MerchantModel> appMerchantModel;

    AlertDialog.Builder builder;

    SQLite.DTRActivitySQLite offDTRDB = new SQLite.DTRActivitySQLite(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_synced_data);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        appMerchantModel = new ArrayList<>();

        VariableCasting();
        SetUpRecyclerViewSynced();
//        LoadSyncedMerchant(offDTRDB.getEmp());
    }

    private void VariableCasting() {
        rvSyncedMerchant = findViewById(R.id.rv_syncedMerchant);
        tvCountSynced = findViewById(R.id.tv_countSynced);
        builder = new AlertDialog.Builder(this);
    }

    private void SetUpRecyclerViewSynced() {
//            setOnClickListener();
        LinearLayoutManager layoutManagerDelivery = new LinearLayoutManager(this);
        rvSyncedMerchant.setLayoutManager(layoutManagerDelivery);
    }

//    private void LoadSyncedMerchant(String name) {
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.GETSYNCEDMERCHANT,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//                        try {
//                            if (!response.equals("1")) {
//                                JSONArray datas = new JSONArray(response);
//                                int dataLength = datas.length();
//
//                                for (int i = 0; i < datas.length(); i++) {
//
//                                    JSONObject dataObject = datas.getJSONObject(i);
//                                    String nameOfMall = dataObject.getString("NameOfMall").trim();
//                                    String dbaName = dataObject.getString("DBAName").trim();
//                                    String mercSpocName = dataObject.getString("MercSPOCName").trim();
//
//                                    MerchantModel merchantModel = new MerchantModel(nameOfMall, dbaName, mercSpocName);
//                                    appMerchantModel.add(merchantModel);
//                                }
//                                adapter = new MerchantAdapter(getApplicationContext(), appMerchantModel);
//                                tvCountSynced.setText(String.valueOf(dataLength));
//                                rvSyncedMerchant.setAdapter(adapter);
//                                adapter.notifyDataSetChanged();
//
//                            } else {
//                                appMerchantModel.clear();
//                                adapter = new MerchantAdapter(getApplicationContext(), appMerchantModel);
//                                rvSyncedMerchant.setAdapter(adapter);
//                                adapter.notifyDataSetChanged();
//                                Toast.makeText(SyncedData.this, "Nothing to show", Toast.LENGTH_SHORT).show();
//                            }
//
//                        } catch (Exception e) {
////                    Toast.makeText(activity, "" + response, Toast.LENGTH_SHORT).show();
//                            Log.d(TAG, "Merchant ADAPTER: " + e.toString());
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.d(TAG, "onErrorResponse: " + error.toString());
//                    }
//                }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//
//                Map<String,String> params = new HashMap<>();
//
//                params.put("EncodedBy", name);
//
//                return params;
//            }
//        };
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(stringRequest);
//    }
}