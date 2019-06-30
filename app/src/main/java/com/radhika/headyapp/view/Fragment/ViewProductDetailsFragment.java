package com.radhika.headyapp.view.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.radhika.headyapp.R;
import com.radhika.headyapp.model.TempProductDetails;
import com.radhika.headyapp.view.adapter.ViewProductAdapter;
import com.radhika.headyapp.viewmodel.ProductViewModel;

import java.util.List;

public class ViewProductDetailsFragment extends Fragment {

    private RecyclerView bindPicRecycler;
    private int tempProductDetailsID;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_view_product_details, container, false);
        bindUI(view);
        getIntentData();
        bindRecyerview();
        ProductViewModel productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
        productViewModel.getProductDetails(getContext(),tempProductDetailsID).observe(this, new Observer<List<TempProductDetails>>() {
            @Override
            public void onChanged(List<TempProductDetails> tempProducts) {
                bindPicRecycler.setVisibility(View.VISIBLE);
                ViewProductAdapter viewProductAdapter = new ViewProductAdapter(tempProducts);
                bindPicRecycler.setAdapter(viewProductAdapter);
            }
        });
        return view;
    }

    private void getIntentData(){
        assert getArguments() != null;
        tempProductDetailsID = getArguments().getInt("productItemId");
    }

    private void bindRecyerview() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        bindPicRecycler.setLayoutManager(mLayoutManager);
        bindPicRecycler.setItemAnimator(new DefaultItemAnimator());
    }

    private void bindUI(View view) {
        bindPicRecycler = view.findViewById(R.id.view_photo_recyler);
    }
}
