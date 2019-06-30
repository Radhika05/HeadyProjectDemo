package com.radhika.headyapp.view.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.radhika.headyapp.R;
import com.radhika.headyapp.model.Categories;
import com.radhika.headyapp.model.Products;
import com.radhika.headyapp.model.Rankings;
import com.radhika.headyapp.view.adapter.CategoryAdapter;
import com.radhika.headyapp.view.adapter.SearchAdapter;
import com.radhika.headyapp.viewmodel.ProductViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements View.OnClickListener {


    private ProductViewModel productViewModel;
    private Button textRank1, textRank2, textRank3;
    private String[] catRankName;
    private RecyclerView recyclerView;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
        bindSpinner();
        initView(view);
        fnClick();
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        return view;
    }

    private void fnClick() {
        textRank1.setOnClickListener(this);
        textRank2.setOnClickListener(this);
        textRank3.setOnClickListener(this);
    }

    private void bindSpinner() {
        List<Rankings> rankings = productViewModel.getRankCategory(this.getContext());
        catRankName = new String[rankings.size()];
        for (int i = 0; i < rankings.size(); i++) {
            catRankName[i] = rankings.get(i).getRanking();
        }
    }

    private void initView(View view) {
        textRank1 = view.findViewById(R.id.btnRank1);
        textRank2 = view.findViewById(R.id.btnRank2);
        textRank3 = view.findViewById(R.id.btnRank3);
        recyclerView = view.findViewById(R.id.rv_products);
        textRank1.setText(catRankName[0]);
        textRank2.setText(catRankName[1]);
        textRank3.setText(catRankName[2]);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnRank1:
                int rankID = 1;
                getRankRecord(rankID);
                break;
            case R.id.btnRank2:
                rankID = 2;
                getRankRecord(rankID);
                break;
            case R.id.btnRank3:
                rankID = 3;
                getRankRecord(rankID);
                break;
        }
    }

    private void getRankRecord(int rankID) {
        ProductViewModel productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
        productViewModel.getRankWiseProduct(getContext(), rankID).observe(this, new Observer<List<Products>>() {
            @Override
            public void onChanged(List<Products> products) {
                if (products != null) {
                    SearchAdapter searchAdapter = new SearchAdapter(products);
                    recyclerView.setAdapter(searchAdapter);
                }
            }
        });
    }
}
