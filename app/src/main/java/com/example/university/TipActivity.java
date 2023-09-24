package com.example.university;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TipActivity extends AppCompatActivity {
    private Button tip_ders1,tip_ders2,tip_ders3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip);
        tip_ders1=(Button)findViewById(R.id.tip_ders1);
        tip_ders2=(Button)findViewById(R.id.tip_ders2);
        tip_ders3=(Button)findViewById(R.id.tip_ders3);
        tip_ders1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ders1=new Intent(TipActivity.this,TipProjeOnerileriActivity.class);
                startActivity(ders1);
            }
        });
        tip_ders2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ders2=new Intent(TipActivity.this,MuhendislikSorActivity.class);
                startActivity(ders2);
            }
        });
        tip_ders3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ders3=new Intent(TipActivity.this,TipSosyalAktivitelerActivity.class);
                startActivity(ders3);
            }
        });
    }
}