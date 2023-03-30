package com.example.survey;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    AlertDialog.Builder builder;

    CardView cvDtr, cvLeave;

    Handler handler;
    Runnable runnable;
    ProgressDialog progressDialog;


    SQLite.MainActivitySQLite offDB = new SQLite.MainActivitySQLite(this);
    SQLite.DTRActivitySQLite dtrDB = new SQLite.DTRActivitySQLite(this);
    SQLite.SurveyFormActivitySQLite surveyDB = new SQLite.SurveyFormActivitySQLite(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Home");


        VariableCasting();
        CheckIfPinExist();
        AutoSync();

        bottomNavigationView.setSelectedItemId(R.id.home);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.dashboard:
                        startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;
                    case R.id.home:
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;
                }
                return false;
            }
        });

        cvDtr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!dtrDB.CheckDTRTimeIn()) {
                    startActivity(new Intent(getApplicationContext(), DTRActivity.class));

                } else if (!offDB.CheckMerchantVerify()) {
                    startActivity(new Intent(getApplicationContext(), ChooseTransactionActivity.class));

                } else {
                    startActivity(new Intent(getApplicationContext(), DTRActivity.class));

                }
            }
        });

        cvLeave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(getApplicationContext(), Itinerary.class));
            }
        });


    }

    private void AutoSync() {
        GlobalVariables.processListSync = 11;
            if (GlobalVariables.processListSync == 11) {
                offDB.AutoSyncDTRToLive();
            }
            if (GlobalVariables.processListSync == 12) {
                offDB.AutoSyncMerchantToLive();
            }

            if (GlobalVariables.processListSync == 13) {
                offDB.AutoSyncMerchantSerialToLive();

            }
            if (GlobalVariables.processListSync == 0) {
            }
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

    private void VariableCasting() {
        bottomNavigationView = findViewById(R.id.bottom_navigator);
        builder = new AlertDialog.Builder(this);
        cvDtr = findViewById(R.id.cv_dtr);
        cvLeave = findViewById(R.id.cv_leave);
    }

    private void CheckIfPinExist() {
        if (!offDB.CheckIfPinExist()) {
//            builder.setTitle("Please setup your pin")
//                    .setMessage("Click setup pin")
//                    .setCancelable(true)
//                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
//                            finish();
//                        }
//                    })
//                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            dialogInterface.cancel();
//                        }
//                    }).show();

            startActivity(new Intent(getApplicationContext(), PinActivity.class));
            finish();
        }
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