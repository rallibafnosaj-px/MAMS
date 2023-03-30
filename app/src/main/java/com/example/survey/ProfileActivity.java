package com.example.survey;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Arrays;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {
    Button btnLogout, btnChangePin;
    BottomNavigationView bottomNavigationView;
    AlertDialog.Builder builder;
    TextView tvEmpName, tvEmpPosition, tvEmpEmail, tvEmpMobile;

    String empName = "", empPosition = "", empEmail = "", empMobile = "";

    Handler handler;
    Runnable runnable;
    ProgressDialog progressDialog;

    SQLite.LoginActivitySQLite offlineDB = new SQLite.LoginActivitySQLite(this);
    SQLite.MainActivitySQLite offMainDB = new SQLite.MainActivitySQLite(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.profile);
        getSupportActionBar().setTitle("My Profile");

        VariableCasting();
        GetEmployee();
        setDetails();

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
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;
                    case R.id.profile:
                        return true;
                }
                return false;
            }
        });

        CheckIfPinExist();

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(offMainDB.CheckIfUnsycMerchant() || offMainDB.CheckIfUnsycDTR()){
                    builder.setTitle("There are unync data(s) in your app.")
                            .setMessage("Sync all unsync data.")
                            .setCancelable(true)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    ManualSync();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    builder.setTitle("Are you sure you want to Logout?")
                                            .setMessage("All the unsync data will be erase.")
                                            .setCancelable(true)
                                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                                    offlineDB.DeleteLoginVerify();
                                                    offlineDB.DeleteAgentDetails();
                                                    offlineDB.DeleteDTR();
                                                    offlineDB.DeleteDTRCounter();
                                                    offlineDB.DeleteMerchant();
                                                    offlineDB.DeleteMerchantVerify();
                                                    offlineDB.DeleteStatus();
                                                    offlineDB.DeleteTerminals();
                                                    offlineDB.DeletePin();
                                                    offlineDB.DeleteMerchantVisit();

                                                    finish();
                                                }
                                            })
                                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    dialogInterface.cancel();
                                                }
                                            }).show();
                                }
                            }).show();
                }else {
                    builder.setTitle("Are you sure you want to Logout?")
                            .setMessage("All the unsync data will be erase.")
                            .setCancelable(true)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                    offlineDB.DeleteLoginVerify();
                                    offlineDB.DeleteAgentDetails();
                                    offlineDB.DeleteDTR();
                                    offlineDB.DeleteDTRCounter();
                                    offlineDB.DeleteMerchant();
                                    offlineDB.DeleteMerchantVerify();
                                    offlineDB.DeleteStatus();
                                    offlineDB.DeleteTerminals();
                                    offlineDB.DeletePin();
                                    offlineDB.DeleteMerchantVisit();

                                    finish();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            }).show();
                }


            }
        });

        btnChangePin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, PinActivity.class));
            }
        });
    }


    private void CheckIfPinExist() {
        if (offMainDB.CheckIfPinExist()) {
            btnChangePin.setText("Change Pin");
        } else {
            btnChangePin.setText("Setup Pin");
        }
    }

    private void setDetails() {
        tvEmpName.setText(empName);
        tvEmpEmail.setText(empEmail);
        tvEmpMobile.setText(empMobile);
    }

    public void GetEmployee() {
        SQLite.ProfileActivitySQLite offdb = new SQLite.ProfileActivitySQLite(this);

        String details = offdb.RetrieveEmp();
        List<String> detailsList = Arrays.asList(details.split("="));

        empName = detailsList.get(0);
        empEmail = detailsList.get(1);
        empMobile = detailsList.get(2);

    }

    private void VariableCasting() {
        btnLogout = findViewById(R.id.btn_logout);
        builder = new AlertDialog.Builder(this);
        btnChangePin = findViewById(R.id.btn_change_pin);
        tvEmpName = findViewById(R.id.tv_emp_name);
        tvEmpPosition = findViewById(R.id.tv_emp_position);
        tvEmpEmail = findViewById(R.id.tv_emp_email);
        tvEmpMobile = findViewById(R.id.tv_emp_mobile_num);
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
                    offMainDB.SyncDTRToLive();
//                    if(offDB.CheckTimeInOut()==true){
//                        offDB.SyncDTRToLive();
//                    }else{
//                        GlobalVariables.processListSync = 2;
//                    }
                }
                if (GlobalVariables.processListSync == 2) {
                    offMainDB.SyncMerchantToLive();
//                    if(offExpenseDB.CheckExpenses()==true){
//                        offExpenseDB.SyncExpenseToLive();
//                        GlobalVariables.thread = true;
//                    }else{
//
//                        GlobalVariables.thread = true;
//                    }
                }

                if (GlobalVariables.processListSync == 3) {
                    offMainDB.SyncMerchantSerialToLive();
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