package com.allsouls.dobrowins.airbitstest.controllers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.allsouls.dobrowins.airbitstest.R;
import com.allsouls.dobrowins.airbitstest.models.Product;

import java.util.ArrayList;

/**
 * Created on 02.06.2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ProductViewHolder> {

    private ArrayList<Product> productsArrayList;

    public RecyclerViewAdapter(@NonNull ArrayList<Product> productsArrayList) {
        this.productsArrayList = productsArrayList;
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView foodItemTitle;
        RelativeLayout container;

        ProductViewHolder(View itemView) {
            super(itemView);
            foodItemTitle = (TextView) itemView.findViewById(R.id.food_item_title);
            container = (RelativeLayout) itemView.findViewById(R.id.food_item_container);
        }
    }

    private Context context;

    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.food_item_layout, parent, false);
        return new ProductViewHolder(itemView);
    }

    @Override
    @SuppressWarnings( " deprecation ")
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Product product = productsArrayList.get(position);
        holder.foodItemTitle.setText(product.toString());
        switch(product.getType()) {
            case "milk_products":
                holder.container.setBackground(
                        context.getResources().getDrawable(R.drawable.item_rounded_corners_blue_light));
                break;
            case "fruits_and_vegetables":
                holder.container.setBackground(
                        context.getResources().getDrawable(R.drawable.item_rounded_corners_green));
                break;
            case "drinks":
                holder.container.setBackground(
                        context.getResources().getDrawable(R.drawable.item_rounded_corners_gray));
                break;
            case "other":
                holder.container.setBackground(
                        context.getResources().getDrawable(R.drawable.item_rounded_corners_red));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return productsArrayList.size();
    }

    public Product getItem (int position) {
        return productsArrayList.get(position);
    }
}
