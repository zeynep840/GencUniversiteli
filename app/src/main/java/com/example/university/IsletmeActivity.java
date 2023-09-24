package com.example.university;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class IsletmeActivity extends AppCompatActivity {
    private Button isletme_ders1,isletme_ders2,isletme_ders3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isletme);
        isletme_ders1=(Button)findViewById(R.id.isletme_ders1);
        isletme_ders2=(Button)findViewById(R.id.isletme_ders2);
        isletme_ders3=(Button)findViewById(R.id.isletme_ders3);
        isletme_ders1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ders1=new Intent(IsletmeActivity.this,IsletmeProjeOnerileriActivity.class);
                startActivity(ders1);
            }
        });
        isletme_ders2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ders2=new Intent(IsletmeActivity.this,IsletmeSorActivity.class);
                startActivity(ders2);
            }
        });
        isletme_ders3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ders3=new Intent(IsletmeActivity.this,IsletmeSosyalAktivitelerActivity.class);
                startActivity(ders3);
            }
        });
    }
}