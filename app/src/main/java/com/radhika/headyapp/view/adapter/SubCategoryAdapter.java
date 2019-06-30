package com.radhika.headyapp.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.radhika.headyapp.R;
import com.radhika.headyapp.model.Categories;
import com.radhika.headyapp.model.TempSubCat;
import com.radhika.headyapp.utils.FragmentsManager;
import com.radhika.headyapp.view.Fragment.ProductFragment;

import java.util.List;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.ViewHolder> {

   private FragmentActivity activity;
    private List<TempSubCat> subcategories;

    public SubCategoryAdapter(FragmentActivity activity, List<TempSubCat> subcategories) {
        this.activity = activity;
        this.subcategories = subcategories;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sub_category, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TempSubCat products = subcategories.get(position);
        holder.adTitle.setText(products.getCategories().getName());
    }

    @Override
    public int getItemCount() {
        return subcategories.size();
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
            Categories categories = subcategories.get(position).getCategories();
            bundle.putInt("productId", subcategories.get(position).getCategories().getId());
            productFragment.setArguments(bundle);
            FragmentsManager.replaceFragment((Activity) context, productFragment, R.id.frame_one, true);
        }
    }
}
