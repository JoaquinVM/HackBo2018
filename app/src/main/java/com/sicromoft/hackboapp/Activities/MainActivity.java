package com.sicromoft.hackboapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.sicromoft.hackboapp.R;

public class MainActivity extends BaseActivity {

    private FloatingActionButton floatingButton;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    FirebaseAuth.getInstance().signOut();
                    return true;
                case R.id.navigation_profile:
                    Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_favorites:
                    intent = new Intent(MainActivity.this, FavoritesActivity.class);
                    startActivity(intent);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        floatingButton = findViewById(R.id.fab);
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
}
