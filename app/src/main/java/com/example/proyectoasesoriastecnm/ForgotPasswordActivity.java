package com.example.proyectoasesoriastecnm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ForgotPasswordActivity extends AppCompatActivity  implements View.OnClickListener{

    private Button signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        signIn = (Button) findViewById(R.id.signIn);
        signIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signIn:
                startActivity(new Intent(this,MainActivity.class));
                Toast.makeText(ForgotPasswordActivity.this, "Sent", Toast.LENGTH_LONG).show();
                break;
        }
    }
}