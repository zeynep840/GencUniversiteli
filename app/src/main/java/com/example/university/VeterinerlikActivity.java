package com.example.university;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class VeterinerlikActivity extends AppCompatActivity {
    private Button veterinerlik_ders1,veterinerlik_ders2,veterinerlik_ders3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veterinerlik);
        veterinerlik_ders1=(Button)findViewById(R.id.veterinerlik_ders1);
        veterinerlik_ders2=(Button)findViewById(R.id.veterinerik_ders2);
        veterinerlik_ders3=(Button)findViewById(R.id.veterinerlik_ders3);
        veterinerlik_ders1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ders1=new Intent(VeterinerlikActivity.this,VeterinerlikProjeOnerileriActivity.class);
                startActivity(ders1);
            }
        });
        veterinerlik_ders2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ders2=new Intent(VeterinerlikActivity.this,VeterinerlikSorActivity.class);
                startActivity(ders2);
            }
        });
        veterinerlik_ders3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ders3=new Intent(VeterinerlikActivity.this,VeterinerlikSosyalAktivitelerActivity.class);
                startActivity(ders3);
            }
        });
    }
}