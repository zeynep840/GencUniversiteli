package com.example.university;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class MuhendislikSorActivity extends AppCompatActivity {
    private Toolbar mToolbar1;
    private ImageButton camera23;
    private DatabaseReference PostsRef1,SetupRef;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muhendislik_sor);



        PostsRef1=FirebaseDatabase.getInstance().getReference().child("MuhendislikSor");
        camera23=(ImageButton)findViewById(R.id.camera23);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(linearLayoutManager);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Mühendislik Bölümü");
        camera23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent post2=new Intent(MuhendislikSorActivity.this,MuhendislikSorPostsActivity.class);
                startActivity(post2);
                finish();
            }
        });
        DisplayAllUserPosts();

    }

    private void DisplayAllUserPosts() {
        final FirebaseRecyclerAdapter<MuhendislikSorPosts,MuhendislikSorPostsViewHolder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<MuhendislikSorPosts,MuhendislikSorPostsViewHolder>
                (
                        MuhendislikSorPosts.class,
                        R.layout.all_muhendislik_sor_post_layout,
                        MuhendislikSorPostsViewHolder.class,
                        PostsRef1
                ) {
            @Override
            protected void populateViewHolder(MuhendislikSorPostsViewHolder viewHolder, MuhendislikSorPosts model, int position) {
                final String PostKey3 =getRef(position).getKey();

                viewHolder.setDescription2(model.getDescription2());
                viewHolder.setUsername(model.getUsername());

                viewHolder.setDate2(model.getDate2());
                viewHolder.setTime2(model.getTime2());
                viewHolder.CommentPostButton1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent commentPostIntent1=new Intent(MuhendislikSorActivity.this,MuhendislikSorCommentActivity.class);
                        commentPostIntent1.putExtra("PostKey3",PostKey3);
                        startActivity(commentPostIntent1);


                    }
                });
                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent clickPostIntent=new Intent(MuhendislikSorActivity.this,MuhendislikSorClickPostActivity.class);
                        clickPostIntent.putExtra("PostKey3",PostKey3);
                        startActivity(clickPostIntent);

                    }
                });


            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }


    public static class MuhendislikSorPostsViewHolder extends RecyclerView.ViewHolder {
        View mView;
        Button CommentPostButton1;


        public MuhendislikSorPostsViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            CommentPostButton1=(Button) mView.findViewById(R.id.yorum2);

        }
//buraya setler yazılacak


        public void setDate2(String date2) {
            TextView datem2 = (TextView) mView.findViewById(R.id.date2);
            datem2.setText(" " + date2);

        }

        public void setTime2(String time2) {
            TextView timem2 = (TextView) mView.findViewById(R.id.time2);
            timem2.setText(" " + time2);

        }

        public void setUsername(String username) {
            TextView usernamem2 = (TextView) mView.findViewById(R.id.post_username2);
            usernamem2.setText(" " + username);
        }

        public void setDescription2(String description2) {
            TextView descreption2 = (TextView) mView.findViewById(R.id.descreption2);
            descreption2.setText(" " + description2);
        }




    }
}


