package com.sicromoft.hackboapp.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.sicromoft.hackboapp.R;

public class FavoritesActivity extends BaseActivity {

    @Override
    int getContentViewId() {
        return R.layout.activity_favorites;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.navigation_favorites;
    }
}
