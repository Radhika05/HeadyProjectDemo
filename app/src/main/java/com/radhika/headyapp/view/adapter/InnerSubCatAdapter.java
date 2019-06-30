package com.radhika.headyapp.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.radhika.headyapp.R;
import com.radhika.headyapp.model.TempSubCat;
import com.radhika.headyapp.utils.FragmentsManager;
import com.radhika.headyapp.view.Fragment.ProductFragment;

import java.util.List;

public class InnerSubCatAdapter extends RecyclerView.Adapter<InnerSubCatAdapter.ViewHolder> {
    private List<TempSubCat> tempProducts;

    public InnerSubCatAdapter(List<TempSubCat> tempProducts) {
        this.tempProducts = tempProducts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_list_item, parent, false);
        return new InnerSubCatAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TempSubCat products = tempProducts.get(position);
        holder.adTitle.setText(products.getCategories().getName());
    }

    @Override
    public int getItemCount() {
        return tempProducts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView adTitle;
        private Context context;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            adTitle = itemView.findViewById(R.id.add_title_txt);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position =  getLayoutPosition();
            ProductFragment productFragment = new ProductFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("productId", tempProducts.get(position).getCategories().getId());
            productFragment.setArguments(bundle);
            FragmentsManager.replaceFragment((Activity) context, productFragment, R.id.frame_one, true);
        }
    }
}
