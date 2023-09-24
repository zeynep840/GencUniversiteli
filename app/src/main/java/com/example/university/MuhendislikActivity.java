package com.example.university;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MuhendislikActivity extends AppCompatActivity {
    private Button muhendislik_ders1,muhendislik_ders2,muhendislik_ders3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muhendislik);
       muhendislik_ders1=(Button)findViewById(R.id.muhendislik_ders1);
        muhendislik_ders2=(Button)findViewById(R.id.muhendislik_ders2);
        muhendislik_ders3=(Button)findViewById(R.id.muhendislik_ders3);
        muhendislik_ders1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ders1=new Intent(MuhendislikActivity.this,MuhendislikProjeOnerileriActivity.class);
                startActivity(ders1);
            }
        });
       muhendislik_ders2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ders2=new Intent(MuhendislikActivity.this,MuhendislikSorActivity.class);
                startActivity(ders2);
            }
        });
        muhendislik_ders3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ders3=new Intent(MuhendislikActivity.this,MuhendislikSosyalAktivitelerActivity.class);
                startActivity(ders3);
            }
        });
    }
}