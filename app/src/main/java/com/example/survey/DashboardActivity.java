package com.example.survey;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import Adapter.MerchantAdapter;
import Adapter.UnsyncDTRAdapter;
import Model.MerchantModel;
import SQLite.SQLiteDatabaseTemplate;

import static android.content.ContentValues.TAG;

public class DashboardActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    RecyclerView rvUnsyncDTR, rvUnsyncMerchant, rvUnsyncMerchantImages;

    Button btnSyncedMerchant;

    Handler handler;
    Runnable runnable;
    ProgressDialog progressDialog;

    AlertDialog.Builder builder;

    SQLite.DashboardActivitySQLite offlineDB = new SQLite.DashboardActivitySQLite(this);
    SQLite.MainActivitySQLite offDB = new SQLite.MainActivitySQLite(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getSupportActionBar().setTitle("Dashboard");

        VariableCasting();
        SetUpRecyclerView();


        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.dashboard);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.dashboard:
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                }
                return false;
            }
        });

        btnSyncedMerchant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SyncedData.class));
            }
        });
    }

    private void VariableCasting() {

        rvUnsyncDTR = findViewById(R.id.rv_dtr);
        rvUnsyncMerchant = findViewById(R.id.rv_merchant);
        btnSyncedMerchant = findViewById(R.id.btn_syncedMerchant);
        builder = new AlertDialog.Builder(this);
    }

    private void SetUpRecyclerView() {

        LinearLayoutManager layoutManagerDelivery = new LinearLayoutManager(this);
        rvUnsyncDTR.setLayoutManager(layoutManagerDelivery);

        rvUnsyncDTR.setAdapter(offlineDB.RetrieveUnsyncDTR());

        LinearLayoutManager layoutManagerDTR = new LinearLayoutManager(this);
        rvUnsyncMerchant.setLayoutManager(layoutManagerDTR);
        rvUnsyncMerchant.setAdapter(offlineDB.RetrieveUnsyncMerchant());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sync, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int item_id = item.getItemId();
        if (item_id == R.id.sync) {
            ManualSync();
        }
        return true;
    }

    public void ManualSync() {

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Connecting to the Server.");
        progressDialog.setMessage("Syncing Data");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        progressDialog.show();

        GlobalVariables.processListSync = 1;

        handler = new Handler(Looper.myLooper());
        runnable = new Runnable() {
            @Override
            public void run() {

                if (!GlobalVariables.thread) {

                    // starting the loop
                    handler.postDelayed(runnable, 1000);

                    CheckSystemProcessSync();

                } else {

                    handler.removeCallbacks(runnable);
                    progressDialog.dismiss();
                    GlobalVariables.thread = false;
                    GlobalVariables.processListSync = 0;
                }

            }

            private void CheckSystemProcessSync() {

                if (GlobalVariables.processListSync == 1) {
                    offDB.SyncDTRToLive();
//                    if(offDB.CheckTimeInOut()==true){
//                        offDB.SyncDTRToLive();
//                    }else{
//                        GlobalVariables.processListSync = 2;
//                    }
                }
                if (GlobalVariables.processListSync == 2) {
                    offDB.SyncMerchantToLive();
//                    if(offExpenseDB.CheckExpenses()==true){
//                        offExpenseDB.SyncExpenseToLive();
//                        GlobalVariables.thread = true;
//                    }else{
//
//                        GlobalVariables.thread = true;
//                    }
                }

                if (GlobalVariables.processListSync == 3) {
                    offDB.SyncMerchantSerialToLive();
                    GlobalVariables.thread = true;
//                    if(offExpenseDB.CheckExpenses()==true){
//                        offExpenseDB.SyncExpenseToLive();
//                        GlobalVariables.thread = true;
//                    }else{
//
//                        GlobalVariables.thread = true;
//                    }
                }

                if (GlobalVariables.processListSync == 0) {
                    progressDialog.setMessage(GlobalVariables.progressDialogMessage);
                }
            }

        };


        handler.postDelayed(runnable, 1000);


    }
}