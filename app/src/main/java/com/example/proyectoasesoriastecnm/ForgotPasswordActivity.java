package com.example.proyectoasesoriastecnm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotPasswordActivity extends AppCompatActivity  implements View.OnClickListener{

    private EditText emailEditText;
    private Button resetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        emailEditText = (EditText) findViewById(R.id.email);
        resetPassword = (Button) findViewById(R.id.resetPassword);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.resetPassword:
                //startActivity(new Intent(this,MainActivity.class));
                Toast.makeText(ForgotPasswordActivity.this, "Sent, verify your email", Toast.LENGTH_LONG).show();
                break;
        }
    }
}