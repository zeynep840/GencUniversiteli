package com.example.university;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class MimarlikSorCommentActivity extends AppCompatActivity {
    private EditText edt_yorum_ekle2;
    private ImageView profil_resmi2, gonder2;
    private String Post_Key4, saveCurrentDate2, saveCurrentTime2, postRandom2,current_user_id2;
    private DatabaseReference postsRef2,UsersReference2;
    private RecyclerView CommentsList2;
    private FirebaseAuth mAuth2;

    private String uid2, postid3;
    private FirebaseUser firebaseUser2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mimarlik_sor_comment);
        Post_Key4 = getIntent().getExtras().get("PostKey4").toString();
        postsRef2 = FirebaseDatabase.getInstance().getReference().child("MimarlikSor").child(Post_Key4).child("MimarlikSorYorum");
        mAuth2=FirebaseAuth.getInstance();
        current_user_id2=mAuth2.getCurrentUser().getUid();
        UsersReference2=FirebaseDatabase.getInstance().getReference().child("Users2");



        firebaseUser2 = FirebaseAuth.getInstance().getCurrentUser();
        edt_yorum_ekle2 = (EditText) findViewById(R.id.edt_yorumEkle_yorumlarActivity2);

        gonder2 = (ImageView) findViewById(R.id.post_comment_btn2);
        Intent intent = getIntent();
        postid3= intent.getStringExtra(postid3);
        uid2 = intent.getStringExtra(uid2);
        CommentsList2=(RecyclerView)findViewById(R.id.recycler_view_yorumlarActivity2);
        CommentsList2.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        CommentsList2.setLayoutManager(linearLayoutManager);
        gonder2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //usersreference komutları eklenecek
                UsersReference2.child(current_user_id2).addValueEventListener(new ValueEventListener() {
                    @Override

                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            String comments = edt_yorum_ekle2.getText().toString();
                            if (TextUtils.isEmpty(comments)) {
                                Toast.makeText(MimarlikSorCommentActivity.this, "Boş Yorum Gönderemezsin...", Toast.LENGTH_SHORT).show();
                            } else {
                                String userFullname = dataSnapshot.child("username").getValue().toString();
                                String postid = postsRef2.push().getKey();
                                Calendar calFordDate = Calendar.getInstance();
                                SimpleDateFormat currentDate = new SimpleDateFormat("dd-MMM-yyyy");
                                saveCurrentDate2 = currentDate.format(calFordDate.getTime());
                                Calendar calFordTime=Calendar.getInstance();
                                SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss");

                                saveCurrentTime2 = currentTime.format(calFordTime.getTime());
                                postRandom2 = saveCurrentDate2 + saveCurrentTime2;

                                DatabaseReference yorumlarYolu = FirebaseDatabase.getInstance().getReference("Yorumlar").child(postid);
                                HashMap<String, Object> commentsMap = new HashMap<>();
                                commentsMap .put("yorum2", comments);
                                commentsMap .put("date2", saveCurrentDate2);
                                commentsMap .put("time2", saveCurrentTime2);
                                commentsMap.put("username", userFullname);
                                edt_yorum_ekle2.setText("");


                                postsRef2.child(postRandom2).updateChildren(commentsMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(MimarlikSorCommentActivity.this, "Başarılı bir şekilde yüklendi", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(MimarlikSorCommentActivity.this, "Eror", Toast.LENGTH_SHORT).show();

                                        }


                                    }
                                });
                            }

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });





            }
        });

    }


    protected void onStart() {

        super.onStart();
        FirebaseRecyclerAdapter<MimarlikSorComment,MimarlikSorCommentViewHolder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<MimarlikSorComment, MimarlikSorCommentViewHolder>
                (MimarlikSorComment.class,
                        R.layout.all_mimarlik_sor_comment_layout,
                        MimarlikSorCommentViewHolder.class,
                        postsRef2

                )
        {
            @Override
            protected void populateViewHolder(MimarlikSorCommentViewHolder viewHolder, MimarlikSorComment model, int position) {
                viewHolder.setUsername(model.getUsername());
                viewHolder.setDate2(model.getDate2());
                viewHolder.setTime2(model.getTime2());
                viewHolder.setYorum2(model.getYorum2());


            }

        };
        CommentsList2.setAdapter(firebaseRecyclerAdapter);
    }

    public static class MimarlikSorCommentViewHolder extends RecyclerView.ViewHolder{
        View mView;

        public MimarlikSorCommentViewHolder(@NonNull View itemView) {
            super(itemView);
            mView=itemView;
        }
        //setler yazılacak
        public void setUsername(String username) {
            TextView myUsername2=(TextView)mView.findViewById(R.id.comment_username2);
            myUsername2.setText("@ "+username);

        }
        public void setTime2(String time2) {
            TextView myTime2=(TextView)mView.findViewById(R.id.comment_time2);
            myTime2.setText(" Saat:"+time2);

        }
        public void setDate2(String date2) {
            TextView myDate2=(TextView)mView.findViewById(R.id.comment_date2);
            myDate2.setText(" Tarih:"+date2);

        }
        public void setYorum2(String yorum2) {
            TextView myComment2=(TextView)mView.findViewById(R.id.comment_text2);
            myComment2.setText(yorum2);

        }
    }
}
