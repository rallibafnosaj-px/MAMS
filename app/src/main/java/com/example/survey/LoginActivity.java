package com.example.survey;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nabinbhandari.android.permissions.PermissionHandler;
import com.nabinbhandari.android.permissions.Permissions;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    EditText etUsername;
    EditText etPassword;
    ProgressDialog progressDialog;

    Handler handler;
    Runnable runnable;

    SQLite.LoginActivitySQLite offlineDB = new SQLite.LoginActivitySQLite(this);
    SQLite.SQLiteDatabaseTemplate createDB = new SQLite.SQLiteDatabaseTemplate(this);
    WebService.LoginActivityWebService onlineDB = new WebService.LoginActivityWebService(this);
    StringValidation stringValidation = new StringValidation();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        VariableCasting();
        SettingUpURL();
        CheckPermissions();
        CheckIfUserLogin();


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ValidationLogin();

//                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                startActivity(intent);
//                finish();
            }
        });
    }

    private void CheckPermissions() {

        String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};
        Permissions.check(this/*context*/, permissions, null/*rationale*/, null/*options*/, new PermissionHandler() {

            @Override
            public void onGranted() {
            }

            public void onDenied(Context context, ArrayList<String> deniedPermissions) {
                Toast.makeText(getApplicationContext(), "Please enable permissions at the setting.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void ValidationLogin() {

        String email;
        String password;

        email = etUsername.getText().toString();
        password = etPassword.getText().toString();

        if (stringValidation.HasNull(email) || stringValidation.HasNull(password)) {
            Toast.makeText(this, "Please fill up the missing fields.", Toast.LENGTH_SHORT).show();
        } else if (stringValidation.HasSpecialCharacters(password)) {
            Toast.makeText(this, "Please remove special characters.", Toast.LENGTH_SHORT).show();
        } else {

            LoginAccount(email, password);
//            onlineDB.LoginAccount(email, password);
//            onlineDB.CheckDeviceID(offlineDB.getEmpID(), GlobalVariables.IMEIID);
//            olStoreDB.GetStoresFromLive(offlineDB.getEmpID());
//            olStoreDB.GetStoreAreaFromLive();
//            olRequestDB.GetLeaveTypeFromLive();
//            olExpenseDB.GetExpenseTypeFromLive();

        }

    }

    private void CheckIfUserLogin() {
        if (offlineDB.CheckIfSomeoneLogin().equals("1")) {
            if (offlineDB.getPinCode().equals("empty")) {

            } else {
                finish();
                Intent intent = new Intent(getApplicationContext(), PinVerifyActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                overridePendingTransition(0, 0);
                startActivity(intent);
            }

        }


    }

    private void VariableCasting() {
        btnLogin = findViewById(R.id.btn_login);
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
//        passcodeView = findViewById(R.id.passcodeView);
    }

    private void SettingUpURL() {
//      local ip
//      String IP = "192.168.1.11:8080";
        //live ip
        String IP = "192.168.1.7:8181";

        String ROOT_URL = "http://" + IP + "/Survey/";
        URL.LOGINACCOUNT = ROOT_URL + "login_searchacc.php";
        URL.GETEMPLOYEEDETAILS = ROOT_URL + "get_employee.php";
        URL.GETMAYASTATUS = ROOT_URL + "get_status.php";
        URL.GETTERMINALS = ROOT_URL + "get_terminals.php";
        URL.GETMERCHANTVISIT = ROOT_URL + "get_merchant_visit.php";
        URL.ADDDTR = ROOT_URL + "add_time_in_out.php";
        URL.ADDMERCHANT = ROOT_URL + "add_merchant.php";
        URL.GETITINERARY = ROOT_URL + "get_itinerary.php";
        URL.UPDATEASSIGNSTATUS = ROOT_URL + "update_assignment_status.php";
        URL.ADDMERCHANTSERIAL = ROOT_URL + "add_merchant_serialnumber.php";
        URL.GETSYNCEDMERCHANT = ROOT_URL + "get_synced_merchant.php";

    }

    public void LoginAccount(String username, String password) {

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Connecting to the Server.");
        progressDialog.setMessage("Preparing data.");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        progressDialog.show();

        GlobalVariables.processList = 1;

        handler = new Handler(Looper.myLooper());
        runnable = new Runnable() {
            @Override
            public void run() {

                if (!GlobalVariables.thread) {

                    // starting the loop
                    handler.postDelayed(runnable, 1000);

                    CheckSystemProcess();

                } else {

                    if (!GlobalVariables.successLogin) {
                        Toast.makeText(LoginActivity.this, "Invalid Credentials.", Toast.LENGTH_SHORT).show();
                    } else {

                        Toast.makeText(LoginActivity.this, "Welcome!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        finish();
                        startActivity(intent);

                    }

                    handler.removeCallbacks(runnable);
                    progressDialog.dismiss();
                    GlobalVariables.thread = false;
                    GlobalVariables.processList = 0;
                }

            }

            private void CheckSystemProcess() {

                if (GlobalVariables.processList == 1) {
                    onlineDB.LoginAccount(username, password);
                }

                if (GlobalVariables.processList == 2) {
                    onlineDB.GetEmployee(username);
                }

                if (GlobalVariables.processList == 3) {
                    offlineDB.DeleteStatus();
                    onlineDB.GetMayaStatus();
                }

                if (GlobalVariables.processList == 4) {
                    offlineDB.DeleteTerminals();
                    onlineDB.GetTerminals();

                }
                if (GlobalVariables.processList == 5) {
                    offlineDB.DeleteMerchantVisit();
                    onlineDB.GetMerchantVisit(username);

                }

                if (GlobalVariables.processList == 0) {
                    progressDialog.setMessage(GlobalVariables.progressDialogMessage);
                }
            }

        };
        handler.postDelayed(runnable, 1000);


    }
}