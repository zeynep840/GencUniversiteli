package com.example.university;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class TipSosyalAktivitelerPostsActivity extends AppCompatActivity {
    private StorageTask uploadTask;

    private ImageButton backButton1;
    private ImageView SelecPostImage1;
    private Button UpdatePostButton1;
    private EditText PostDescription1;
    private Uri ImageUri;
    private String Description,saveCurrentTime,saveCurrentDate,postRandom,current_user_id;
    private String myUrl;
    private StorageReference PostsImageReference;

    private DatabaseReference usersReference,postsRef;
    private FirebaseAuth mAuth;
    private ProgressDialog loadingBar;
    private static final int Gallery_Pick=1;
    public void init(){
        backButton1=(ImageButton) findViewById(R.id.geri1);

        UpdatePostButton1=(Button) findViewById(R.id.post_button2);
        PostDescription1=(EditText) findViewById(R.id.post_editText1);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_sosyal_aktiviteler_posts);
        init();
        mAuth=FirebaseAuth.getInstance();
        current_user_id=mAuth.getCurrentUser().getUid();


        usersReference= FirebaseDatabase.getInstance().getReference().child("Users2");

        loadingBar=new ProgressDialog(this);

        //güncelleme butonuna basılınca
        UpdatePostButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidatePost1Info();
            }
        });
        backButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent geriIntent=new Intent( TipSosyalAktivitelerPostsActivity.this,TipSosyalAktivitelerActivity.class);
                startActivity(geriIntent);
                finish();
            }
        });



    }
    private String getFileExtension(Uri uri){
        ContentResolver contentResolver=getContentResolver();
        MimeTypeMap mime=MimeTypeMap.getSingleton();
        return  mime.getExtensionFromMimeType(contentResolver.getType(uri));
    }
    //güncelleme butonuna basılınca görüntüyü depolamaya kaydetmek için işlemler yapar
    private void ValidatePost1Info() {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Posting");
        progressDialog.show();

        postsRef=FirebaseDatabase.getInstance().getReference().child("TipSosyalAktiviteler");
        String postid=postsRef.push().getKey();
        Calendar calFordDate=Calendar.getInstance();
        SimpleDateFormat currentDate=new SimpleDateFormat("dd-MMM-yyyy");
        saveCurrentDate=currentDate.format(calFordDate.getTime());
        SimpleDateFormat currentTime=new SimpleDateFormat("HH:mm:ss");

        saveCurrentTime=currentTime.format(calFordDate.getTime());
        postRandom=saveCurrentDate+saveCurrentTime;
        //users den alınması için kullanılır...
        usersReference.child(current_user_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String userFullname=dataSnapshot.child("username").getValue().toString();

                HashMap<String,Object> hashMap=new HashMap<>();
                hashMap.put("postid",current_user_id);
                hashMap.put("date",saveCurrentDate);
                hashMap.put("time",saveCurrentTime);


                hashMap.put("username",userFullname);
                hashMap.put("description",PostDescription1.getText().toString());
                hashMap.put("publisher", mAuth.getCurrentUser().getUid());
                postsRef.child(current_user_id+postRandom).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            startActivity(new Intent(TipSosyalAktivitelerPostsActivity.this,TipSosyalAktivitelerActivity.class));
                            finish();

                        }
                        else{
                            Toast.makeText(TipSosyalAktivitelerPostsActivity.this,"Eror",Toast.LENGTH_SHORT).show();

                        }

                    }
                });




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }


}
