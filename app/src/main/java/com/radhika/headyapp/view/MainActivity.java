package com.radhika.headyapp.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.radhika.headyapp.R;
import com.radhika.headyapp.model.MainPojo;
import com.radhika.headyapp.viewmodel.ProductViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ProductViewModel productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
        productViewModel.getHeroes(this).observe(this, new Observer<MainPojo>() {
            @Override
            public void onChanged(MainPojo mainPojo) {
                //
            }
        });
    }
}
