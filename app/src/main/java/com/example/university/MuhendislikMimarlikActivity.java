package com.example.university;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MuhendislikMimarlikActivity extends AppCompatActivity {
    private Button muhendislik,mimarlik;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muhendislik_mimarlik);
        muhendislik=(Button)findViewById(R.id.muhendislik);
        mimarlik=(Button)findViewById(R.id.mimarlik);

       muhendislik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent doktor=new Intent(MuhendislikMimarlikActivity.this,MuhendislikActivity.class);
                startActivity(doktor);
            }
        });
        mimarlik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sosyolog=new Intent(MuhendislikMimarlikActivity.this,MimarlikSorActivity.class);
                startActivity(sosyolog);
            }
        });

    }
}