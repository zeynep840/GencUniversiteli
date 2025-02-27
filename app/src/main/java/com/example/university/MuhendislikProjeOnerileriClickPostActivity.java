package com.example.university;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MuhendislikProjeOnerileriClickPostActivity extends AppCompatActivity {
    private ImageView PostImage;
    private TextView PostDesription;
    private Button DeletePostButton,EditPostButon;
    private String PostKey;
    private DatabaseReference ClickPostRef;
    private FirebaseAuth mAuth;
    private String currentUserID,databaseUserID,description,image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muhendislik_proje_onerileri_click_post);
        PostDesription=(TextView)findViewById(R.id.yorum_yapmak);
        DeletePostButton=(Button)findViewById(R.id.postsil);
        EditPostButon=(Button)findViewById(R.id.postkaydet);
        PostKey=getIntent().getExtras().get("PostKey2").toString();
        ClickPostRef= FirebaseDatabase.getInstance().getReference().child("MuhendislikProjeOnerileri").child(PostKey);
        mAuth=FirebaseAuth.getInstance();
        currentUserID=mAuth.getCurrentUser().getUid();
        DeletePostButton.setVisibility(View.INVISIBLE);
        EditPostButon.setVisibility(View.INVISIBLE);



        ClickPostRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    description = dataSnapshot.child("description").getValue().toString();

                    databaseUserID = dataSnapshot.child("postid").getValue().toString();
                    PostDesription.setText(description);

                    if (currentUserID.equals(databaseUserID)) {
                        DeletePostButton.setVisibility(View.VISIBLE);
                        EditPostButon.setVisibility(View.VISIBLE);


                    }
                    EditPostButon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            EditCurrentPost(description);
                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        DeletePostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteCurrentPost();
            }
        });

    }
    //postu güncellemek için
    private void EditCurrentPost(String description) {
        AlertDialog.Builder builder=new AlertDialog.Builder(MuhendislikProjeOnerileriClickPostActivity.this);
        builder.setTitle("DÜZENLE");
        final EditText InputField=new EditText(MuhendislikProjeOnerileriClickPostActivity.this);
        InputField.setText(description);
        builder.setView(InputField);
        builder.setPositiveButton("Güncelle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ClickPostRef.child("description").setValue(InputField.getText().toString());
                Toast.makeText(MuhendislikProjeOnerileriClickPostActivity.this,"Post başarıyla güncellendi",Toast.LENGTH_SHORT).show();
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

    //postu silmek için
    private void DeleteCurrentPost() {
        ClickPostRef.removeValue();
        SendUserToMainActivity();
        Toast.makeText(this,"Post silindi",Toast.LENGTH_SHORT).show();
    }

    private void SendUserToMainActivity() {
        Intent mainIntent = new Intent(MuhendislikProjeOnerileriClickPostActivity.this,MuhendislikProjeOnerileriActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainIntent);


    }
}

