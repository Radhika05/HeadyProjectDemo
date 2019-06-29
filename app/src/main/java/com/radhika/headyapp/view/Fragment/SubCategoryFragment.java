package com.radhika.headyapp.view.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.radhika.headyapp.R;
import com.radhika.headyapp.model.Products;
import com.radhika.headyapp.view.adapter.SubCategoryAdapter;
import com.radhika.headyapp.viewmodel.ProductViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubCategoryFragment extends Fragment {

    private RecyclerView recyclerView;
    private  ProductViewModel productViewModel;
    private  int categotyId;
    private  ShimmerFrameLayout shimmerFrameLayout;
    private List<Products> productsList;

    public SubCategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_sub_category, container, false);
        productViewModel= ViewModelProviders.of(this).get(ProductViewModel.class);
        bindUI(view);
        getIntentData();
        bindRecyerview();
        /*productViewModel= ViewModelProviders.of(this).get(ProductViewModel.class);
        productViewModel.getSubCategory(getContext(),categotyId).observe(this, new Observer<List<Products>>() {
            @Override
            public void onChanged(List<Products> categories) {
                if(categories!=null){
                    recyclerView.setVisibility(View.VISIBLE);
                    SubCategoryAdapter subCategoryAdapter = new SubCategoryAdapter(getActivity(), categories);
                    recyclerView.setAdapter(subCategoryAdapter);
                }
            }
        });*/
        return view;
    }


    private void getIntentData() {
        assert getArguments() != null;
        categotyId = getArguments().getInt("categoryId");
    }

    private void bindUI(View view){
        recyclerView=view.findViewById(R.id.dashboard_recycler);
        shimmerFrameLayout = view.findViewById(R.id.shimmer);
    }

    private void bindRecyerview() {
        shimmerFrameLayout.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        shimmerFrameLayout.stopShimmer();
        productsList = productViewModel.getSubCategoryA(getContext(),categotyId);
        SubCategoryAdapter subCategoryAdapter = new SubCategoryAdapter(getActivity(), productsList);
        recyclerView.setAdapter(subCategoryAdapter);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }


}
