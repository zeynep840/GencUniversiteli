package com.example.university;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EgitimActivity extends AppCompatActivity {
    private Button egitim_ders1,egitim_ders2,egitim_ders3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_egitim);
        egitim_ders1=(Button)findViewById(R.id.egitim_ders1);
        egitim_ders2=(Button)findViewById(R.id.egitim_ders2);
        egitim_ders3=(Button)findViewById(R.id.egitim_ders3);
        egitim_ders1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ders1=new Intent(EgitimActivity.this,EgitimProjeOnerileriActivity.class);
                startActivity(ders1);
            }
        });
        egitim_ders2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ders2=new Intent(EgitimActivity.this,EgitimSorActivity.class);
                startActivity(ders2);
            }
        });
        egitim_ders3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ders3=new Intent(EgitimActivity.this,EgitimSosyalAktivitelerActivity.class);
                startActivity(ders3);
            }
        });
    }
}