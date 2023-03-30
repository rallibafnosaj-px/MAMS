package com.example.survey;

import static android.content.ContentValues.TAG;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import Model.MerchantModel;

public class SurveyFormActivity extends AppCompatActivity {

    Spinner spMayaStatus, spSN1, spSN2, spSN3, spSN4, spSN5, spSN6, spSN7, spSN8, spSN9, spSN10;
    View line1, line2, line3, line4, line5, line6, line7;
    RelativeLayout RL1, RL2, RL3, RL4, RL5, RL6, RL7, RL8, RL9, RL10, RL11, RL12, RL13,
            RLSerial1, RLSerial2, RLSerial3, RLSerial4, RLSerial5, RLSerial6, RLSerial7, RLSerial8, RLSerial9, RLSerial10, RLSerial00;
    LinearLayout LL1;
    Button btnUploadCounterPic, btnSQRPicture, btnSubmitForm, btnPisoTest, btnSerial, btnSQRCount, btnEXTRACount,
            btnSerial00, btnSerial2, btnSerial3, btnSerial4, btnSerial5, btnSerial6, btnSerial7, btnSerial8, btnSerial9, btnSerial10;


    //    TextView tvTerminalAvailable;
    TextView tvTerminalAvailable, tvOtherMercVisible, tvAvailSQR, tvMercRestrict, tvDateOfVisit,
            tvAgentName, tvGapa, tvMaya, text1, text2, text3, text4, text5, text6, tvNonMaya, tvQRVersion;
    EditText etNameOfMall, etDBAName, etRegBizName, etSubArea, etMercSPOCName, etMercSPOCDesig,
            etMercSPOCEmail, etMercSPOCContact, etQ2, etQ3, etQ4, etQ5, etCompDelAdd, etRemarks,
            etSerial00, etSerial2, etSerial3, etSerial4, etSerial5, etSerial6, etSerial7, etSerial8, etSerial9, etSerial10;
    ImageView ivUploadCounterPrice, ivSQRPicture, ivStoreScan;
    SQLite.SurveyFormActivitySQLite offDB = new SQLite.SurveyFormActivitySQLite(this);
    GlobalFunction globalFunction = new GlobalFunction(this);
    boolean[] selectedTerminals, selectedOtherMerc, selectedAvailSQR, selectedMercRes;
    ArrayList<Integer> terminalList = new ArrayList<>(), otherMercList = new ArrayList<>(), sqrList = new ArrayList<>(), MercResList = new ArrayList<>();

    String[] terminalArray = {"Maya", "BDO", "BPI", "Citibank", "Metrobank", "Global Payments", "Stripe Payment", "HSBC"
            , "RCBC", "GHL", "G-Xchange", "Rbank", "UnionBank", "Eastwest", "Gcash", "Security Bank", "PNB"
            , "Maybank", "Food Panda", "GrabFood Terminal", "Maya Terminal", "Robinsons Bank", "STARPAY"
            , "JCB", "Allied Bank", "Alipay", "China Bank", "Development Bank of the Philippines", "Others"};
    String[] otherMercArray = {"Maya", "BDO", "BPI", "Citibank", "Metrobank", "Global Payments", "Stripe Payment", "HSBC"
            , "RCBC", "GHL", "G-Xchange", "Rbank", "UnionBank", "Eastwest", "Gcash", "Security Bank", "PNB"
            , "Maybank", "Food Panda", "GrabFood Terminal", "Maya Terminal", "Robinsons Bank", "STARPAY"
            , "JCB", "Allied Bank", "Alipay", "China Bank", "Development Bank of the Philippines", "Others"};
    String[] availSQRArray = {"Maya SQR", "GCash SQR", "BDO Pay", "Shopee Pay", "Alipay", "Union Pay", "Atome"
            , "AUB", "Watsons", "BPI Pay", "QRPH", "Grab Pay", "Others"};
    String[] mercResArray = {"Clean door policy", "Clear counter policy", "No Restriction"
            , "No area to display", "Merch Restriction (Clean Door and counter Policy)"};
    String image, image2, image3;
    int gCashStaticQR, gCashQRInsidePOS, mayaHidden, mayaStandee, mayaDoorHanger, mayaNone, nonMayaStandee,
            nonMayaDoorHanger, nonMayaNone, qrGreenBird, qrMaya2, otherPaymentEwallet, gCashBoth;
    String empName = "", mayaStatus, imageString1 = "", imageString2 = "", imageString3 = "", yearMonthDay, sourceTransID, transactionID,
            imageStringSN1 = "", imageStringSN2 = "", imageStringSN3 = "", imageStringSN4 = "", imageStringSN5 = "",
            imageStringSN6 = "", imageStringSN7 = "", imageStringSN8 = "", imageStringSN9 = "", imageStringSN10 = "",
            textSN1 = "", textSN2 = "", textSN3 = "", textSN4 = "", textSN5 = "", textSN6 = "", textSN7 = "", textSN8 = "", textSN9 = "", textSN10 = "";

    String id = "";

    RadioButton rbStaticQRYes;
    RadioButton rbStaticQRNo;
    RadioButton rbQRPOSYes;
    RadioButton rbQRPOSNo;
    RadioButton rbMayaVisibilityHiddenYes;
    RadioButton rbMayaVisibilityHiddenNo;
    RadioButton rbMayaVisiblityStandeeYes;
    RadioButton rbMayaVisiblityStandeeNo;
    RadioButton rbMayaVisibilityDoorHangerYes;
    RadioButton rbMayaVisibilityDoorHangerNo;
    RadioButton rbMayaVisibilityNoneYes;
    RadioButton rbMayaVisibilityNoneNo;

    RadioButton rbNonMayaVisibilityStandeeYes;
    RadioButton rbNonMayaVisibilityStandeeNo;
    RadioButton rbNonMayaVisibilityNoneYes;
    RadioButton rbNonMayaVisibilityNoneNo;
    RadioButton rbNonMayaVisibilityDoorHangerYes;
    RadioButton rbNonMayaVisibilityDoorHangerNo;

    RadioButton rbNonMayaVisibilityQRGreenBirdYes;
    RadioButton rbNonMayaVisibilityQRGreenBirdNo;

    RadioButton rbNonVisibilityMayaMayaYes;
    RadioButton rbNonVisibilityMayaMayaNo;

    RadioButton rbQ1Yes;
    RadioButton rbQ1No;

    RadioButton rbGcashBothYes;
    RadioButton rbGcashBothNo;


    List<MerchantModel> merchantModels;




    AlertDialog.Builder builder;
    private static final int CAMERA_PIC_REQUEST = 110;
    private static final int CAMERA_PIC_REQUEST1 = 120;
    ActivityResultLauncher<Intent> activityResultLauncher;
    ActivityResultLauncher<Intent> activityResultLauncher2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_form);
        getSupportActionBar().setTitle(GlobalVariables.forMayaSetup);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        VariableCasting();
        AddingSpinners();
        ResetPhotos();

        Toast.makeText(this, "" + GlobalVariables.fromDashboard, Toast.LENGTH_SHORT).show();


        if (getSupportActionBar().getTitle().equals("Maya")) {
            spMayaStatus.setSelection(1);
            spMayaStatus.setEnabled(false);
            MercShow();

        } else if (getSupportActionBar().getTitle().equals("Non-Maya")) {
//            spMayaStatus.setEnabled(false);

            MerchantNonMayaHide();
        }
        getCurrentDate();
        GetEmployee();
        setDetails();
        terminalCheckBox();
        otherMercVisibleCheckBox();
        availSQRCheckBox();
        mercRestrictionCheckBox();
        fillUpTheFields();

        transactionID = offDB.RetrieveAgentID() + "-" + sourceTransID;

        ivStoreScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(SurveyFormActivity.this);

                intentIntegrator.setPrompt("For flash use volume up key");

                intentIntegrator.setBeepEnabled(true);
                intentIntegrator.setOrientationLocked(true);
                intentIntegrator.setCaptureActivity(Capture.class);
                intentIntegrator.initiateScan();
            }
        });
        btnUploadCounterPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                checkPermission(Manifest.permission.CAMERA, CAMERA_PIC_REQUEST);
//                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//                activityResultLauncher.launch(cameraIntent);

                SelectPhoto();
            }
        });

        btnSQRPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                checkPermission(Manifest.permission.CAMERA, CAMERA_PIC_REQUEST1);
//                Intent cameraIntent2 = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//                activityResultLauncher2.launch(cameraIntent2);

                SelectPhoto2();
            }
        });

        btnSerial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value = Integer.parseInt(etQ2.getText().toString());
                if (value > 10) {
                    Toast.makeText(SurveyFormActivity.this, "Exceeded the number of device.", Toast.LENGTH_SHORT).show();

                } else {
                    if (value == 1) {
                        RLSerial00.setVisibility(View.VISIBLE);
//                        RLSerial1.setVisibility(View.VISIBLE);
                        RLSerial2.setVisibility(View.GONE);
                        RLSerial3.setVisibility(View.GONE);
                        RLSerial4.setVisibility(View.GONE);
                        RLSerial5.setVisibility(View.GONE);
                        RLSerial6.setVisibility(View.GONE);
                        RLSerial7.setVisibility(View.GONE);
                        RLSerial8.setVisibility(View.GONE);
                        RLSerial9.setVisibility(View.GONE);
                        RLSerial10.setVisibility(View.GONE);
                    } else if (value == 2) {
                        RLSerial00.setVisibility(View.VISIBLE);
//                        RLSerial1.setVisibility(View.VISIBLE);
                        RLSerial2.setVisibility(View.VISIBLE);
                        RLSerial3.setVisibility(View.GONE);
                        RLSerial4.setVisibility(View.GONE);
                        RLSerial5.setVisibility(View.GONE);
                        RLSerial6.setVisibility(View.GONE);
                        RLSerial7.setVisibility(View.GONE);
                        RLSerial8.setVisibility(View.GONE);
                        RLSerial9.setVisibility(View.GONE);
                        RLSerial10.setVisibility(View.GONE);
                    } else if (value == 3) {
                        RLSerial00.setVisibility(View.VISIBLE);
//                        RLSerial1.setVisibility(View.VISIBLE);
                        RLSerial2.setVisibility(View.VISIBLE);
                        RLSerial3.setVisibility(View.VISIBLE);
                        RLSerial4.setVisibility(View.GONE);
                        RLSerial5.setVisibility(View.GONE);
                        RLSerial6.setVisibility(View.GONE);
                        RLSerial7.setVisibility(View.GONE);
                        RLSerial8.setVisibility(View.GONE);
                        RLSerial9.setVisibility(View.GONE);
                        RLSerial10.setVisibility(View.GONE);
                    } else if (value == 4) {
                        RLSerial00.setVisibility(View.VISIBLE);
//                        RLSerial1.setVisibility(View.VISIBLE);
                        RLSerial2.setVisibility(View.VISIBLE);
                        RLSerial3.setVisibility(View.VISIBLE);
                        RLSerial4.setVisibility(View.VISIBLE);
                        RLSerial5.setVisibility(View.GONE);
                        RLSerial6.setVisibility(View.GONE);
                        RLSerial7.setVisibility(View.GONE);
                        RLSerial8.setVisibility(View.GONE);
                        RLSerial9.setVisibility(View.GONE);
                        RLSerial10.setVisibility(View.GONE);
                    } else if (value == 5) {
                        RLSerial00.setVisibility(View.VISIBLE);
//                        RLSerial1.setVisibility(View.VISIBLE);
                        RLSerial2.setVisibility(View.VISIBLE);
                        RLSerial3.setVisibility(View.VISIBLE);
                        RLSerial4.setVisibility(View.VISIBLE);
                        RLSerial5.setVisibility(View.VISIBLE);
                        RLSerial6.setVisibility(View.GONE);
                        RLSerial7.setVisibility(View.GONE);
                        RLSerial8.setVisibility(View.GONE);
                        RLSerial9.setVisibility(View.GONE);
                        RLSerial10.setVisibility(View.GONE);
                    } else if (value == 6) {
                        RLSerial00.setVisibility(View.VISIBLE);
//                        RLSerial1.setVisibility(View.VISIBLE);
                        RLSerial2.setVisibility(View.VISIBLE);
                        RLSerial3.setVisibility(View.VISIBLE);
                        RLSerial4.setVisibility(View.VISIBLE);
                        RLSerial5.setVisibility(View.VISIBLE);
                        RLSerial6.setVisibility(View.VISIBLE);
                        RLSerial7.setVisibility(View.GONE);
                        RLSerial8.setVisibility(View.GONE);
                        RLSerial9.setVisibility(View.GONE);
                        RLSerial10.setVisibility(View.GONE);
                    } else if (value == 7) {
                        RLSerial00.setVisibility(View.VISIBLE);
//                        RLSerial1.setVisibility(View.VISIBLE);
                        RLSerial2.setVisibility(View.VISIBLE);
                        RLSerial3.setVisibility(View.VISIBLE);
                        RLSerial4.setVisibility(View.VISIBLE);
                        RLSerial5.setVisibility(View.VISIBLE);
                        RLSerial6.setVisibility(View.VISIBLE);
                        RLSerial7.setVisibility(View.VISIBLE);
                        RLSerial8.setVisibility(View.GONE);
                        RLSerial9.setVisibility(View.GONE);
                        RLSerial10.setVisibility(View.GONE);
                    } else if (value == 8) {
                        RLSerial00.setVisibility(View.VISIBLE);
//                        RLSerial1.setVisibility(View.VISIBLE);
                        RLSerial2.setVisibility(View.VISIBLE);
                        RLSerial3.setVisibility(View.VISIBLE);
                        RLSerial4.setVisibility(View.VISIBLE);
                        RLSerial5.setVisibility(View.VISIBLE);
                        RLSerial6.setVisibility(View.VISIBLE);
                        RLSerial7.setVisibility(View.VISIBLE);
                        RLSerial8.setVisibility(View.VISIBLE);
                        RLSerial9.setVisibility(View.GONE);
                        RLSerial10.setVisibility(View.GONE);
                    } else if (value == 9) {
                        RLSerial00.setVisibility(View.VISIBLE);
//                        RLSerial1.setVisibility(View.VISIBLE);
                        RLSerial2.setVisibility(View.VISIBLE);
                        RLSerial3.setVisibility(View.VISIBLE);
                        RLSerial4.setVisibility(View.VISIBLE);
                        RLSerial5.setVisibility(View.VISIBLE);
                        RLSerial6.setVisibility(View.VISIBLE);
                        RLSerial7.setVisibility(View.VISIBLE);
                        RLSerial8.setVisibility(View.VISIBLE);
                        RLSerial9.setVisibility(View.VISIBLE);
                        RLSerial10.setVisibility(View.GONE);
                    } else if (value == 10) {
                        RLSerial00.setVisibility(View.VISIBLE);
//                        RLSerial1.setVisibility(View.VISIBLE);
                        RLSerial2.setVisibility(View.VISIBLE);
                        RLSerial3.setVisibility(View.VISIBLE);
                        RLSerial4.setVisibility(View.VISIBLE);
                        RLSerial5.setVisibility(View.VISIBLE);
                        RLSerial6.setVisibility(View.VISIBLE);
                        RLSerial7.setVisibility(View.VISIBLE);
                        RLSerial8.setVisibility(View.VISIBLE);
                        RLSerial9.setVisibility(View.VISIBLE);
                        RLSerial10.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        btnSubmitForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(GlobalVariables.fromDashboard)
                {
                    builder.setMessage("Proceed?")
                            .setCancelable(true)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    offDB.UpdateSurveyFromActivity(id, yearMonthDay, empName, etNameOfMall.getText().toString(),
                                            imageString1, mayaStatus, etDBAName.getText().toString(), etRegBizName.getText().toString(),
                                            etSubArea.getText().toString(),
                                            etMercSPOCName.getText().toString(),
                                            etMercSPOCDesig.getText().toString(),
                                            etMercSPOCEmail.getText().toString(),
                                            etMercSPOCContact.getText().toString(),
                                            gCashStaticQR,
                                            gCashQRInsidePOS,
                                            gCashBoth,
                                            tvTerminalAvailable.getText().toString(),
                                            tvOtherMercVisible.getText().toString(),
                                            mayaHidden,
                                            mayaStandee,
                                            mayaDoorHanger,
                                            mayaNone,
                                            nonMayaStandee,
                                            nonMayaDoorHanger,
                                            nonMayaNone,
                                            qrGreenBird,
                                            qrMaya2,
                                            imageString2,
                                            tvAvailSQR.getText().toString(),
                                            tvMercRestrict.getText().toString(),
                                            otherPaymentEwallet,
                                            etQ2.getText().toString(),
                                            etQ3.getText().toString(),
                                            etQ4.getText().toString(),
                                            etQ5.getText().toString(),
                                            transactionID,
                                            etCompDelAdd.getText().toString(),
                                            etRemarks.getText().toString());
                                }
                            });
                }
                else
                {
                    Toast.makeText(SurveyFormActivity.this, "2", Toast.LENGTH_SHORT).show();
                    if (spMayaStatus.getSelectedItem().toString().equals("Maya Status")) {
                        Toast.makeText(SurveyFormActivity.this, "Please select status", Toast.LENGTH_SHORT).show();
                    } else {
                        if (etNameOfMall.getText().toString().isEmpty() || etDBAName.getText().toString().isEmpty() || etMercSPOCName.getText().toString().isEmpty()) {
                            Toast.makeText(SurveyFormActivity.this, "Please fill up Business Area, DBA name or Merchant SPOC Name", Toast.LENGTH_SHORT).show();
                        } else {

                            builder.setMessage("Proceed?")
                                    .setCancelable(true)
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {


                                            offDB.InsertSurveyFromActivity(yearMonthDay, empName, etNameOfMall.getText().toString(),
                                                    imageString1, mayaStatus, etDBAName.getText().toString(), etRegBizName.getText().toString(),
                                                    etSubArea.getText().toString(),
                                                    etMercSPOCName.getText().toString(),
                                                    etMercSPOCDesig.getText().toString(),
                                                    etMercSPOCEmail.getText().toString(),
                                                    etMercSPOCContact.getText().toString(),
                                                    gCashStaticQR,
                                                    gCashQRInsidePOS,
                                                    gCashBoth,
                                                    tvTerminalAvailable.getText().toString(),
                                                    tvOtherMercVisible.getText().toString(),
                                                    mayaHidden,
                                                    mayaStandee,
                                                    mayaDoorHanger,
                                                    mayaNone,
                                                    nonMayaStandee,
                                                    nonMayaDoorHanger,
                                                    nonMayaNone,
                                                    qrGreenBird,
                                                    qrMaya2,
                                                    imageString2,
                                                    tvAvailSQR.getText().toString(),
                                                    tvMercRestrict.getText().toString(),
                                                    otherPaymentEwallet,
                                                    etQ2.getText().toString(),
                                                    etQ3.getText().toString(),
                                                    etQ4.getText().toString(),
                                                    etQ5.getText().toString(),
                                                    transactionID,
                                                    etCompDelAdd.getText().toString(),
                                                    etRemarks.getText().toString());

                                            offDB.InsertSerialNumberAndImage(transactionID,
                                                    etSerial00.getText().toString(),
                                                    imageStringSN1,
                                                    etSerial2.getText().toString(),
                                                    imageStringSN2,
                                                    etSerial3.getText().toString(),
                                                    imageStringSN3,
                                                    etSerial4.getText().toString(),
                                                    imageStringSN4,
                                                    etSerial5.getText().toString(),
                                                    imageStringSN5,
                                                    etSerial6.getText().toString(),
                                                    imageStringSN6,
                                                    etSerial7.getText().toString(),
                                                    imageStringSN7,
                                                    etSerial8.getText().toString(),
                                                    imageStringSN8,
                                                    etSerial9.getText().toString(),
                                                    imageStringSN9,
                                                    etSerial10.getText().toString(),
                                                    imageStringSN10,
                                                    textSN1,
                                                    textSN2,
                                                    textSN3,
                                                    textSN4,
                                                    textSN5,
                                                    textSN6,
                                                    textSN7,
                                                    textSN8,
                                                    textSN9,
                                                    textSN10);

                                            offDB.InsertMerchantCounter("1");
                                            Toast.makeText(SurveyFormActivity.this, "Transaction Complete", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(SurveyFormActivity.this, DTRActivity.class));


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
                }


            }
        });

        btnSerial00.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectPhotoSN1();
            }
        });
        btnSerial2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectPhotoSN2();
            }
        });
        btnSerial3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectPhotoSN3();
            }
        });
        btnSerial4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectPhotoSN4();
            }
        });
        btnSerial5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectPhotoSN5();
            }
        });
        btnSerial6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectPhotoSN6();
            }
        });
        btnSerial7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectPhotoSN7();
            }
        });
        btnSerial8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectPhotoSN8();
            }
        });
        btnSerial9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectPhotoSN9();
            }
        });
        btnSerial10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectPhotoSN10();
            }
        });
    }



    public void fillUpTheFields() {

        if (GlobalVariables.fromSQLite)
        {

            Intent intent = getIntent();
            String ID = intent.getExtras().getString("ID");


            merchantModels = offDB.RetrieveDataMerchant(ID);

            String staticQR = merchantModels.get(0).getGcash_accept_statc().toLowerCase();
            String mayaStatus = merchantModels.get(0).getMaya_status().toLowerCase();
            String qrPOS =  merchantModels.get(0).getGcash_accept_qrinsidepos().toLowerCase();
            String mayaHidden =  merchantModels.get(0).getMaya_visibility_hidden().toLowerCase();
            String mayaStandee =  merchantModels.get(0).getMaya_visibility_standee().toLowerCase();
            String mayaDH =  merchantModels.get(0).getMaya_visibility_doorhanger().toLowerCase();
            String mayaNone =  merchantModels.get(0).getMaya_visiblity_none().toLowerCase();
            String nonMayaStandee =  merchantModels.get(0).getNonmaya_visiblity_standee().toLowerCase();
            String nonMayaDoorHanger =  merchantModels.get(0).getNonmaya_visiblity_doorhanger().toLowerCase();
            String nonMayaNone =  merchantModels.get(0).getNonmaya_visibility_none().toLowerCase();
            String nonMayaGreenBird =  merchantModels.get(0).getQr_green_bird().toLowerCase();
            String nonMayaMaya =  merchantModels.get(0).getQr_mayatwo().toLowerCase();
            String countOfDevices = merchantModels.get(0).getMaya_device_count().toLowerCase();
            String countOfSQRs = merchantModels.get(0).getMaya_sqr_count().toLowerCase();
            String storeCode = merchantModels.get(0).getStore_code().toLowerCase();
            String completeDeliveryAddress = merchantModels.get(0).getComplete_delivery_add().toLowerCase();
            String remarks = merchantModels.get(0).getRemarks().toLowerCase();
            String gcashBoth = merchantModels.get(0).getGcash_accept_both().toLowerCase();
            String nameOfMall = merchantModels.get(0).getName_of_mall();


            etRegBizName.setText(merchantModels.get(0).getReg_business_name());
            etDBAName.setText(merchantModels.get(0).getDba_name());
            etSubArea.setText(merchantModels.get(0).getSub_area());
            etMercSPOCName.setText(merchantModels.get(0).getMerc_spoc_name());
            etMercSPOCDesig.setText(merchantModels.get(0).getMerc_spoc_designation());
            etMercSPOCEmail.setText(merchantModels.get(0).getMerc_spoc_email());
            etMercSPOCContact.setText(merchantModels.get(0).getMerc_spoc_contact());
            etNameOfMall.setText(nameOfMall);
            spMayaStatus.setSelection(Integer.parseInt(mayaStatus));


            etCompDelAdd.setText(completeDeliveryAddress);
            etRemarks.setText(remarks);
            etQ2.setText(countOfDevices);
            etQ4.setText(countOfSQRs);
            etQ5.setText(storeCode);


            if(staticQR.equals("y"))
            {
                rbStaticQRYes.setChecked(true);
            }
            else
            {
                rbStaticQRNo.setChecked(true);
            }


            if(qrPOS.equals("y"))
            {
                rbQRPOSYes.setChecked(true);
            }
            else
            {
                rbQRPOSNo.setChecked(true);
            }


            if(gcashBoth.equals("y"))
            {
                rbGcashBothYes.setChecked(true);
            }
            else
            {
                rbGcashBothNo.setChecked(true);
            }




            if(mayaDH.equals("y"))
            {
                rbMayaVisibilityDoorHangerYes.setChecked(true);
            }
            else
            {
                rbMayaVisibilityDoorHangerNo.setChecked(true);
            }




            if(mayaHidden.equals("y"))
            {

                rbMayaVisibilityHiddenYes.setChecked(true);
            }
            else
            {
                rbMayaVisibilityHiddenNo.setChecked(true);
            }


            if(mayaDH.equals("y"))
            {
                rbMayaVisibilityDoorHangerYes.setChecked(true);
            }
            else
            {
                rbMayaVisibilityDoorHangerNo.setChecked(true);
            }



            if(mayaStandee.equals("y"))
            {
                rbMayaVisiblityStandeeYes.setChecked(true);
            }
            else
            {
                rbMayaVisiblityStandeeNo.setChecked(true);
            }

            if(mayaNone.equals("y"))
            {
                rbMayaVisibilityNoneYes.setChecked(true);
            }
            else
            {
                rbMayaVisibilityNoneNo.setChecked(true);
            }

            if(nonMayaDoorHanger.equals("y"))
            {
                rbNonMayaVisibilityDoorHangerYes.setChecked(true);
            }
            else
            {
                rbNonMayaVisibilityDoorHangerNo.setChecked(true);
            }

            if(nonMayaStandee.equals("y"))
            {
                rbNonMayaVisibilityStandeeYes.setChecked(true);
            }
            else
            {
                rbNonMayaVisibilityStandeeNo.setChecked(true);
            }

            if(nonMayaDoorHanger.equals("y"))
            {
                rbNonMayaVisibilityDoorHangerYes.setChecked(true);
            }
            else
            {
                rbNonMayaVisibilityDoorHangerNo.setChecked(true);
            }


            if(nonMayaNone.equals("y"))
            {
                rbNonMayaVisibilityNoneYes.setChecked(true);
            }
            else
            {
                rbNonMayaVisibilityNoneNo.setChecked(true);
            }



            if(nonMayaMaya.equals("y"))
            {
                rbNonVisibilityMayaMayaYes.setChecked(true);
            }
            else
            {
                rbNonVisibilityMayaMayaNo.setChecked(true);
            }


            if(nonMayaGreenBird.equals("y"))
            {
                rbNonMayaVisibilityQRGreenBirdYes.setChecked(true);
            }
            else
            {
                rbNonMayaVisibilityQRGreenBirdNo.setChecked(true);
            }







            GlobalVariables.fromSQLite = false;
        }


    }

    public void AddingSpinners() {
        AddingItemToSpinnerStatus();
        AddingItemToSN1();
        AddingItemToSN2();
        AddingItemToSN3();
        AddingItemToSN4();
        AddingItemToSN5();
        AddingItemToSN6();
        AddingItemToSN7();
        AddingItemToSN8();
        AddingItemToSN9();
        AddingItemToSN10();
    }

    public void ResetPhotos(){
        GlobalVariables.pisoTestPhotos.clear();
        GlobalVariables.deliveryPhotos.clear();
        GlobalVariables.extraPhotos.clear();
    }

    public void SelectPhotoSQR(View view) {

        globalFunction.SelectPhotoSQR1();

    }

    public void SelectPhotoEXTRA(View view) {

        globalFunction.SelectPhotoEXTRA();

    }

    public void SelectPhotoPISOTEST(View view) {

        globalFunction.SelectPhotoPisoTest();

    }


    public void SelectPhoto() {

        globalFunction.SelectPhoto();
    }

    public void SelectPhoto2() {

        globalFunction.SelectPhoto2();
    }

    public void SelectPhoto3() {

        globalFunction.SelectPhoto3();
    }

    public void SelectPhotoSN1() {

        globalFunction.SelectPhotoSN1();
    }

    public void SelectPhotoSN2() {

        globalFunction.SelectPhotoSN2();
    }

    public void SelectPhotoSN3() {

        globalFunction.SelectPhotoSN3();
    }

    public void SelectPhotoSN4() {

        globalFunction.SelectPhotoSN4();
    }

    public void SelectPhotoSN5() {

        globalFunction.SelectPhotoSN5();
    }

    public void SelectPhotoSN6() {

        globalFunction.SelectPhotoSN6();
    }

    public void SelectPhotoSN7() {

        globalFunction.SelectPhotoSN7();
    }

    public void SelectPhotoSN8() {

        globalFunction.SelectPhotoSN8();
    }

    public void SelectPhotoSN9() {

        globalFunction.SelectPhotoSN9();
    }

    public void SelectPhotoSN10() {

        globalFunction.SelectPhotoSN10();
    }

    private void VariableCasting() {

        spMayaStatus = findViewById(R.id.sp_MayaStatus);
        tvTerminalAvailable = findViewById(R.id.tv_sTerminalAvailable);
        tvOtherMercVisible = findViewById(R.id.tv_sOtherMercVisible);
        tvAvailSQR = findViewById(R.id.tv_sAvailSQR);
        tvMercRestrict = findViewById(R.id.tv_sMercRestrict);
        tvDateOfVisit = findViewById(R.id.tv_sDateOfVisit);
        tvAgentName = findViewById(R.id.tv_sAgentName);
        tvGapa = findViewById(R.id.tv_gapa);
        tvMaya = findViewById(R.id.tv_maya);
        tvNonMaya = findViewById(R.id.tv_nonMaya);
        tvQRVersion = findViewById(R.id.tv_sQRVersion);
        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);
        text3 = findViewById(R.id.text3);
        text4 = findViewById(R.id.text4);
        text5 = findViewById(R.id.text5);
        text6 = findViewById(R.id.text6);
        RL1 = findViewById(R.id.RL1);
        RL2 = findViewById(R.id.RL2);
        RL3 = findViewById(R.id.RL3);
        RL4 = findViewById(R.id.RL4);
        RL5 = findViewById(R.id.RL5);
        RL6 = findViewById(R.id.RL6);
        RL7 = findViewById(R.id.RL7);
        RL8 = findViewById(R.id.RL8);
        RL9 = findViewById(R.id.RL9);
        RL10 = findViewById(R.id.RL10);
        RL11 = findViewById(R.id.RL11);
        RL12 = findViewById(R.id.RL12);
        RL13 = findViewById(R.id.RL13);
//        RLSerial1 = findViewById(R.id.RLSerial1);
        RLSerial2 = findViewById(R.id.RLSerial2);
        RLSerial3 = findViewById(R.id.RLSerial3);
        RLSerial4 = findViewById(R.id.RLSerial4);
        RLSerial5 = findViewById(R.id.RLSerial5);
        RLSerial6 = findViewById(R.id.RLSerial6);
        RLSerial7 = findViewById(R.id.RLSerial7);
        RLSerial8 = findViewById(R.id.RLSerial8);
        RLSerial9 = findViewById(R.id.RLSerial9);
        RLSerial10 = findViewById(R.id.RLSerial10);
        RLSerial00 = findViewById(R.id.RLSerial00);
        LL1 = findViewById(R.id.LL1);
        line1 = findViewById(R.id.line1);
        line2 = findViewById(R.id.line2);
        line3 = findViewById(R.id.line3);
        line4 = findViewById(R.id.line4);
        line5 = findViewById(R.id.line5);
        line6 = findViewById(R.id.line6);
        line7 = findViewById(R.id.line7);
        etNameOfMall = findViewById(R.id.et_sNameOfMall);
        etDBAName = findViewById(R.id.et_sDBAName);
        etRegBizName = findViewById(R.id.et_sRegBizName);
        etSubArea = findViewById(R.id.et_sSubArea);
        etMercSPOCName = findViewById(R.id.et_sMercSPOCName);
        etMercSPOCDesig = findViewById(R.id.et_sMercSPOCDesig);
        etMercSPOCEmail = findViewById(R.id.et_sMercSPOCEmail);
        etMercSPOCContact = findViewById(R.id.et_sMercSPOCContact);
        etQ2 = findViewById(R.id.et_sQ2);
        etQ3 = findViewById(R.id.et_sQ3);
        etQ4 = findViewById(R.id.et_sQ4);
        etQ5 = findViewById(R.id.et_sQ5);
        etCompDelAdd = findViewById(R.id.et_sCompDelAddress);
        etRemarks = findViewById(R.id.et_sRemarks);
        ivUploadCounterPrice = findViewById(R.id.iv_sUploadCounterPrice);
        ivSQRPicture = findViewById(R.id.iv_sSQRPicture);
        ivStoreScan = findViewById(R.id.iv_storeScan);
        btnUploadCounterPic = findViewById(R.id.btn_sUploadCounterPic);
        btnSQRPicture = findViewById(R.id.btn_sSQRPicture);
        btnSubmitForm = findViewById(R.id.btn_sSubmitForm);
        btnPisoTest = findViewById(R.id.btn_sPisoTest);
        btnSerial = findViewById(R.id.btn_sSerial);
        builder = new AlertDialog.Builder(this);

        etSerial00 = findViewById(R.id.et_sSerial00);
        etSerial2 = findViewById(R.id.et_sSerial2);
        etSerial3 = findViewById(R.id.et_sSerial3);
        etSerial4 = findViewById(R.id.et_sSerial4);
        etSerial5 = findViewById(R.id.et_sSerial5);
        etSerial6 = findViewById(R.id.et_sSerial6);
        etSerial7 = findViewById(R.id.et_sSerial7);
        etSerial8 = findViewById(R.id.et_sSerial8);
        etSerial9 = findViewById(R.id.et_sSerial9);
        etSerial10 = findViewById(R.id.et_sSerial10);
        btnSerial00 = findViewById(R.id.btn_sSerial00);
        btnSerial2 = findViewById(R.id.btn_sSerial2);
        btnSerial3 = findViewById(R.id.btn_sSerial3);
        btnSerial4 = findViewById(R.id.btn_sSerial4);
        btnSerial5 = findViewById(R.id.btn_sSerial5);
        btnSerial6 = findViewById(R.id.btn_sSerial6);
        btnSerial7 = findViewById(R.id.btn_sSerial7);
        btnSerial8 = findViewById(R.id.btn_sSerial8);
        btnSerial9 = findViewById(R.id.btn_sSerial9);
        btnSerial10 = findViewById(R.id.btn_sSerial10);
        spSN1 = findViewById(R.id.sp_SN1);
        spSN2 = findViewById(R.id.sp_SN2);
        spSN3 = findViewById(R.id.sp_SN3);
        spSN4 = findViewById(R.id.sp_SN4);
        spSN5 = findViewById(R.id.sp_SN5);
        spSN6 = findViewById(R.id.sp_SN6);
        spSN7 = findViewById(R.id.sp_SN7);
        spSN8 = findViewById(R.id.sp_SN8);
        spSN9 = findViewById(R.id.sp_SN9);
        spSN10 = findViewById(R.id.sp_SN10);

        rbStaticQRYes = findViewById(R.id.rb_sGcashStaticQrYes);
        rbStaticQRNo = findViewById(R.id.rb_sGcashStaticQrNo);
        rbQRPOSYes = findViewById(R.id.rb_sGcashQRIPOSYes);
        rbQRPOSNo = findViewById(R.id.rb_sGcashQRIPOSNo);
        rbMayaVisibilityDoorHangerYes = findViewById(R.id.rb_sMayaDHYes);
        rbMayaVisibilityDoorHangerNo = findViewById(R.id.rb_sMayaDHNo);
        rbMayaVisibilityHiddenYes = findViewById(R.id.rb_sMayaHiddenYes);
        rbMayaVisibilityHiddenNo = findViewById(R.id.rb_sMayaHiddenNo);
        rbMayaVisiblityStandeeNo = findViewById(R.id.rb_sMayaStandeeNo);
        rbMayaVisiblityStandeeYes = findViewById(R.id.rb_sMayaStandeeYes);
        rbMayaVisibilityNoneYes = findViewById(R.id.rb_sMayaNoneYes);
        rbMayaVisibilityNoneNo = findViewById(R.id.rb_sMayaNoneNo);

        rbNonMayaVisibilityQRGreenBirdNo = findViewById(R.id.rb_sQrGBNo);
        rbNonMayaVisibilityQRGreenBirdYes = findViewById(R.id.rb_sQrGBYes);
        rbNonMayaVisibilityStandeeYes = findViewById(R.id.rb_sNonMayaStandeeYes);
        rbNonMayaVisibilityStandeeNo = findViewById(R.id.rb_sNonMayaStandeeNo);
        rbNonMayaVisibilityDoorHangerNo = findViewById(R.id.rb_sNonMayaDHNo);
        rbNonMayaVisibilityDoorHangerYes = findViewById(R.id.rb_sNonMayaDHYes);
        rbNonMayaVisibilityNoneYes = findViewById(R.id.rb_sNonMayaNoneYes);
        rbNonMayaVisibilityNoneNo = findViewById(R.id.rb_sNonMayaNoneNo);
        rbNonVisibilityMayaMayaYes = findViewById(R.id.rb_sMaya2zeroYes);
        rbNonVisibilityMayaMayaNo = findViewById(R.id.rb_sMaya2zeroNo);

        rbQ1Yes = findViewById(R.id.rb_sQ1Yes);
        rbQ1No = findViewById(R.id.rb_sQ1No);

        rbGcashBothYes = findViewById(R.id.rb_sGcashBothYes);
        rbGcashBothNo = findViewById(R.id.rb_sGcashBothNo);








        btnSQRCount = findViewById(R.id.btn_sSQRCount);
        btnEXTRACount = findViewById(R.id.btn_sExtraCount);

    }

    public void GetEmployee() {
        SQLite.ProfileActivitySQLite offdb = new SQLite.ProfileActivitySQLite(this);

        String details = offdb.RetrieveEmp();
        List<String> detailsList = Arrays.asList(details.split("="));

        empName = detailsList.get(0);

    }

    private void setDetails() {
        tvAgentName.setText(empName);
    }

    public void getCurrentDate() {
        Calendar c = Calendar.getInstance();

        SimpleDateFormat df = new SimpleDateFormat("EEEE, MMMM d, y");
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat df2 = new SimpleDateFormat("yyyyMMddHhmmss");
        String formattedDate = df.format(c.getTime());
        String formattedDate1 = df1.format(c.getTime());
        String formattedDate2 = df2.format(c.getTime());

        tvDateOfVisit.setText(formattedDate);
        yearMonthDay = formattedDate1;
        sourceTransID = formattedDate2;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IntentIntegrator.REQUEST_CODE && resultCode == RESULT_OK) {
            IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

            if (intentResult.getContents() != null) {
                etQ5.setText(intentResult.getContents());
            } else {
                Toast.makeText(this, "Oops.. You did not scan anything", Toast.LENGTH_SHORT).show();
            }
        }


        if (requestCode == globalFunction.IMG_REQUEST_PICKER && resultCode == Activity.RESULT_OK) {

            if (data != null) {

                Uri path = data.getData();
                imageString1 = globalFunction.ProcessingBitmap(path);
                btnUploadCounterPic.setText("Re-select Photo");

            }

        }

        if (requestCode == globalFunction.IMG_REQUEST_CAMERA && resultCode == Activity.RESULT_OK) {

            imageString1 = globalFunction.ProcessingBitmap(globalFunction.outputFileUri);
            btnUploadCounterPic.setText("Re-select Photo");

        }

        if (requestCode == globalFunction.IMG_REQUEST_PICKER2 && resultCode == Activity.RESULT_OK) {

            if (data != null) {

                Uri path = data.getData();
                imageString2 = globalFunction.ProcessingBitmap(path);
                btnSQRPicture.setText("Re-select Photo");

            }

        }

        if (requestCode == globalFunction.IMG_REQUEST_CAMERA2 && resultCode == Activity.RESULT_OK) {

            imageString2 = globalFunction.ProcessingBitmap(globalFunction.outputFileUri);
            btnSQRPicture.setText("Re-select Photo");

        }

        if (requestCode == globalFunction.IMG_REQUEST_PICKER3 && resultCode == Activity.RESULT_OK) {

            if (data != null) {

                Uri path = data.getData();
                imageString3 = globalFunction.ProcessingBitmap(path);
                btnPisoTest.setText("Re-select Photo");

            }

        }

        if (requestCode == globalFunction.IMG_REQUEST_CAMERA3 && resultCode == Activity.RESULT_OK) {

            imageString3 = globalFunction.ProcessingBitmap(globalFunction.outputFileUri);
            btnPisoTest.setText("Re-select Photo");

        }

        if (requestCode == globalFunction.IMG_REQUEST_PICKERSN1 && resultCode == Activity.RESULT_OK) {

            if (data != null) {

                Uri path = data.getData();
                imageStringSN1 = globalFunction.ProcessingBitmap(path);
                btnSerial00.setText("DONE");

            }

        }

        if (requestCode == globalFunction.IMG_REQUEST_CAMERASN1 && resultCode == Activity.RESULT_OK) {

            imageStringSN1 = globalFunction.ProcessingBitmap(globalFunction.outputFileUri);
            btnSerial00.setText("DONE");

        }

        if (requestCode == globalFunction.IMG_REQUEST_PICKERSN2 && resultCode == Activity.RESULT_OK) {

            if (data != null) {

                Uri path = data.getData();
                imageStringSN2 = globalFunction.ProcessingBitmap(path);
                btnSerial2.setText("DONE");

            }

        }

        if (requestCode == globalFunction.IMG_REQUEST_CAMERASN2 && resultCode == Activity.RESULT_OK) {

            imageStringSN2 = globalFunction.ProcessingBitmap(globalFunction.outputFileUri);
            btnSerial2.setText("DONE");

        }

        if (requestCode == globalFunction.IMG_REQUEST_PICKERSN3 && resultCode == Activity.RESULT_OK) {

            if (data != null) {

                Uri path = data.getData();
                imageStringSN3 = globalFunction.ProcessingBitmap(path);
                btnSerial3.setText("DONE");

            }

        }

        if (requestCode == globalFunction.IMG_REQUEST_CAMERASN3 && resultCode == Activity.RESULT_OK) {

            imageStringSN3 = globalFunction.ProcessingBitmap(globalFunction.outputFileUri);
            btnSerial3.setText("DONE");

        }

        if (requestCode == globalFunction.IMG_REQUEST_PICKERSN4 && resultCode == Activity.RESULT_OK) {

            if (data != null) {

                Uri path = data.getData();
                imageStringSN4 = globalFunction.ProcessingBitmap(path);
                btnSerial4.setText("DONE");

            }

        }

        if (requestCode == globalFunction.IMG_REQUEST_CAMERASN4 && resultCode == Activity.RESULT_OK) {

            imageStringSN4 = globalFunction.ProcessingBitmap(globalFunction.outputFileUri);
            btnSerial4.setText("DONE");

        }

        if (requestCode == globalFunction.IMG_REQUEST_PICKERSN5 && resultCode == Activity.RESULT_OK) {

            if (data != null) {

                Uri path = data.getData();
                imageStringSN5 = globalFunction.ProcessingBitmap(path);
                btnSerial5.setText("DONE");

            }

        }

        if (requestCode == globalFunction.IMG_REQUEST_CAMERASN5 && resultCode == Activity.RESULT_OK) {

            imageStringSN5 = globalFunction.ProcessingBitmap(globalFunction.outputFileUri);
            btnSerial5.setText("DONE");

        }

        if (requestCode == globalFunction.IMG_REQUEST_PICKERSN6 && resultCode == Activity.RESULT_OK) {

            if (data != null) {

                Uri path = data.getData();
                imageStringSN6 = globalFunction.ProcessingBitmap(path);
                btnSerial6.setText("DONE");

            }

        }

        if (requestCode == globalFunction.IMG_REQUEST_CAMERASN7 && resultCode == Activity.RESULT_OK) {

            imageStringSN7 = globalFunction.ProcessingBitmap(globalFunction.outputFileUri);
            btnSerial7.setText("DONE");

        }

        if (requestCode == globalFunction.IMG_REQUEST_PICKERSN7 && resultCode == Activity.RESULT_OK) {

            if (data != null) {

                Uri path = data.getData();
                imageStringSN7 = globalFunction.ProcessingBitmap(path);
                btnSerial7.setText("DONE");

            }

        }

        if (requestCode == globalFunction.IMG_REQUEST_CAMERASN8 && resultCode == Activity.RESULT_OK) {

            imageStringSN8 = globalFunction.ProcessingBitmap(globalFunction.outputFileUri);
            btnSerial8.setText("DONE");

        }

        if (requestCode == globalFunction.IMG_REQUEST_PICKERSN8 && resultCode == Activity.RESULT_OK) {

            if (data != null) {

                Uri path = data.getData();
                imageStringSN8 = globalFunction.ProcessingBitmap(path);
                btnSerial8.setText("DONE");

            }

        }

        if (requestCode == globalFunction.IMG_REQUEST_CAMERASN8 && resultCode == Activity.RESULT_OK) {

            imageStringSN8 = globalFunction.ProcessingBitmap(globalFunction.outputFileUri);
            btnSerial8.setText("DONE");

        }

        if (requestCode == globalFunction.IMG_REQUEST_PICKERSN9 && resultCode == Activity.RESULT_OK) {

            if (data != null) {

                Uri path = data.getData();
                imageStringSN9 = globalFunction.ProcessingBitmap(path);
                btnSerial9.setText("DONE");

            }

        }

        if (requestCode == globalFunction.IMG_REQUEST_CAMERASN9 && resultCode == Activity.RESULT_OK) {

            imageStringSN9 = globalFunction.ProcessingBitmap(globalFunction.outputFileUri);
            btnSerial9.setText("DONE");

        }

        if (requestCode == globalFunction.IMG_REQUEST_PICKERSN10 && resultCode == Activity.RESULT_OK) {

            if (data != null) {

                Uri path = data.getData();
                imageStringSN10 = globalFunction.ProcessingBitmap(path);
                btnSerial10.setText("DONE");

            }

        }

        if (requestCode == globalFunction.IMG_REQUEST_CAMERASN10 && resultCode == Activity.RESULT_OK) {

            imageStringSN10 = globalFunction.ProcessingBitmap(globalFunction.outputFileUri);
            btnSerial10.setText("DONE");

        }

        if (requestCode == globalFunction.IMG_REQUEST_PICKERSQR1 && resultCode == Activity.RESULT_OK) {

            if (data != null) {

                Uri path = data.getData();

                if (GlobalVariables.deliveryPhotos.size() < 5) {

                    image = globalFunction.ProcessingBitmap(path);
                    GlobalVariables.deliveryPhotos.add(image);
                    btnSQRCount.setText("Selected Photo: " + GlobalVariables.deliveryPhotos.size());

                } else {
                    Toast.makeText(this, "Maximum of 5 Pictures allowed.", Toast.LENGTH_SHORT).show();
                }

            }

        }

        if (requestCode == globalFunction.IMG_REQUEST_CAMERASQR1 && resultCode == Activity.RESULT_OK) {

            if (GlobalVariables.deliveryPhotos.size() < 5) {

                image = globalFunction.ProcessingBitmap(globalFunction.outputFileUri);
                GlobalVariables.deliveryPhotos.add(image);
                btnSQRCount.setText("Selected Photo: " + GlobalVariables.deliveryPhotos.size());
            } else {
                Toast.makeText(this, "Maximum of 5 Pictures allowed.", Toast.LENGTH_SHORT).show();
            }


        }

        if (requestCode == globalFunction.IMG_REQUEST_PICKEREXTRA && resultCode == Activity.RESULT_OK) {

            if (data != null) {

                Uri path = data.getData();

                if (GlobalVariables.extraPhotos.size() < 5) {

                    image2 = globalFunction.ProcessingBitmap(path);
                    GlobalVariables.extraPhotos.add(image2);
                    btnEXTRACount.setText("Selected Photo: " + GlobalVariables.extraPhotos.size());

                } else {
                    Toast.makeText(this, "Maximum of 5 Pictures allowed.", Toast.LENGTH_SHORT).show();
                }

            }

        }

        if (requestCode == globalFunction.IMG_REQUEST_CAMERAEXTRA && resultCode == Activity.RESULT_OK) {

            if (GlobalVariables.extraPhotos.size() < 5) {

                image2 = globalFunction.ProcessingBitmap(globalFunction.outputFileUri);
                GlobalVariables.extraPhotos.add(image2);
                btnEXTRACount.setText("Selected Photo: " + GlobalVariables.extraPhotos.size());
            } else {
                Toast.makeText(this, "Maximum of 5 Pictures allowed.", Toast.LENGTH_SHORT).show();
            }


        }

        if (requestCode == globalFunction.IMG_REQUEST_PICKERPISOTEST && resultCode == Activity.RESULT_OK) {

            if (data != null) {

                Uri path = data.getData();

                if (GlobalVariables.pisoTestPhotos.size() < 2) {

                    image3 = globalFunction.ProcessingBitmap(path);
                    GlobalVariables.pisoTestPhotos.add(image3);
                    btnPisoTest.setText("Selected Photo: " + GlobalVariables.pisoTestPhotos.size());

                } else {
                    Toast.makeText(this, "Maximum of 2 Pictures allowed.", Toast.LENGTH_SHORT).show();
                }

            }

        }

        if (requestCode == globalFunction.IMG_REQUEST_CAMERAPISOTEST && resultCode == Activity.RESULT_OK) {

            if (GlobalVariables.pisoTestPhotos.size() < 2) {

                image3 = globalFunction.ProcessingBitmap(globalFunction.outputFileUri);
                GlobalVariables.pisoTestPhotos.add(image3);
                btnPisoTest.setText("Selected Photo: " + GlobalVariables.pisoTestPhotos.size());
            } else {
                Toast.makeText(this, "Maximum of 2 Pictures allowed.", Toast.LENGTH_SHORT).show();
            }


        }

    }

    private void mercRestrictionCheckBox() {
        // initialize selected language array
        selectedMercRes = new boolean[mercResArray.length];

        tvMercRestrict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Initialize alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(SurveyFormActivity.this);

                // set title
                builder.setTitle("Merchant Restriction");

                // set dialog non cancelable
                builder.setCancelable(false);

                builder.setMultiChoiceItems(mercResArray, selectedMercRes, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        // check condition
                        if (b) {
                            // when checkbox selected
                            // Add position  in lang list
                            MercResList.add(i);
                            // Sort array list
                            Collections.sort(MercResList);
                        } else {
                            // when checkbox unselected
                            // Remove position from langList
                            MercResList.remove(Integer.valueOf(i));
                        }
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Initialize string builder
                        StringBuilder stringBuilder = new StringBuilder();
                        // use for loop
                        for (int j = 0; j < MercResList.size(); j++) {
                            // concat array value
                            stringBuilder.append(mercResArray[MercResList.get(j)]);
                            // check condition
                            if (j != MercResList.size() - 1) {
                                // When j value  not equal
                                // to lang list size - 1
                                // add comma
                                stringBuilder.append(", ");
                            }
                        }
                        // set text on textView
                        tvMercRestrict.setText(stringBuilder.toString());
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // dismiss dialog
                        dialogInterface.dismiss();
                    }
                });
                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // use for loop
                        for (int j = 0; j < selectedMercRes.length; j++) {
                            // remove all selection
                            selectedMercRes[j] = false;
                            // clear language list
                            MercResList.clear();
                            // clear text view value
                            tvMercRestrict.setText("");
                        }
                    }
                });
                // show dialog
                builder.show();
            }
        });

    }

    private void availSQRCheckBox() {
        // initialize selected language array
        selectedAvailSQR = new boolean[availSQRArray.length];

        tvAvailSQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Initialize alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(SurveyFormActivity.this);

                // set title
                builder.setTitle("Available SQR");

                // set dialog non cancelable
                builder.setCancelable(false);

                builder.setMultiChoiceItems(availSQRArray, selectedAvailSQR, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        // check condition
                        if (b) {
                            // when checkbox selected
                            // Add position  in lang list
                            sqrList.add(i);
                            // Sort array list
                            Collections.sort(sqrList);
                        } else {
                            // when checkbox unselected
                            // Remove position from langList
                            sqrList.remove(Integer.valueOf(i));
                        }
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Initialize string builder
                        StringBuilder stringBuilder = new StringBuilder();
                        // use for loop
                        for (int j = 0; j < sqrList.size(); j++) {
                            // concat array value
                            stringBuilder.append(availSQRArray[sqrList.get(j)]);
                            // check condition
                            if (j != sqrList.size() - 1) {
                                // When j value  not equal
                                // to lang list size - 1
                                // add comma
                                stringBuilder.append(", ");
                            }
                        }
                        // set text on textView
                        tvAvailSQR.setText(stringBuilder.toString());
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // dismiss dialog
                        dialogInterface.dismiss();
                    }
                });
                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // use for loop
                        for (int j = 0; j < selectedAvailSQR.length; j++) {
                            // remove all selection
                            selectedAvailSQR[j] = false;
                            // clear language list
                            sqrList.clear();
                            // clear text view value
                            tvAvailSQR.setText("");
                        }
                    }
                });
                // show dialog
                builder.show();
            }
        });
    }

    private void otherMercVisibleCheckBox() {
        // initialize selected language array
        selectedOtherMerc = new boolean[otherMercArray.length];

        tvOtherMercVisible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Initialize alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(SurveyFormActivity.this);

                // set title
                builder.setTitle("Other Merchant");

                // set dialog non cancelable
                builder.setCancelable(false);

                builder.setMultiChoiceItems(otherMercArray, selectedOtherMerc, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        // check condition
                        if (b) {
                            // when checkbox selected
                            // Add position  in lang list
                            otherMercList.add(i);
                            // Sort array list
                            Collections.sort(otherMercList);
                        } else {
                            // when checkbox unselected
                            // Remove position from langList
                            otherMercList.remove(Integer.valueOf(i));
                        }
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Initialize string builder
                        StringBuilder stringBuilder = new StringBuilder();
                        // use for loop
                        for (int j = 0; j < otherMercList.size(); j++) {
                            // concat array value
                            stringBuilder.append(otherMercArray[otherMercList.get(j)]);
                            // check condition
                            if (j != otherMercList.size() - 1) {
                                // When j value  not equal
                                // to lang list size - 1
                                // add comma
                                stringBuilder.append(", ");
                            }
                        }
                        // set text on textView
                        tvOtherMercVisible.setText(stringBuilder.toString());
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // dismiss dialog
                        dialogInterface.dismiss();
                    }
                });
                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // use for loop
                        for (int j = 0; j < selectedOtherMerc.length; j++) {
                            // remove all selection
                            selectedOtherMerc[j] = false;
                            // clear language list
                            otherMercList.clear();
                            // clear text view value
                            tvOtherMercVisible.setText("");
                        }
                    }
                });
                // show dialog
                builder.show();
            }
        });
    }

    private void terminalCheckBox() {
        // initialize selected language array
        selectedTerminals = new boolean[terminalArray.length];

        tvTerminalAvailable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Initialize alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(SurveyFormActivity.this);

                // set title
                builder.setTitle("Terminal Available");

                // set dialog non cancelable
                builder.setCancelable(false);

                builder.setMultiChoiceItems(terminalArray, selectedTerminals, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        // check condition
                        if (b) {
                            // when checkbox selected
                            // Add position  in lang list
                            terminalList.add(i);
                            // Sort array list
                            Collections.sort(terminalList);
                        } else {
                            // when checkbox unselected
                            // Remove position from langList
                            terminalList.remove(Integer.valueOf(i));
                        }
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Initialize string builder
                        StringBuilder stringBuilder = new StringBuilder();
                        // use for loop
                        for (int j = 0; j < terminalList.size(); j++) {
                            // concat array value
                            stringBuilder.append(terminalArray[terminalList.get(j)]);
                            // check condition
                            if (j != terminalList.size() - 1) {
                                // When j value  not equal
                                // to lang list size - 1
                                // add comma
                                stringBuilder.append(", ");
                            }
                        }
                        // set text on textView
                        tvTerminalAvailable.setText(stringBuilder.toString());
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // dismiss dialog
                        dialogInterface.dismiss();
                    }
                });
                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // use for loop
                        for (int j = 0; j < selectedTerminals.length; j++) {
                            // remove all selection
                            selectedTerminals[j] = false;
                            // clear language list
                            terminalList.clear();
                            // clear text view value
                            tvTerminalAvailable.setText("");
                        }
                    }
                });
                // show dialog
                builder.show();
            }
        });
    }

    private void AddingItemToSpinnerStatus() {
        // accounts
        ArrayAdapter<String> arrayAdapterSpinnerStatus = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, offDB.RetrieveStatus()) {
            @Override
            public boolean isEnabled(int position) {
                return position > 1;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        arrayAdapterSpinnerStatus.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spMayaStatus.setAdapter(arrayAdapterSpinnerStatus);

        spMayaStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mayaStatus = spMayaStatus.getSelectedItem().toString();
                if (mayaStatus.equals("Not Entertained") || mayaStatus.equals("On-going Affiliation") || mayaStatus.equals("Pull out Terminal")) {
                    MerchantMayaHide();
                } else if (mayaStatus.equals("Maya")) {

                    MercShow();
                } else if (mayaStatus.equals("Non-Maya")) {
                    MerchantNonMayaHide();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void onRBClickedGcashStaticQR(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.rb_sGcashStaticQrYes:
                if (checked)
                    gCashStaticQR = 1;
                break;
            case R.id.rb_sGcashStaticQrNo:
                if (checked)
                    gCashStaticQR = 0;
                break;
        }
    }

    public void onRBClickedGcashQRIPOS(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.rb_sGcashQRIPOSYes:
                if (checked)
                    gCashQRInsidePOS = 1;
                break;
            case R.id.rb_sGcashQRIPOSNo:
                if (checked)
                    gCashQRInsidePOS = 0;
                break;
        }
    }

    public void onRBClickedGcashBoth(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.rb_sGcashBothYes:
                if (checked)
                    gCashBoth = 1;
                break;
            case R.id.rb_sGcashBothNo:
                if (checked)
                    gCashBoth = 0;
                break;
        }
    }

    public void onRBClickedMayaHidden(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.rb_sMayaHiddenYes:
                if (checked)
                    mayaHidden = 1;
                break;
            case R.id.rb_sMayaHiddenNo:
                if (checked)
                    mayaHidden = 0;
                break;
        }
    }

    public void onRBClickedMayaStandee(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.rb_sMayaStandeeYes:
                if (checked)
                    mayaStandee = 1;
                break;
            case R.id.rb_sMayaStandeeNo:
                if (checked)
                    mayaStandee = 0;
                break;
        }
    }

    public void onRBClickedMayaDH(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.rb_sMayaDHYes:
                if (checked)
                    mayaDoorHanger = 1;
                break;
            case R.id.rb_sMayaDHNo:
                if (checked)
                    mayaDoorHanger = 0;
                break;
        }
    }

    public void onRBClickedMayaNone(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.rb_sMayaNoneYes:
                if (checked)
                    mayaNone = 1;
                break;
            case R.id.rb_sMayaNoneNo:
                if (checked)
                    mayaNone = 0;
                break;
        }
    }

    public void onRBClickedNonMayaStandee(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.rb_sNonMayaStandeeYes:
                if (checked)
                    nonMayaStandee = 1;
                break;
            case R.id.rb_sNonMayaStandeeNo:
                if (checked)
                    nonMayaStandee = 0;
                break;
        }
    }

    public void onRBClickedNonMayaDH(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.rb_sNonMayaDHYes:
                if (checked)
                    nonMayaDoorHanger = 1;
                break;
            case R.id.rb_sNonMayaDHNo:
                if (checked)
                    nonMayaDoorHanger = 0;
                break;
        }
    }

    public void onRBClickedNonMayaNone(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.rb_sNonMayaNoneYes:
                if (checked)
                    nonMayaNone = 1;
                break;
            case R.id.rb_sNonMayaNoneNo:
                if (checked)
                    nonMayaNone = 0;
                break;
        }
    }

    public void onRBClickedQrGB(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.rb_sQrGBYes:
                if (checked)
                    qrGreenBird = 1;
                break;
            case R.id.rb_sQrGBNo:
                if (checked)
                    qrGreenBird = 0;
                break;
        }
    }

    public void onRBClickedMaya2zero(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.rb_sMaya2zeroYes:
                if (checked)
                    qrMaya2 = 1;
                break;
            case R.id.rb_sMaya2zeroNo:
                if (checked)
                    qrMaya2 = 0;
                break;
        }
    }

    public void onRBClickedQ1(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.rb_sQ1Yes:
                if (checked)
                    otherPaymentEwallet = 1;
                break;
            case R.id.rb_sQ1No:
                if (checked)
                    otherPaymentEwallet = 0;
                break;
        }
    }

    public void MerchantMayaHide() {

        for (RelativeLayout rl : Arrays.asList(RL1, RL2, RL3, RL4, RL5, RL6, RL7, RL8, RL9, RL10, RL11, RL12, RL13)) {
            rl.setVisibility(View.GONE);
        }
        for (TextView t1 : Arrays.asList(tvGapa, tvMaya, tvNonMaya, text1, text2, text3, text4, text5, text6, tvTerminalAvailable
                , tvTerminalAvailable, tvTerminalAvailable, tvAvailSQR, tvQRVersion, tvMercRestrict)) {
            t1.setVisibility(View.GONE);
        }
        for (View v1 : Arrays.asList(line1, line2, line3, line4, line5, line6, line7)) {
            v1.setVisibility(View.GONE);
        }
        for (EditText e1 : Arrays.asList(etQ2, etQ3, etQ4)) {
            e1.setVisibility(View.GONE);
        }

        LL1.setVisibility(View.GONE);
        btnSQRPicture.setVisibility(View.GONE);
        btnPisoTest.setVisibility(View.GONE);
        etCompDelAdd.setVisibility(View.VISIBLE);
        btnSerial.setVisibility(View.GONE);
        etRemarks.setVisibility(View.VISIBLE);
        btnEXTRACount.setVisibility(View.GONE);
        btnSQRCount.setVisibility(View.GONE);
    }

    public void MerchantNonMayaHide() {

        for (RelativeLayout rl : Arrays.asList(RL1, RL2, RL3, RL4, RL5, RL6, RL7, RL8, RL9, RL10, RL11, RL12, RL13)) {
            rl.setVisibility(View.GONE);
        }
        for (TextView t1 : Arrays.asList(tvGapa, tvMaya, text1, text2, text3, text4, text5, text6, tvTerminalAvailable
                , tvNonMaya, tvAvailSQR, tvQRVersion, tvMercRestrict, tvOtherMercVisible)) {
            t1.setVisibility(View.GONE);
        }
        for (View v1 : Arrays.asList(line1, line2, line3, line4, line5, line6, line7)) {
            v1.setVisibility(View.GONE);
        }
        for (EditText e1 : Arrays.asList(etQ2, etQ3, etQ4, etCompDelAdd, etRemarks)) {
            e1.setVisibility(View.GONE);
        }
        LL1.setVisibility(View.GONE);
        btnSQRPicture.setVisibility(View.GONE);
        btnSerial.setVisibility(View.GONE);
        btnPisoTest.setVisibility(View.GONE);
        btnEXTRACount.setVisibility(View.GONE);
        btnSQRCount.setVisibility(View.GONE);
        etCompDelAdd.setVisibility(View.GONE);
        etRemarks.setVisibility(View.GONE);
    }

    public void MercShow() {
        etDBAName.setVisibility(View.VISIBLE);
        etRegBizName.setVisibility(View.VISIBLE);
        etSubArea.setVisibility(View.VISIBLE);
        etMercSPOCName.setVisibility(View.VISIBLE);
        etMercSPOCDesig.setVisibility(View.VISIBLE);
        etMercSPOCEmail.setVisibility(View.VISIBLE);
        etMercSPOCContact.setVisibility(View.VISIBLE);
        for (RelativeLayout rl : Arrays.asList(RL1, RL2, RL4, RL5, RL6, RL7, RL11, RL12, RL13)) {
            rl.setVisibility(View.VISIBLE);
        }
        for (TextView t1 : Arrays.asList(tvGapa, tvMaya, text4, text5, text6, tvQRVersion, tvMercRestrict)) {
            t1.setVisibility(View.VISIBLE);
        }
        for (View v1 : Arrays.asList(line1, line2, line3, line5, line6, line7)) {
            v1.setVisibility(View.VISIBLE);
        }
        for (EditText e1 : Arrays.asList(etQ2, etQ3, etQ4)) {
            e1.setVisibility(View.VISIBLE);
        }
        RL3.setVisibility(View.GONE);
        RL8.setVisibility(View.GONE);
        RL9.setVisibility(View.GONE);
        RL10.setVisibility(View.GONE);
        line4.setVisibility(View.GONE);
        LL1.setVisibility(View.VISIBLE);
        text1.setVisibility(View.GONE);
        text2.setVisibility(View.GONE);
        text3.setVisibility(View.GONE);
        tvTerminalAvailable.setVisibility(View.GONE);
        tvNonMaya.setVisibility(View.GONE);
        tvOtherMercVisible.setVisibility(View.GONE);
        tvAvailSQR.setVisibility(View.GONE);
        btnSQRPicture.setVisibility(View.VISIBLE);
        etCompDelAdd.setVisibility(View.GONE);
        etRemarks.setVisibility(View.GONE);
    }

    private void AddingItemToSN1() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.terminaltype, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSN1.setAdapter(adapter);

        spSN1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Terminal Type")) {

                } else {
                    String text = parent.getItemAtPosition(position).toString();
                    textSN1 = text;
//                    Toast.makeText(parent.getContext(), textSN1 + "spsn1", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    private void AddingItemToSN2() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.terminaltype, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSN2.setAdapter(adapter);

        spSN2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Terminal Type")) {

                } else {
                    String text = parent.getItemAtPosition(position).toString();
                    textSN2 = text;
//                    Toast.makeText(parent.getContext(), textSN2 + "spsn2", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    private void AddingItemToSN3() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.terminaltype, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSN3.setAdapter(adapter);

        spSN3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Terminal Type")) {

                } else {
                    String text = parent.getItemAtPosition(position).toString();
                    textSN3 = text;
//                    Toast.makeText(parent.getContext(), textSN3 + "spsn3", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    private void AddingItemToSN4() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.terminaltype, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSN4.setAdapter(adapter);

        spSN4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Terminal Type")) {

                } else {
                    String text = parent.getItemAtPosition(position).toString();
                    textSN4 = text;
//                    Toast.makeText(parent.getContext(), textSN4 + "spsn4", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    private void AddingItemToSN5() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.terminaltype, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSN5.setAdapter(adapter);

        spSN5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Terminal Type")) {

                } else {
                    String text = parent.getItemAtPosition(position).toString();
                    textSN5 = text;
//                    Toast.makeText(parent.getContext(), textSN5 + "spsn5", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    private void AddingItemToSN6() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.terminaltype, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSN6.setAdapter(adapter);

        spSN6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Terminal Type")) {

                } else {
                    String text = parent.getItemAtPosition(position).toString();
                    textSN6 = text;
//                    Toast.makeText(parent.getContext(), textSN6 + "spsn6", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    private void AddingItemToSN7() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.terminaltype, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSN7.setAdapter(adapter);

        spSN7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Terminal Type")) {

                } else {
                    String text = parent.getItemAtPosition(position).toString();
                    textSN7 = text;
//                    Toast.makeText(parent.getContext(), textSN7 + "spsn7", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    private void AddingItemToSN8() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.terminaltype, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSN8.setAdapter(adapter);

        spSN8.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Terminal Type")) {

                } else {
                    String text = parent.getItemAtPosition(position).toString();
                    textSN8 = text;
//                    Toast.makeText(parent.getContext(), textSN8 + "spsn8", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    private void AddingItemToSN9() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.terminaltype, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSN9.setAdapter(adapter);

        spSN9.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Terminal Type")) {

                } else {
                    String text = parent.getItemAtPosition(position).toString();
                    textSN9 = text;
//                    Toast.makeText(parent.getContext(), textSN9 + "spsn9", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    private void AddingItemToSN10() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.terminaltype, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSN10.setAdapter(adapter);

        spSN10.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Terminal Type")) {

                } else {
                    String text = parent.getItemAtPosition(position).toString();
                    textSN10 = text;
//                    Toast.makeText(parent.getContext(), textSN10 + "spsn10", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }
}