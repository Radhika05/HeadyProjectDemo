package com.radhika.headyapp.view.Fragment;


import android.os.Bundle;
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
import com.radhika.headyapp.model.TempSubCat;
import com.radhika.headyapp.view.adapter.InnerSubCatAdapter;
import com.radhika.headyapp.viewmodel.ProductViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class InnerSubCatFragment extends Fragment {


    int subCategotyId;
    private RecyclerView recyclerView;
    private ShimmerFrameLayout shimmerFrameLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_inner_sub_cat, container, false);
        bindUI(view);
        getIntentData();
        setRecyclerView();
        ProductViewModel productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
        productViewModel.getSubCategoryA(getContext(),subCategotyId).observe(this, new Observer<List<TempSubCat>>() {
            @Override
            public void onChanged(List<TempSubCat> tempProducts) {
                recyclerView.setVisibility(View.VISIBLE);
                InnerSubCatAdapter innerSubCatAdapter = new InnerSubCatAdapter(tempProducts);
                recyclerView.setAdapter(innerSubCatAdapter);
            }
        });
        return view;
    }



    private void setRecyclerView(){
        shimmerFrameLayout.stopShimmer();
        shimmerFrameLayout.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }


    private void getIntentData() {
        assert getArguments() != null;
        subCategotyId = getArguments().getInt("subcategory");
    }

    private void bindUI(View view){
        recyclerView=view.findViewById(R.id.dashboard_recycler);
        shimmerFrameLayout = view.findViewById(R.id.shimmer);
    }

}
