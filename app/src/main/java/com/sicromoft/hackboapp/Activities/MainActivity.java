package com.sicromoft.hackboapp.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sicromoft.hackboapp.Adapters.CreateProjectTagAdapter;
import com.sicromoft.hackboapp.Adapters.ProjectAdapter;
import com.sicromoft.hackboapp.Beans.Project;
import com.sicromoft.hackboapp.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private ImageView floatingButton;
    private final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private ProjectAdapter adapter;
    private List<Project> projects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        floatingButton = findViewById(R.id.crear_proyecto);


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProjectAdapter(this);

        floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateProjectActivity.class);
                startActivity(intent);
            }
        });

        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            startLogInActivity();
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Projects");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Project> projects = new ArrayList<>();

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String name = snapshot.child("name").getValue(String.class);
                    String overview = snapshot.child("overview").getValue(String.class);
                    String description = snapshot.child("description").getValue(String.class);
                    ArrayList<String> tags = new ArrayList<>();
                    for(DataSnapshot child : snapshot.child("tags").getChildren()){
                        tags.add(child.getValue(String.class));
                    }
                    projects.add(new Project(name, overview, description, tags));
                }

                updateRecycler(projects);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "Failed to read value.", databaseError.toException());
            }
        });

    }

    @Override
    int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.navigation_home;
    }


    private void startLogInActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("signed_recently", false);
        startActivity(intent);
    }

    private void updateRecycler(List<Project> projects){
        adapter.setListContent(projects);
        recyclerView.setAdapter(adapter);
    }
}
