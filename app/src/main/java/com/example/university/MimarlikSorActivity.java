package com.example.university;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MimarlikSorActivity extends AppCompatActivity {
    private Button mimarlik_ders1,mimarlik_ders2,mimarlik_ders3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mimarlik_sor);
        mimarlik_ders1=(Button)findViewById(R.id.mimarlik_ders1);
        mimarlik_ders2=(Button)findViewById(R.id.mimarlik_ders2);
        mimarlik_ders3=(Button)findViewById(R.id.mimarlik_ders3);
        mimarlik_ders1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ders1=new Intent(MimarlikSorActivity.this,MimarlikProjeOnerileriActivity.class);
                startActivity(ders1);
            }
        });
        mimarlik_ders2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ders2=new Intent(MimarlikSorActivity.this,MuhendislikActivity.class);
                startActivity(ders2);
            }
        });
        mimarlik_ders3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ders3=new Intent(MimarlikSorActivity.this,MimarlikSosyalAktivitelerActivity.class);
                startActivity(ders3);
            }
        });
    }
}