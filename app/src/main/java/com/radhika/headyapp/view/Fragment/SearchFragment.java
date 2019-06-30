package com.radhika.headyapp.view.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.radhika.headyapp.R;
import com.radhika.headyapp.model.Categories;
import com.radhika.headyapp.model.Rankings;
import com.radhika.headyapp.viewmodel.ProductViewModel;

import java.util.List;


public class SearchFragment extends Fragment implements View.OnClickListener {
   private ProductViewModel productViewModel;
    private TextView textRank1,textRank2,textRank3;
    private String[] catRankName ;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_search, container, false);
        productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
        bindSpinner();
        initView(view);
        fnClick();
        return view;
    }

    private void fnClick() {
        textRank1.setOnClickListener(this);
        textRank2.setOnClickListener(this);
        textRank3.setOnClickListener(this);
    }

    private void bindSpinner(){
       List<Rankings> rankings =  productViewModel.getRankCategory(this.getContext());
        catRankName = new String[rankings.size()];
        for (int i = 0; i < rankings.size(); i++) {
            catRankName[i] = rankings.get(i).getRanking();
        }
    }

    private void initView(View view){
        textRank1 = view.findViewById(R.id.btnRank1);
        textRank2 = view.findViewById(R.id.btnRank2);
        textRank3 = view.findViewById(R.id.btnRank3);
        textRank1.setText(catRankName[0]);
        textRank2.setText(catRankName[1]);
        textRank3.setText(catRankName[2]);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
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
        productViewModel.getCategory(getContext()).observe(this, new Observer<List<Categories>>() {
            @Override
            public void onChanged(List<Categories> categories) {
                if(categories!=null){
                   /* recyclerView.setVisibility(View.VISIBLE);
                    CategoryAdapter categoryAdapter = new CategoryAdapter(categories,img);
                    recyclerView.setAdapter(categoryAdapter);*/
                }
            }
        });
    }
}
