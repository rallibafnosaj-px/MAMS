package com.example.survey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hanks.passcodeview.PasscodeView;

public class PinVerifyActivity extends AppCompatActivity {

    PasscodeView passcodeView;
    TextView tvBackToLogin;


    SQLite.LoginActivitySQLite offDB = new SQLite.LoginActivitySQLite(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_verify);

        VariableCasting();

        passcodeView.setPasscodeLength(4)
                .setLocalPasscode(offDB.getPinCode())
                .setListener(new PasscodeView.PasscodeViewListener() {
                    @Override
                    public void onFail() {


                    }

                    @Override
                    public void onSuccess(String number) {
//                        ValidationLogin();

                        Intent intent = new Intent(PinVerifyActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
        tvBackToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                offDB.DeleteLoginVerify();
                Intent intent = new Intent(PinVerifyActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }

    private void VariableCasting() {
        passcodeView = findViewById(R.id.passcodeView);
        tvBackToLogin = findViewById(R.id.tv_backToLogin);
    }


}