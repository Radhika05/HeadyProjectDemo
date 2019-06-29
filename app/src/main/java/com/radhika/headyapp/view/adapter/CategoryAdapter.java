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
import com.radhika.headyapp.utils.FragmentsManager;
import com.radhika.headyapp.view.Fragment.SubCategoryFragment;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private  FragmentActivity categoryFragment;
    public List<Categories> categoryList;
    private View.OnClickListener mOnItemClickListener;
    Context mContext;


    public CategoryAdapter(FragmentActivity categoryFragment, List<Categories> categoryList) {
        this.categoryFragment  = categoryFragment;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_list_item, parent, false);
        itemView.setOnClickListener(mOnItemClickListener);
        return new ViewHolder(itemView);
    }

    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        this.mOnItemClickListener = itemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      Categories categories = categoryList.get(position);
      holder.adTitle.setText(categories.getName());

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView adTitle;
        private Context context;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            context = itemView.getContext();
            adTitle = itemView.findViewById(R.id.add_title_txt);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position =  getLayoutPosition();
            SubCategoryFragment subCategoryFragment = new SubCategoryFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("categoryId", categoryList.get(position).getId());
            subCategoryFragment.setArguments(bundle);
            FragmentsManager.replaceFragment((Activity) context, subCategoryFragment, R.id.frame, false);
        }
    }



}
