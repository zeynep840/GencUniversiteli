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

public class MimarlikActivity extends AppCompatActivity {
    private Toolbar mToolbar4;
    private ImageButton camera4;
    private DatabaseReference PostsRef4,SetupRef4;
    private RecyclerView recyclerView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mimarlik);
        PostsRef4= FirebaseDatabase.getInstance().getReference().child("MimarlikSor");

        recyclerView4=(RecyclerView)findViewById(R.id.recyclerview4);
        recyclerView4.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setReverseLayout(true);
        recyclerView4.setLayoutManager(linearLayoutManager);



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Mimarlik Bölümü");
        camera4=(ImageButton)findViewById(R.id.camera4);
        camera4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4=new Intent(MimarlikActivity.this,MimarlikSorPostsActivity.class);
                startActivity(intent4);
                finish();
            }
        });
        DisplayAllUserPosts4();
    }

    private void DisplayAllUserPosts4() {
        final FirebaseRecyclerAdapter<MimarlikSorPosts,MimarlikSorPostsViewHolder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<MimarlikSorPosts, MimarlikSorPostsViewHolder>(
                MimarlikSorPosts.class,
                R.layout.all_mimarlik_sor_post_layout,
                MimarlikSorPostsViewHolder.class,
                PostsRef4
        ) {
            @Override
            protected void populateViewHolder(MimarlikSorPostsViewHolder viewHolder, MimarlikSorPosts model, int position) {
                final String PostKey4=getRef(position).getKey();

                viewHolder.setDescription3(model.getDescription3());
                viewHolder.setUsername(model.getUsername());


                viewHolder.setDate3(model.getDate3());
                viewHolder.setTime3(model.getTime3());
                viewHolder.CommentPostButton2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent commentPostIntent1=new Intent(MimarlikActivity.this,MimarlikSorCommentActivity.class);
                        commentPostIntent1.putExtra("PostKey4",PostKey4);
                        startActivity(commentPostIntent1);


                    }
                });
                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent clickPostIntent=new Intent(MimarlikActivity.this,MimarlikSorClickPostActivity.class);
                        clickPostIntent.putExtra("PostKey4",PostKey4);
                        startActivity(clickPostIntent);

                    }
                });



            }
        };
        recyclerView4.setAdapter(firebaseRecyclerAdapter);

    }
    public static class MimarlikSorPostsViewHolder extends RecyclerView.ViewHolder {
        View mView;
        Button CommentPostButton2;


        public MimarlikSorPostsViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            CommentPostButton2=(Button) mView.findViewById(R.id.yorum3);

        }
//buraya setler yazılacak


        public void setDate3(String date3) {
            TextView datem3 = (TextView) mView.findViewById(R.id.date3);
            datem3.setText(" " + date3);

        }

        public void setTime3(String time3) {
            TextView timem3 = (TextView) mView.findViewById(R.id.time3);
            timem3.setText(" " + time3);

        }

        public void setUsername(String username) {
            TextView usernamem3 = (TextView) mView.findViewById(R.id.post_username3);
            usernamem3.setText(" " + username);
        }

        public void setDescription3(String description3) {
            TextView descreption3 = (TextView) mView.findViewById(R.id.descreption3);
            descreption3.setText(" " + description3);
        }




    }
}

