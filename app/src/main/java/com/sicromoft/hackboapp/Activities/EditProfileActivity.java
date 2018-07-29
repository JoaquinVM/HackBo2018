package com.sicromoft.hackboapp.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sicromoft.hackboapp.Adapters.DeletableTagAdapter;
import com.sicromoft.hackboapp.Adapters.TagAdapter;
import com.sicromoft.hackboapp.Beans.Deletable;
import com.sicromoft.hackboapp.R;

import java.util.ArrayList;

public class EditProfileActivity extends AppCompatActivity implements Deletable{

    private EditText skill;
    private Button addSkill, edit;
    private TagAdapter adapter;
    private RecyclerView recyclerView;
    private ArrayList<String> skills;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        skill = findViewById(R.id.skill);
        addSkill = findViewById(R.id.addSkillButton);

        edit = findViewById(R.id.editButton);

        recyclerView = findViewById(R.id.recyclerView1);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false));
        adapter = new TagAdapter(this);

        skills = new ArrayList<>();

        addSkill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!skills.contains(skill.getText().toString().toUpperCase())) {
                    skills.add(skill.getText().toString().toUpperCase());
                    skill.setText("");
                    updateRecycler();
                }
            }
        });

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Users");

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.child(FirebaseAuth.getInstance().getUid()).child("skills").setValue(skills);
                onBackPressed();
            }
        });
    }

    public void updateRecycler(){
        adapter.setListContent(skills);
        recyclerView.setAdapter(adapter);
    }

    public void deleteFromList(String name){
        skills.remove(name);
        updateRecycler();
    }
}
