package com.sicromoft.hackboapp.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sicromoft.hackboapp.Adapters.TagAdapter;
import com.sicromoft.hackboapp.R;

import java.util.ArrayList;

public class EditProfileActivity extends AppCompatActivity {

    private EditText skill;
    private Button addSkill;
    private TagAdapter adapter;
    private RecyclerView recyclerView;
    private ArrayList<String> tags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        skill = findViewById(R.id.skill);
        addSkill = findViewById(R.id.addSkillButton);
    }
}
