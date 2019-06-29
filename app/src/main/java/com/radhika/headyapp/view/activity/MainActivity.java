package com.radhika.headyapp.view.activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.radhika.headyapp.R;
import com.radhika.headyapp.databinding.ActivityMainBinding;
import com.radhika.headyapp.utils.FragmentsManager;
import com.radhika.headyapp.view.Fragment.CategoryFragment;
import com.radhika.headyapp.view.Fragment.SearchFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        CategoryFragment categoryFragment = new CategoryFragment();
        FragmentsManager.replaceFragment(this, categoryFragment, R.id.frame, false);
        binding.navigation.setOnNavigationItemSelectedListener(this);

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.home:
               // toolTitle.setText("HOME");
                CategoryFragment categoryFragment = new CategoryFragment();
                FragmentsManager.replaceFragment(this, categoryFragment, R.id.frame, false);
                break;
            case R.id.search:
               // toolTitle.setText("MY SERVICE");
                SearchFragment searchFragment = new SearchFragment();
                FragmentsManager.replaceFragment(this, searchFragment, R.id.frame, false);
                break;
        }
        return false;
    }


}
