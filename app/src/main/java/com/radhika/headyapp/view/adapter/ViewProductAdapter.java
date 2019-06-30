package com.radhika.headyapp.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.radhika.headyapp.R;
import com.radhika.headyapp.model.TempProductDetails;

import java.util.List;

public  class ViewProductAdapter extends RecyclerView.Adapter<ViewProductAdapter.ViewHolder> {
    private List<TempProductDetails> tempProducts;
    public ViewProductAdapter(List<TempProductDetails> tempProducts) {
        this.tempProducts = tempProducts;
    }

    @NonNull
    @Override


    public ViewProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_list, parent, false);
        return new ViewProductAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewProductAdapter.ViewHolder holder, int position) {
        TempProductDetails  tempProduct = tempProducts.get(position);
        holder.color.setText(tempProduct.variants.getColor());
        holder.size.setText(tempProduct.variants.getSize());
        holder.price.setText(tempProduct.variants.getPrice());
    }

    @Override
    public int getItemCount() {
        return tempProducts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView color,size,price;
        Context context;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            color = itemView.findViewById(R.id.txt_color_value);
            price = itemView.findViewById(R.id.txt_price_value);
            size = itemView.findViewById(R.id.txt_size_value);
        }

    }
}
