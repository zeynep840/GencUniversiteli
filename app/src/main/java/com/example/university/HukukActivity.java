package com.example.university;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HukukActivity extends AppCompatActivity {
    private Button hukuk_ders1,hukuk_ders2,hukuk_ders3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hukuk);
        hukuk_ders1=(Button)findViewById(R.id.hukuk_ders1);
        hukuk_ders2=(Button)findViewById(R.id.hukuk_ders2);
        hukuk_ders3=(Button)findViewById(R.id.hukuk_ders3);
        hukuk_ders1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ders1=new Intent(HukukActivity.this,HukukProjeOnerileriActivity.class);
                startActivity(ders1);
            }
        });
        hukuk_ders2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ders2=new Intent(HukukActivity.this,HukukSorActivity.class);
                startActivity(ders2);
            }
        });
        hukuk_ders3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ders3=new Intent(HukukActivity.this,HukukSosyalAktivitelerActivity.class);
                startActivity(ders3);
            }
        });
    }
}