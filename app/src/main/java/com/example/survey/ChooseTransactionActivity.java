package com.example.survey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseTransactionActivity extends AppCompatActivity {

    Button btnMaya, btnNonMaya;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_transaction);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        VariableCasting();

        btnMaya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GlobalVariables.forMayaSetup = "Maya";
                startActivity(new Intent(getApplicationContext(), SurveyFormActivity.class));

            }
        });

        btnNonMaya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalVariables.forMayaSetup = "Non-Maya";
                startActivity(new Intent(getApplicationContext(), SurveyFormActivity.class));
            }
        });
    }

    private void VariableCasting() {
        btnMaya = findViewById(R.id.btn_maya);
        btnNonMaya = findViewById(R.id.btn_nonmaya);
    }
}