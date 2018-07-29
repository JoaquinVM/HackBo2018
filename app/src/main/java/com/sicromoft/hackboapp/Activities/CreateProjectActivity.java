package com.sicromoft.hackboapp.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sicromoft.hackboapp.Adapters.CreateProjectTagAdapter;
import com.sicromoft.hackboapp.Beans.Project;
import com.sicromoft.hackboapp.R;

import java.util.ArrayList;

public class CreateProjectActivity extends AppCompatActivity {

    private EditText name, description, tag, overview;
    private Button addTag, createProject;
    private CreateProjectTagAdapter adapter;
    private RecyclerView recyclerView;
    private ArrayList<String> tags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_project);

        name = findViewById(R.id.name);
        overview = findViewById(R.id.overview);
        description = findViewById(R.id.description);
        tag = findViewById(R.id.tag);
        addTag = findViewById(R.id.addTagButton);
        createProject = findViewById(R.id.createProject);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false));
        adapter = new CreateProjectTagAdapter(this, this);

        tags = new ArrayList<>();

        addTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!tags.contains(tag.getText().toString().toUpperCase())) {
                    tags.add(tag.getText().toString().toUpperCase());
                    updateRecycler();
                }
            }
        });

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Projects");

        createProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Project p = new Project(name.getText().toString(), overview.getText().toString(),
                        description.getText().toString(), tags);
                myRef.push().setValue(p);
                onBackPressed();
            }
        });

    }

    public void updateRecycler(){
        adapter.setListContent(tags);
        recyclerView.setAdapter(adapter);
    }

    public void deleteFromList(String name){
        tags.remove(name);
        updateRecycler();
    }

}

