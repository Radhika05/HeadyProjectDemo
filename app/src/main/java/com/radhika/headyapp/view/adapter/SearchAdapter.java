package com.radhika.headyapp.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.radhika.headyapp.R;
import com.radhika.headyapp.model.Categories;
import com.radhika.headyapp.model.Products;
import com.radhika.headyapp.utils.FragmentsManager;
import com.radhika.headyapp.view.Fragment.SubCategoryFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private List<Products> productsList;

    public SearchAdapter(List<Products> productsList) {
        this.productsList= productsList;
    }

    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_list_item, parent, false);
        return new SearchAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, int position) {
        Products categories = productsList.get(position);
        holder.adTitle.setText(categories.getName());
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView adTitle;
        ImageView agImage;
        private Context context;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            context = itemView.getContext();
            adTitle = itemView.findViewById(R.id.add_title_txt);
            agImage = itemView.findViewById(R.id.add_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position =  getLayoutPosition();
        }
    }
}
