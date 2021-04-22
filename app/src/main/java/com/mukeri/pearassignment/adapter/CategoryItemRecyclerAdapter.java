package com.mukeri.pearassignment.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mukeri.pearassignment.R;
import com.mukeri.pearassignment.model.CategoryItem;

import java.util.List;

public class CategoryItemRecyclerAdapter extends RecyclerView.Adapter<CategoryItemRecyclerAdapter.CategoryItemViewHolder> {

    private Context context;
    private List<CategoryItem> categoryItemList;

    public CategoryItemRecyclerAdapter(Context context, List<CategoryItem> categoryItemList) {
        this.context = context;
        this.categoryItemList = categoryItemList;
    }

    @NonNull
    @Override
    public CategoryItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryItemViewHolder(LayoutInflater.from(context).inflate(R.layout.category_row_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryItemViewHolder holder, int position) {

        holder.price.setText(categoryItemList.get(position).getPrice());
        if(categoryItemList.get(position).getDiscount().equals("0"))
        {
            holder.discount.setVisibility(View.GONE);
            holder.acprice.setVisibility(View.GONE);
        }
        else
        {
            holder.discount.setVisibility(View.VISIBLE);
            holder.acprice.setVisibility(View.VISIBLE);
        }
        holder.discount.setText(categoryItemList.get(position).getDiscount());
        holder.acprice.setText(categoryItemList.get(position).getACprice());
        holder.acprice.setPaintFlags(holder.acprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.itemname.setText(categoryItemList.get(position).getItemname());
        holder.itemImage.setImageResource(categoryItemList.get(position).getImageUrl());
    }

    @Override
    public int getItemCount() {
        return categoryItemList.size();
    }

    public static final class CategoryItemViewHolder extends RecyclerView.ViewHolder{

        ImageView itemImage;
        TextView itemname;
        TextView price;
        TextView acprice;
        TextView discount;

        public CategoryItemViewHolder(@NonNull View itemView) {
            super(itemView);

            itemImage = itemView.findViewById(R.id.item_image);
            itemname = itemView.findViewById(R.id.itemname);
            acprice = itemView.findViewById(R.id.acprice);
            price = itemView.findViewById(R.id.price);
            discount = itemView.findViewById(R.id.discount);

        }
    }

}