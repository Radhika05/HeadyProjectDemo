package com.radhika.headyapp.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.radhika.headyapp.R;
import com.radhika.headyapp.model.Categories;
import com.radhika.headyapp.view.Fragment.CategoryFragment;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private  CategoryFragment categoryFragment;
    private LiveData<List<Categories>> categoryList;
    Context mContext;


    public CategoryAdapter(CategoryFragment categoryFragment, LiveData<List<Categories>> categoryList) {
        this.categoryFragment  = categoryFragment;
        this.categoryList = categoryList;
        mContext = categoryFragment.getContext();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_list_item, parent, false);
        //itemView.setOnClickListener(mOnItemClickListener);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        categoryList = (LiveData<List<Categories>>) categoryList.getValue();
        holder.adTitle.setText((CharSequence) categoryList.getValue());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView adTitle;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            adTitle = itemView.findViewById(R.id.add_title_txt);
        }
    }
}
