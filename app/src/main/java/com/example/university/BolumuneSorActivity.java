package com.example.university;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BolumuneSorActivity extends AppCompatActivity {
    private Button muhendislik_mimarlik,saglik_tip,hukuk,isletme,egitim_ögretim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bolumune_sor);
        muhendislik_mimarlik=(Button)findViewById(R.id.muhendislik_mimarlik);
        hukuk=(Button)findViewById(R.id.hukuk);
        isletme=(Button)findViewById(R.id.isletme);
        egitim_ögretim=(Button)findViewById(R.id.egitim_ogretim);
        saglik_tip=(Button)findViewById(R.id.saglik_tip);


        muhendislik_mimarlik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent muhendislik=new Intent(BolumuneSorActivity.this,MuhendislikMimarlikActivity.class);
                startActivity(muhendislik);
            }
        });
        hukuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hukuk=new Intent(BolumuneSorActivity.this,HukukActivity.class);
                startActivity(hukuk);
            }
        });
        isletme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent isletme=new Intent(BolumuneSorActivity.this,IsletmeActivity.class);
                startActivity(isletme);
            }
        });
        egitim_ögretim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent egitim=new Intent(BolumuneSorActivity.this,EgitimActivity.class);
                startActivity(egitim);
            }
        });

        saglik_tip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent egitim=new Intent(BolumuneSorActivity.this,TipVeterinerlikActivity.class);
                startActivity(egitim);
            }
        });
    }
}