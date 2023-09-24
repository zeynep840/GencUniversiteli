package com.example.university;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity {

    private Toolbar actionbar;

    private TabLayout tabsMain;

    private FirebaseAuth auth;
    private FirebaseUser currentUser;
    private ImageButton AddNewButton;
    private RecyclerView recyclerView1;
    private Button child,adult,animal;
    private Button uzman1,uzman,ogretmenie_sor;
    private ImageView imageview23;

    private DatabaseReference UsersRef;


    public void init(){


        auth=FirebaseAuth.getInstance();
        UsersRef=FirebaseDatabase.getInstance().getReference().child("Users");

        currentUser=auth.getCurrentUser();








    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        auth=FirebaseAuth.getInstance();
        UsersRef=FirebaseDatabase.getInstance().getReference().child("Users");

        currentUser=auth.getCurrentUser();
        imageview23=(ImageView)findViewById(R.id.imageView23);
        uzman1=(Button)findViewById(R.id.uzman1);
        ogretmenie_sor=(Button)findViewById(R.id.ogretmenine_sor);



        uzman=(Button)findViewById(R.id.uzman);



        uzman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent9=new Intent(HomeActivity.this,BolumuneSorActivity.class);
                startActivity(intent9);

            }
        });






    }

    @Override
    //aktif kullanıcı değilse mainactivity e gönderir
    protected void onStart() {
        if(currentUser==null){
            //anasayfadan giriş sayfasına gönderir
            Intent MainIntent=new Intent(HomeActivity.this,MainActivity.class);
            startActivity(MainIntent);
            finish();
        }
        else{
            CheckUserExistence();
        }
        super.onStart();

    }
    //kullanıcı bilgilerini kaydetmek için
    private void CheckUserExistence() {
        final String current_user_id=auth.getCurrentUser().getUid();
        UsersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!dataSnapshot.hasChild(current_user_id)){


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    @Override
    //3 nokta için kullanıldı
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    //Çıkış yapa basıldığında giriş sayfasına yönlendirelecek

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        //neye basıldığına ne yapacağına karar vermek için id le karar veriyoruz
        if(item.getItemId()==R.id.mainLogout){
            auth.signOut();
            Intent loginIntent=new Intent(HomeActivity.this,LoginActivity.class);
            startActivity(loginIntent);
            finish();
        }
        return true;








    }
}
