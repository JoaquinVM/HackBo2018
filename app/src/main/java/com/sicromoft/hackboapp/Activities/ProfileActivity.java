package com.sicromoft.hackboapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sicromoft.hackboapp.Adapters.DeletableTagAdapter;
import com.sicromoft.hackboapp.R;

import java.util.ArrayList;

public class ProfileActivity extends BaseActivity{

    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private FirebaseUser user = auth.getCurrentUser();

    private EditText name, description, tag;
    private Button addTag;
    private ImageView addSkills;
    private DeletableTagAdapter adapter;
    private RecyclerView recyclerView;
    private ArrayList<String> tags;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView name, email;
        name = findViewById(R.id.profile_name);
        email = findViewById(R.id.email);
        name.setText(user.getDisplayName());
        email.setText(user.getEmail());

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false));
        adapter = new DeletableTagAdapter(this, this);

        tags = new ArrayList<>();

        addSkills = findViewById(R.id.edit_profile);
        addSkills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
                startActivity(intent);
            }
        });
        Button logout = findViewById(R.id.logoutButton);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    int getContentViewId() {
        return R.layout.activity_profile;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.navigation_profile;
    }



}
