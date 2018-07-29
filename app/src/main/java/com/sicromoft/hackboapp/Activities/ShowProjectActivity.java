package com.sicromoft.hackboapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.sicromoft.hackboapp.Adapters.TagAdapter;
import com.sicromoft.hackboapp.Beans.Project;
import com.sicromoft.hackboapp.R;

import java.util.List;

public class ShowProjectActivity extends AppCompatActivity {

    private TextView nameView, overviewView, descriptionView;
    private RecyclerView recyclerView;
    private TagAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_project);

        Intent intent = getIntent();
        Project project = (Project) intent.getSerializableExtra("Project");
        String name = project.getName();
        String overview = project.getOverview();
        String description = project.getDescription();
        List<String> tags = project.getTags();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false));
        adapter = new TagAdapter(this);

        nameView = findViewById(R.id.name);
        overviewView = findViewById(R.id.overview);
        descriptionView = findViewById(R.id.description);

        nameView.setText(name);
        overviewView.setText(overview);
        descriptionView.setText(description);

        adapter.setListContent(tags);
        recyclerView.setAdapter(adapter);
    }
}
