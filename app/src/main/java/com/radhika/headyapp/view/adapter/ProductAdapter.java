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
import com.radhika.headyapp.model.TempProduct;
import com.radhika.headyapp.utils.FragmentsManager;
import com.radhika.headyapp.view.Fragment.ViewProductDetailsFragment;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<TempProduct> tempProducts;
    public ProductAdapter(List<TempProduct> tempProducts) {
        this.tempProducts = tempProducts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_list_item, parent, false);
        return new ProductAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TempProduct  tempProduct = tempProducts.get(position);
        holder.adTitle.setText(tempProduct.products.getName());

    }

    @Override
    public int getItemCount() {
        return tempProducts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView adTitle;
        Context context;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            adTitle = itemView.findViewById(R.id.add_title_txt);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position =  getLayoutPosition();
            ViewProductDetailsFragment viewProductDetailsFragment = new ViewProductDetailsFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("productItemId", tempProducts.get(position).products.getId());
            viewProductDetailsFragment.setArguments(bundle);
            FragmentsManager.replaceFragment((Activity) context, viewProductDetailsFragment, R.id.frame_one, true);
        }
    }
}
