package com.example.university;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MuhendislikSorClickPostActivity extends AppCompatActivity {
    private ImageView PostImage1;
    private TextView PostDesription1;
    private Button DeletePostButton1,EditPostButon1;
    private String PostKey3;
    private DatabaseReference ClickPostRef1;
    private FirebaseAuth mAuth1;
    private String currentUserID1,databaseUserID1,description2,image1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muhendislik_sor_click_post);
        PostDesription1=(TextView)findViewById(R.id.yorum_yapmak1);
        DeletePostButton1=(Button)findViewById(R.id.postsil1);
        EditPostButon1=(Button)findViewById(R.id.postkaydet1);
        PostKey3=getIntent().getExtras().get("PostKey3").toString();
        ClickPostRef1= FirebaseDatabase.getInstance().getReference().child("MuhendislikSor").child(PostKey3);
        mAuth1=FirebaseAuth.getInstance();
        currentUserID1=mAuth1.getCurrentUser().getUid();
        DeletePostButton1.setVisibility(View.INVISIBLE);
        EditPostButon1.setVisibility(View.INVISIBLE);



        ClickPostRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    description2 = dataSnapshot.child("description2").getValue().toString();

                    databaseUserID1 = dataSnapshot.child("postid2").getValue().toString();
                    PostDesription1.setText(description2);

                    if (currentUserID1.equals(databaseUserID1)) {
                        DeletePostButton1.setVisibility(View.VISIBLE);
                        EditPostButon1.setVisibility(View.VISIBLE);


                    }
                    EditPostButon1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            EditCurrentPost1(description2);
                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        DeletePostButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteCurrentPost1();
            }
        });

    }
    private void EditCurrentPost1(String description2) {
        AlertDialog.Builder builder=new AlertDialog.Builder(MuhendislikSorClickPostActivity.this);
        builder.setTitle("DÜZENLE");
        final EditText InputField=new EditText(MuhendislikSorClickPostActivity.this);
        InputField.setText(description2);
        builder.setView(InputField);
        builder.setPositiveButton("Güncelle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ClickPostRef1.child("description2").setValue(InputField.getText().toString());
                Toast.makeText(MuhendislikSorClickPostActivity.this,"Post başarıyla güncellendi",Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("İptal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();

            }
        });
        Dialog dialog=builder.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.holo_green_dark);
    }
    private void DeleteCurrentPost1() {
        ClickPostRef1.removeValue();
        SendUserToMain2Activity();
        Toast.makeText(this,"Post silindi",Toast.LENGTH_SHORT).show();
    }

    private void SendUserToMain2Activity() {
        Intent mainIntent2 = new Intent(MuhendislikSorClickPostActivity.this,MuhendislikSorActivity.class);
        mainIntent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainIntent2);

    }
}
