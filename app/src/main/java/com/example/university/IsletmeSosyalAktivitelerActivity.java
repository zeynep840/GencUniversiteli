package com.example.university;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class IsletmeSosyalAktivitelerActivity extends AppCompatActivity {
    private Toolbar mToolbar5;
    private ImageButton camera5;
    private DatabaseReference PostsRef5,SetupRef5;
    private RecyclerView recyclerView5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isletme_sosyal_aktiviteler);
        PostsRef5= FirebaseDatabase.getInstance().getReference().child("IsletmeSosyalAktiviteler");
        camera5=(ImageButton)findViewById(R.id.camera5);
        recyclerView5=(RecyclerView)findViewById(R.id.recyclerview5);
        recyclerView5.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setReverseLayout(true);
        recyclerView5.setLayoutManager(linearLayoutManager);



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("IsletmeSosyalAktiviteler");
        camera5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent post5=new Intent(IsletmeSosyalAktivitelerActivity.this,IsletmeSosyalAktivitelerPostsActivity.class);
                startActivity(post5);
                finish();
            }
        });
        DisplayAllUserPosts5();
    }

    private void DisplayAllUserPosts5() {
        final FirebaseRecyclerAdapter<IsletmeSosyalAktivitelerPosts, IsletmeSosyalAktivitelerPostsViewHolder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<IsletmeSosyalAktivitelerPosts, IsletmeSosyalAktivitelerPostsViewHolder>(
                IsletmeSosyalAktivitelerPosts.class,
                R.layout.all_isletme_sosyal_aktiviteler_post_layout,
                IsletmeSosyalAktivitelerPostsViewHolder.class,
                PostsRef5
        ) {
            @Override
            protected void populateViewHolder(IsletmeSosyalAktivitelerPostsViewHolder viewHolder, IsletmeSosyalAktivitelerPosts model, int position) {
                final String PostKey2=getRef(position).getKey();

                viewHolder.setDescription(model.getDescription());
                viewHolder.setUsername(model.getUsername());


                viewHolder.setDate(model.getDate());
                viewHolder.setTime(model.getTime());
                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent clickPostIntent=new Intent(IsletmeSosyalAktivitelerActivity.this,IsletmeSosyalAktivitelerClickPostActivity.class);
                        clickPostIntent.putExtra("PostKey2",PostKey2);
                        startActivity(clickPostIntent);

                    }
                });
                viewHolder.CommentPostButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent commentPostIntent=new Intent(IsletmeSosyalAktivitelerActivity.this,IsletmeSosyalAktivitelerCommentActivity.class);
                        commentPostIntent.putExtra("PostKey2",PostKey2);
                        startActivity(commentPostIntent);


                    }
                });




            }
        };
//post sayfasında gözükmeyi sağlar
        recyclerView5.setAdapter(firebaseRecyclerAdapter);

    }
    public static class IsletmeSosyalAktivitelerPostsViewHolder extends RecyclerView.ViewHolder {
        View mView;
        Button CommentPostButton;


        public IsletmeSosyalAktivitelerPostsViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            CommentPostButton=(Button) mView.findViewById(R.id.yorum1);

        }
//buraya setler yazılacak


        public void setDate(String date) {
            TextView datem = (TextView) mView.findViewById(R.id.date);
            datem.setText(" " + date);

        }

        public void setTime(String time) {
            TextView timem = (TextView) mView.findViewById(R.id.time);
            timem.setText(" " + time);

        }

        public void setUsername(String username) {
            TextView usernamem = (TextView) mView.findViewById(R.id.post_username);
            usernamem.setText(" " + username);
        }

        public void setDescription(String description) {
            TextView descreption = (TextView) mView.findViewById(R.id.descreption);
            descreption.setText(" " + description);
        }



    }
}
