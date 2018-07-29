package com.sicromoft.hackboapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sicromoft.hackboapp.R;

public class ProfileActivity extends BaseActivity{

    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private FirebaseUser user = auth.getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView name;
        name = findViewById(R.id.profile_name);
        name.setText(user.getDisplayName());

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
