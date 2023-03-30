package com.example.survey;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Adapter.ItineraryAdapter;
import Model.GetItineraryModel;

public class Itinerary extends AppCompatActivity {
    RecyclerView rvItinerary;
    private ItineraryAdapter adapter;
    ItineraryAdapter.ItemClickListener itemClickListener;

    SQLite.DashboardActivitySQLite merchantDB = new SQLite.DashboardActivitySQLite(this);
    WebService.ItineraryWebService itiDB = new WebService.ItineraryWebService(this);
    SQLite.DTRActivitySQLite dtrDB = new SQLite.DTRActivitySQLite(this);


    private List<GetItineraryModel> appItinerary;

    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itinerary);
        getSupportActionBar().setTitle("Assigned Itinerary");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        appItinerary = new ArrayList<>();

        VariableCasting();
        SetUpRecyclerViewItinerary();


//        rvItinerary.setAdapter(adapter);
//        rvItinerary.setLayoutManager(new LinearLayoutManager(Itinerary.this));
//        LoadItineraryPending(dtrDB.getAgentID());
    }
    public void onResume() {
        super.onResume();
        GlobalVariables.fromDashboard = false;
        Toast.makeText(this, "haha" + GlobalVariables.fromDashboard , Toast.LENGTH_SHORT).show();
    }
    public void onDestroy() {
        super.onDestroy();
        GlobalVariables.fromDashboard = false;
        Toast.makeText(this, "haha" + GlobalVariables.fromDashboard , Toast.LENGTH_SHORT).show();
    }

    private void LoadItineraryPending(String empid) {

//        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.GETITINERARY,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//                        try {
//                            if (!response.equals("1")) {
//                                JSONArray datas = new JSONArray(response);
//
//                                for (int i = 0; i < datas.length(); i++) {
//
//                                    JSONObject dataObject = datas.getJSONObject(i);
//                                    String id = dataObject.getString("ID").trim();
//                                    String agent = dataObject.getString("Agent").trim();
//                                    String group = dataObject.getString("GroupX").trim();
//                                    String mall = dataObject.getString("Mall").trim();
//                                    String address = dataObject.getString("Address").trim();
//                                    String status = dataObject.getString("Status").trim();
//                                    String agentid = dataObject.getString("AgentID").trim();
//
//                                    GetItineraryModel itineraryModel = new GetItineraryModel(id, agent, group, mall, address, status, agentid);
//                                    appItinerary.add(itineraryModel);
//
//                                }
//                                adapter = new ItineraryAdapter(getApplicationContext(), appItinerary, new ItineraryAdapter.ItemClickListener() {
//                                    @Override
//                                    public void onItemClick(GetItineraryModel details) {
//                                        builder.setMessage("Choose action")
//                                                .setCancelable(true)
//                                                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
//                                                    @Override
//                                                    public void onClick(DialogInterface dialogInterface, int i) {
//                                                        builder.setMessage("Are you sure?")
//                                                                .setCancelable(true)
//                                                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                                                                    @Override
//                                                                    public void onClick(DialogInterface dialogInterface, int i) {
//                                                                        itiDB.UpdateAssignStatus(details.getId(), "0");
//                                                                        Toast.makeText(Itinerary.this, "Task Completed", Toast.LENGTH_SHORT).show();
//                                                                        RefreshIntent();
//                                                                    }
//                                                                })
//                                                                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
//                                                                    @Override
//                                                                    public void onClick(DialogInterface dialogInterface, int i) {
//                                                                        dialogInterface.cancel();
//                                                                    }
//                                                                }).show();
//                                                    }
//                                                })
//                                                .setNegativeButton("Re-assign", new DialogInterface.OnClickListener() {
//                                                    @Override
//                                                    public void onClick(DialogInterface dialogInterface, int i) {
//                                                        builder.setMessage("Are you sure?")
//                                                                .setCancelable(true)
//                                                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                                                                    @Override
//                                                                    public void onClick(DialogInterface dialogInterface, int i) {
//                                                                        itiDB.UpdateAssignStatus(details.getId(), "2");
//                                                                        Toast.makeText(Itinerary.this, "Transaction Re-assigned", Toast.LENGTH_SHORT).show();
//                                                                        RefreshIntent();
//                                                                    }
//                                                                })
//                                                                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
//                                                                    @Override
//                                                                    public void onClick(DialogInterface dialogInterface, int i) {
//                                                                        dialogInterface.cancel();
//                                                                    }
//                                                                }).show();
//                                                    }
//                                                }).show();
//                                    }
//                                });
//                                rvItinerary.setAdapter(adapter);
//                                adapter.notifyDataSetChanged();
//
//                            } else {
//                                appItinerary.clear();
//                                adapter = new ItineraryAdapter(getApplicationContext(), appItinerary, new ItineraryAdapter.ItemClickListener() {
//                                    @Override
//                                    public void onItemClick(GetItineraryModel details) {
////                                        Toast.makeText(Itinerary.this, "" + details.getId(), Toast.LENGTH_SHORT).show();
//
//                                    }
//                                });
//                                rvItinerary.setAdapter(adapter);
//                                adapter.notifyDataSetChanged();
//                                Toast.makeText(Itinerary.this, "Nothing to show", Toast.LENGTH_SHORT).show();
//                            }
//
//                        } catch (Exception e) {
////                    Toast.makeText(activity, "" + response, Toast.LENGTH_SHORT).show();
//                            Log.d(TAG, "APPROVAL LEAVE ADAPTER: " + e.toString());
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.d(TAG, "onErrorResponse: " + error.toString());
//                    }
//                }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//
//                Map<String, String> params = new HashMap<>();
//
//                params.put("AgentID", empid);
//
//                return params;
//            }
//        };
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(stringRequest);
    }

    private void RefreshIntent() {
        finish();
        Intent intent = new Intent(getApplicationContext(), Itinerary.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//        overridePendingTransition(0, 0);
        startActivity(intent);
    }

    private void VariableCasting() {
        rvItinerary = findViewById(R.id.rv_Itinerary);
        builder = new AlertDialog.Builder(this);
    }

    public void SetUpRecyclerViewItinerary() {
//            setOnClickListener();
        LinearLayoutManager layoutManagerDelivery = new LinearLayoutManager(this);
        rvItinerary.setLayoutManager(layoutManagerDelivery);
        rvItinerary.setAdapter(merchantDB.RetrieveUnsyncMerchant());

    }


}