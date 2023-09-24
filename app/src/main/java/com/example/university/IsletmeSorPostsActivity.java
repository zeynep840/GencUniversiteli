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

public class IsletmeSorPostsActivity extends AppCompatActivity {
    private StorageTask uploadTask3;
    private ImageButton backButton3;
    private ImageView SelecPostImage3;
    private Button UpdatePostButton3;
    private EditText PostDescription3;
    private String Description3;
    private Uri ImageUri3;
    private StorageReference PostsImageReference3;
    private String saveCurrentDate3,saveCurrentTime3,postRandomName3,current_user_id3,postRandom3;
    private static final int Gallery_Pick=1;
    private DatabaseReference usersReference3, postsRef3;
    private FirebaseAuth mAuth3;
    private ProgressDialog loadingBar;
    private String myUrl4;
    public void init(){
        backButton3=(ImageButton) findViewById(R.id.geri3);

        UpdatePostButton3=(Button) findViewById(R.id.post_button4);
        PostDescription3=(EditText) findViewById(R.id.post_editText3);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isletme_sor_posts);
        init();


        mAuth3 = FirebaseAuth.getInstance();
        current_user_id3 = mAuth3.getCurrentUser().getUid();

        usersReference3 = FirebaseDatabase.getInstance().getReference().child("Users2");

        loadingBar = new ProgressDialog(this);


        UpdatePostButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidePost3Info();
            }
        });
        backButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent geriIntent=new Intent(IsletmeSorPostsActivity.this,IsletmeSorActivity.class);
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


    private void ValidePost3Info() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Posting");
        progressDialog.show();

        postsRef3 = FirebaseDatabase.getInstance().getReference().child("IsletmeSor");
        String postid = postsRef3.push().getKey();
        Calendar calFordDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MMM-yyyy");
        saveCurrentDate3 = currentDate.format(calFordDate.getTime());
        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss");

        saveCurrentTime3 = currentTime.format(calFordDate.getTime());
        postRandom3 = saveCurrentDate3 + saveCurrentTime3;
        //users den alınması için kullanılır...
        usersReference3.child(current_user_id3).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String userFullname = dataSnapshot.child("username").getValue().toString();

                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("postid3", current_user_id3);
                hashMap.put("date3", saveCurrentDate3);
                hashMap.put("time3", saveCurrentTime3);


                hashMap.put("username", userFullname);
                hashMap.put("description3", PostDescription3.getText().toString());
                hashMap.put("publisher3", mAuth3.getCurrentUser().getUid());
                postsRef3.child(current_user_id3 + postRandom3).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            startActivity(new Intent(IsletmeSorPostsActivity.this, IsletmeSorActivity.class));
                            finish();
                        }
                        else{
                            Toast.makeText(IsletmeSorPostsActivity.this,"Eror",Toast.LENGTH_SHORT).show();

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

