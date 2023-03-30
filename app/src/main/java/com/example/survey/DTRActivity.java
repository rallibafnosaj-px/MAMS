package com.example.survey;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static android.content.ContentValues.TAG;

public class DTRActivity extends AppCompatActivity {

    TextView tvDtrDate, tvDtrAddress;
    TextClock txtClock;
    Button btnTimeInOut, btnRefreshLoc;
    ImageView ivSelfie;
    GetLocation getLocation = new GetLocation(this);

    String imageToString = "";
    String dtrCounter = "1";

    AlertDialog.Builder builder;

    private static final int CAMERA_PIC_REQUEST = 110;

    ActivityResultLauncher<Intent> activityResultLauncher;

    SQLite.DTRActivitySQLite offDB = new SQLite.DTRActivitySQLite(this);
    SQLite.SurveyFormActivitySQLite surveyDB = new SQLite.SurveyFormActivitySQLite(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Time in");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_d_t_r);
        GetCurrentLocation();
        VariableCasting();
        isTimeSetToAutomatic();
        isLocationOn();
        getCurrentDate();
        tvDtrAddress.setText("Current Location: " + getLocation.address);
        CheckDTRCounter();
        takeDTR();


        ivSelfie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermission(Manifest.permission.CAMERA, CAMERA_PIC_REQUEST);
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                activityResultLauncher.launch(cameraIntent);
            }
        });

        btnRefreshLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetCurrentLocation();
                tvDtrAddress.setText("Current Location: " + getLocation.address);
            }
        });
        btnTimeInOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if (btnTimeInOut.getText().toString().equals("Time in")) {
                        builder.setMessage("Proceed Time In?")
                                .setCancelable(true)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Calendar c = Calendar.getInstance();

                                        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
                                        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
                                        String formattedDate = df.format(c.getTime());
                                        String formattedDate1 = df1.format(c.getTime());

                                        offDB.InsertDTRIn(offDB.getAgentID(), formattedDate1, formattedDate, getLocation.latitude, getLocation.longtitude, getLocation.address, imageToString);
                                        offDB.InsertDTRCounter(dtrCounter);
                                        Toast.makeText(DTRActivity.this, "Time in recorded", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(DTRActivity.this, ChooseTransactionActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                }).show();

                    } else {
                        builder.setMessage("Proceed Time Out?")
                                .setCancelable(true)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Calendar c = Calendar.getInstance();

                                        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
                                        String formattedDate = df.format(c.getTime());

                                        offDB.InsertDTROut(formattedDate, getLocation.latitude, getLocation.longtitude, getLocation.address, imageToString);
                                        offDB.DeleteDTRCounter();
                                        surveyDB.DeleteMerchantCounter();
                                        Toast.makeText(DTRActivity.this, "Time out recorded", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(DTRActivity.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                }).show();

//                Toast.makeText(TimeInOutActivity.this, "sa" + getLocation.address, Toast.LENGTH_SHORT).show();
                    }



            }
        });
    }

    private void VariableCasting() {
        tvDtrDate = findViewById(R.id.tv_dtrDate);
        tvDtrAddress = findViewById(R.id.tv_dtrAddress);
        txtClock = findViewById(R.id.textClockTime);
        btnTimeInOut = findViewById(R.id.btn_timeInOut);
        ivSelfie = findViewById(R.id.iv_Pic);
        builder = new AlertDialog.Builder(this);
        btnRefreshLoc = findViewById(R.id.btn_refreshLocation);
    }

    private void CheckDTRCounter() {

//        Toast.makeText(this, "" + offlineDB.CheckDTRTimeIn(),  Toast.LENGTH_SHORT).show();
        if (offDB.CheckDTRTimeIn() == true) {
            btnTimeInOut.setText("Time out");
            getSupportActionBar().setTitle("Time Out");
        }else{
            btnTimeInOut.setText("Time in");
            getSupportActionBar().setTitle("Time In");
        }

    }

    private void isTimeSetToAutomatic() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            try {
                if(Settings.Global.getInt(getContentResolver(), Settings.Global.AUTO_TIME) == 1)
                {
                    // Enabled
                    btnTimeInOut.setEnabled(true);
//                    Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    // Disabed
                    builder.setTitle("Device time/date is not set to automatic")
                            .setMessage("Please set time/date to automatically?")
                            .setCancelable(false)
                            .setPositiveButton("Change", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    startActivity(new Intent(android.provider.Settings.ACTION_DATE_SETTINGS));
                                }
                            }).show();

                    btnTimeInOut.setEnabled(false);
//                    Toast.makeText(this, "", Toast.LENGTH_SHORT).show();

                }
            } catch (Settings.SettingNotFoundException e) {
                Log.d(TAG, "isTimeAutomatic: " + e.toString());
                e.printStackTrace();
            }
        }
    }

    public void isLocationOn(){
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if( !locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ) {
            btnTimeInOut.setEnabled(false);

            builder.setTitle("Location is disabled")  // GPS not found
                    .setMessage("Please enable Location") // Want to enable?
                    .setPositiveButton("Change", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                        }
                    }).show();
        }else {
            btnTimeInOut.setEnabled(true);
        }

    }



    private void GetCurrentLocation() {

        getLocation.getCurrentLocation();

    }

    public void getCurrentDate() {
        Calendar c = Calendar.getInstance();

        SimpleDateFormat df = new SimpleDateFormat("EEEE, MMMM d, y");
        String formattedDate = df.format(c.getTime());

        tvDtrDate.setText(formattedDate);

    }

    public void checkPermission(String permission, int requestCode){
        if(ContextCompat.checkSelfPermission(DTRActivity.this, permission) == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(DTRActivity.this, new String[]{permission}, requestCode);
        } else{
//            Toast.makeText(this, "Permission already Granted", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==CAMERA_PIC_REQUEST){
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Camera permission Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Camera permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void takeDTR() {

        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                try {

                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {

                        Bitmap image = (Bitmap) result.getData().getExtras().get("data");
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        image.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                        byte[] imgBytes = byteArrayOutputStream.toByteArray();
                        imageToString = Base64.encodeToString(imgBytes, Base64.DEFAULT);
                        ivSelfie.setImageBitmap(image);
//                dialogDTR(image);
                        Log.d(TAG, "onActivityResult: " + imageToString);

                        tvDtrAddress.setText("Current Location: " + getLocation.address);
                    }

                } catch (NullPointerException nullExcep) {


                }
            }
        });


    }
}