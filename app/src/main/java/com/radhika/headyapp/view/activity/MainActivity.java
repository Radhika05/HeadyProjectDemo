package com.radhika.headyapp.view.activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.radhika.headyapp.R;
import com.radhika.headyapp.databinding.ActivityMainBinding;
import com.radhika.headyapp.model.MainPojo;
import com.radhika.headyapp.utils.FragmentsManager;
import com.radhika.headyapp.view.Fragment.CategoryFragment;
import com.radhika.headyapp.view.Fragment.SearchFragment;
import com.radhika.headyapp.viewmodel.ProductViewModel;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private ActivityMainBinding binding;
    private ProductViewModel productViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.toolTxt.setText(getString(R.string.label_menu_home));
        CategoryFragment categoryFragment = new CategoryFragment();
        FragmentsManager.replaceFragment(this, categoryFragment, R.id.frame_one, false);
        binding.navigation.setOnNavigationItemSelectedListener(this);
        productViewModel= ViewModelProviders.of(this).get(ProductViewModel.class);
        productViewModel.getProducts(this).observe(this, new Observer<MainPojo>() {
            @Override
            public void onChanged(MainPojo categories) {
                if (categories != null) {
                    productViewModel.inserData(getApplicationContext(), categories);
                }
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.home:
                binding.toolTxt.setText(getString(R.string.label_menu_home));
                CategoryFragment categoryFragment = new CategoryFragment();
                FragmentsManager.replaceFragment(this, categoryFragment, R.id.frame_one, false);
                break;
            case R.id.search:
                binding.toolTxt.setText(getString(R.string.label_menu_search));
                SearchFragment searchFragment = new SearchFragment();
                FragmentsManager.replaceFragment(this, searchFragment, R.id.frame_one, false);
                break;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStackImmediate();
        } else {
            super.onBackPressed();
        }
    }
}
