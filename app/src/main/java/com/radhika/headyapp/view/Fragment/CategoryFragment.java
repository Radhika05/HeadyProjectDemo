package com.radhika.headyapp.view.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.radhika.headyapp.databinding.FragmentCategoryBinding;
import com.radhika.headyapp.model.Categories;
import com.radhika.headyapp.view.adapter.CategoryAdapter;
import com.radhika.headyapp.viewmodel.ProductViewModel;

import java.util.List;


public class CategoryFragment extends Fragment {

    private ProductViewModel productViewModel;
    private FragmentCategoryBinding binding;

    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentCategoryBinding binding = FragmentCategoryBinding.inflate(getLayoutInflater(), container, false);
        View view = binding.getRoot();
        productViewModel= ViewModelProviders.of(this).get(ProductViewModel.class);
        productViewModel.getHeroes(getContext()).observe(this, new Observer<List<Categories>>() {
            @Override
            public void onChanged(List<Categories> categories) {

            }
        });

        getCategoryList();
        return view;


    }

    private void getCategoryList() {
         LiveData<List<Categories>> categoryList =  productViewModel.getHeroes(getContext());
         if(categoryList!=null){

             CategoryAdapter categoryAdapter = new CategoryAdapter(this, categoryList);
             RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
             binding.dashboardRecycler.setLayoutManager(mLayoutManager);
             binding.dashboardRecycler.setItemAnimator(new DefaultItemAnimator());
             binding.dashboardRecycler.setAdapter(categoryAdapter);
            // categoryAdapter.setOnItemClickListener(onItemClickListener);
         }
    }


}
