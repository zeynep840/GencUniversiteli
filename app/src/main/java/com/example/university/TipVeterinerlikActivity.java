package com.example.university;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TipVeterinerlikActivity extends AppCompatActivity {
    private Button tip,veterinerlik;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_veterinerlik);
        tip=(Button)findViewById(R.id. tip);
        veterinerlik=(Button)findViewById(R.id.veterinerlik);

        tip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent doktor=new Intent(TipVeterinerlikActivity.this,TipActivity.class);
                startActivity(doktor);
            }
        });
        veterinerlik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent veteriner=new Intent(TipVeterinerlikActivity.this,VeterinerlikActivity.class);
                startActivity(veteriner);
            }
        });

    }
}