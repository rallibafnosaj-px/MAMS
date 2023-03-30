package com.example.survey;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PinActivity extends AppCompatActivity {

    Button btnUpdatePin;
    EditText etNewPin, etVerifyPin;
    String newpin,verifypin;

    SQLite.MainActivitySQLite offMainDB = new SQLite.MainActivitySQLite(this);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_pin_dialog);

        VariableCasting();

        btnUpdatePin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newpin = etNewPin.getText().toString();
                verifypin = etVerifyPin.getText().toString();

                if (newpin.equals(verifypin)){
                    offMainDB.InsertPin(newpin);
                    finish();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    Toast.makeText(PinActivity.this, "Pin setup is completed", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(PinActivity.this, "Pin not matched", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void VariableCasting() {
        btnUpdatePin = findViewById(R.id.btn_updatePin);
        etNewPin = findViewById(R.id.et_newPin);
        etVerifyPin = findViewById(R.id.et_verifyPin);
    }
}
