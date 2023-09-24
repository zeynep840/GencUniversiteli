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

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class MuhendislikSorPostsActivity extends AppCompatActivity {
    private StorageTask uploadTask2;

    private ImageButton backButton2;

    private Button UpdatePostButton2;
    private EditText PostDescription2;
    private Uri ImageUri2;
    private String Description2, saveCurrentTime2, saveCurrentDate2, postRandom2, current_user_id2;
    private String myUrl;
    private StorageReference PostsImageReference2;

    private DatabaseReference usersReference, postsRef1;
    private FirebaseAuth mAuth;
    private ProgressDialog loadingBar;
    private static final int Gallery_Pick = 1;

    public void init() {
        backButton2 = (ImageButton) findViewById(R.id.geri2);

        UpdatePostButton2 = (Button) findViewById(R.id.post_button3);
        PostDescription2 = (EditText) findViewById(R.id.post_editText2);

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muhendislik_sor_posts);
        init();
        mAuth = FirebaseAuth.getInstance();
        current_user_id2 = mAuth.getCurrentUser().getUid();

        usersReference = FirebaseDatabase.getInstance().getReference().child("Users2");

        loadingBar = new ProgressDialog(this);


        UpdatePostButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidePost2Info();
            }
        });
        backButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent geriIntent = new Intent(MuhendislikSorPostsActivity.this, MuhendislikSorActivity.class);
                startActivity(geriIntent);
                finish();
            }
        });


    }

    private String getFileExtension(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void ValidePost2Info() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Posting");
        progressDialog.show();



        postsRef1 = FirebaseDatabase.getInstance().getReference().child("MuhendislikSor");
        String postid = postsRef1.push().getKey();
        Calendar calFordDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MMM-yyyy");
        saveCurrentDate2 = currentDate.format(calFordDate.getTime());
        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss");

        saveCurrentTime2 = currentTime.format(calFordDate.getTime());
        postRandom2 = saveCurrentDate2 + saveCurrentTime2;
        //users den alınması için kullanılır...
        usersReference.child(current_user_id2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String userFullname = dataSnapshot.child("username").getValue().toString();

                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("postid2", current_user_id2);
                hashMap.put("date2", saveCurrentDate2);
                hashMap.put("time2", saveCurrentTime2);
                hashMap.put("username", userFullname);
                hashMap.put("description2", PostDescription2.getText().toString());
                hashMap.put("publisher2", mAuth.getCurrentUser().getUid());
                postsRef1.child(current_user_id2 + postRandom2).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            startActivity(new Intent(MuhendislikSorPostsActivity.this, MuhendislikSorActivity.class));
                            finish();
                        }
                        else{
                            Toast.makeText(MuhendislikSorPostsActivity.this,"Eror",Toast.LENGTH_SHORT).show();
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