package com.radhika.headyapp.view.Fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.radhika.headyapp.R;
import com.radhika.headyapp.model.Categories;
import com.radhika.headyapp.model.MainPojo;
import com.radhika.headyapp.view.adapter.CategoryAdapter;
import com.radhika.headyapp.viewmodel.ProductViewModel;

import java.util.List;


public class CategoryFragment extends Fragment {

    private ProductViewModel productViewModel;
    private RecyclerView recyclerView;
    ShimmerFrameLayout shimmerFrameLayout;


    public CategoryFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);// Inflate the layout for this fragment
        recyclerView=view.findViewById(R.id.dashboard_recycler);
        shimmerFrameLayout = view.findViewById(R.id.shimmer);

        getCategoryList();

        productViewModel= ViewModelProviders.of(this).get(ProductViewModel.class);
        productViewModel.getHeroes(getContext()).observe(this, new Observer<MainPojo>() {
            @Override
            public void onChanged(MainPojo categories) {
                Log.d("onChanged",categories.toString());

            }
        });

        productViewModel.getCategory(getContext()).observe(this, new Observer<List<Categories>>() {
            @Override
            public void onChanged(List<Categories> categories) {
                if(categories!=null){
                    recyclerView.setVisibility(View.VISIBLE);
                    CategoryAdapter categoryAdapter = new CategoryAdapter(getActivity(), categories);
                    recyclerView.setAdapter(categoryAdapter);
                }
            }
        });



        return view;


    }

    @Override
    public void onResume() {
        super.onResume();
        shimmerFrameLayout.startShimmer();
    }

    @Override
    public void onPause() {
        super.onPause();
        shimmerFrameLayout.stopShimmer();
    }

    private void getCategoryList() {

        shimmerFrameLayout.stopShimmer();
        shimmerFrameLayout.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
             RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
             recyclerView.setLayoutManager(mLayoutManager);
             recyclerView.setItemAnimator(new DefaultItemAnimator());
         }
    }



