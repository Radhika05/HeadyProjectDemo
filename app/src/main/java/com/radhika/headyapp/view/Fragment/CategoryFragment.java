package com.radhika.headyapp.view.Fragment;


import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.radhika.headyapp.R;
import com.radhika.headyapp.model.Categories;
import com.radhika.headyapp.view.adapter.CategoryAdapter;
import com.radhika.headyapp.viewmodel.ProductViewModel;

import java.util.List;
import java.util.Objects;


public class CategoryFragment extends Fragment {

    private RecyclerView recyclerView;
    TextView offline;
    private ShimmerFrameLayout shimmerFrameLayout;

    private int[] img = new int[]{
            R.drawable.img1,
            R.drawable.img6,
            R.drawable.img2,
            R.drawable.img3,
            R.drawable.img4,
            R.drawable.img5,
            R.drawable.img7,
            R.drawable.img6,
            R.drawable.img7
    };


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        recyclerView = view.findViewById(R.id.dashboard_recycler);
        shimmerFrameLayout = view.findViewById(R.id.shimmer);
        getCategoryList();
        offline = Objects.requireNonNull(getActivity()).findViewById(R.id.offline_txt);
        ProductViewModel productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
        productViewModel.getCategory(getContext()).observe(this, new Observer<List<Categories>>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onChanged(List<Categories> categories) {
                if (categories != null && categories.size()!=0) {
                    recyclerView.setVisibility(View.VISIBLE);
                    offline.setVisibility(View.GONE);
                    CategoryAdapter categoryAdapter = new CategoryAdapter(categories, img);
                    recyclerView.setAdapter(categoryAdapter);
                }
                else {
                  offline.setVisibility(View.VISIBLE);
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
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}



