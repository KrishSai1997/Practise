package com.example.practise.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.practise.Interface.ItemClickListner;
import com.example.practise.R;

public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtProductName, txtTotalProducts, txtProductQuantity;
    private ItemClickListner itemClickListner;


    public CartViewHolder(@NonNull View itemView) {
        super(itemView);

        txtProductName = itemView.findViewById(R.id.cart_product_name);
        txtTotalProducts = itemView.findViewById(R.id.cart_total_products);
        txtProductQuantity = itemView.findViewById(R.id.cart_product_quantity);
    }

    @Override
    public void onClick(View v) {
        itemClickListner.onClick(v, getAdapterPosition(), false);
    }

    public void setItemClickListner(ItemClickListner itemClickListner) {
        this.itemClickListner = itemClickListner;
    }
}
