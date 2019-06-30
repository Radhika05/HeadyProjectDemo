package com.radhika.headyapp.view.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.radhika.headyapp.R;
import com.radhika.headyapp.model.TempProduct;
import com.radhika.headyapp.view.adapter.ProductAdapter;
import com.radhika.headyapp.viewmodel.ProductViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductFragment extends Fragment {

    private RecyclerView recyclerView;
    private int tempProductsId;
    private ShimmerFrameLayout shimmerFrameLayout;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_product, container, false);

        bindUI(view);
        getIntentData();
        setRecyclerView();
        ProductViewModel productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
        productViewModel.getProduct(getContext(),tempProductsId).observe(this, new Observer<List<TempProduct>>() {
            @Override
            public void onChanged(List<TempProduct> tempProducts) {
                recyclerView.setVisibility(View.VISIBLE);
                ProductAdapter productAdapter = new ProductAdapter(tempProducts);
                recyclerView.setAdapter(productAdapter);
            }
        });
        return  view;
    }

    private void bindUI(View view) {
        recyclerView=view.findViewById(R.id.dashboard_recycler);
        shimmerFrameLayout = view.findViewById(R.id.shimmer);
    }


    private void getIntentData() {
        assert getArguments() != null;
        tempProductsId = getArguments().getInt("productId");
    }

    private void setRecyclerView(){
        shimmerFrameLayout.stopShimmer();
        shimmerFrameLayout.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

}
