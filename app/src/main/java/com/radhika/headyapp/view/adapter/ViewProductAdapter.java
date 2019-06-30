package com.radhika.headyapp.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView color,size,price;
        Context context;
        Button btnEmail,btnPhone,btnMessage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            color = itemView.findViewById(R.id.txt_color_value);
            price = itemView.findViewById(R.id.txt_price_value);
            size = itemView.findViewById(R.id.txt_size_value);
            btnPhone = itemView.findViewById(R.id.btn_call);
            btnEmail = itemView.findViewById(R.id.btn_mail);
            btnMessage = itemView.findViewById(R.id.btn_txt);
            btnPhone.setOnClickListener(this);
            btnEmail.setOnClickListener(this);
            btnMessage.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_call:
                    openDilaler();
                    break;
                case R.id.btn_mail:
                    openMail();
                    break;
                case R.id.btn_txt:
                    openMessager();
                    break;
            }
        }


        private void openMessager() {
            Intent smsIntent = new Intent(Intent.ACTION_VIEW);
            smsIntent.setData(Uri.parse("smsto:"));
            smsIntent.putExtra("address", 123456789);
            smsIntent.putExtra("sms_body", "Regarding Product Inquiry");
            context.startActivity(smsIntent);
        }

        private void openMail() {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", "test@gmail.com", null));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Regarding Product Inquiry");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Hello");
            context.startActivity(Intent.createChooser(emailIntent, "Send email..."));
        }

        private void openDilaler() {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + 12356789));
            context.startActivity(intent);
        }
    }
}
