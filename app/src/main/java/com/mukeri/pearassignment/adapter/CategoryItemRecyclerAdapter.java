package com.mukeri.pearassignment.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.mukeri.pearassignment.Interface.ItemInterface;
import com.mukeri.pearassignment.MainActivity;
import com.mukeri.pearassignment.R;
import com.mukeri.pearassignment.model.CategoryItem;
import com.wooplr.spotlight.SpotlightView;

import java.util.List;

public class CategoryItemRecyclerAdapter extends RecyclerView.Adapter<CategoryItemRecyclerAdapter.CategoryItemViewHolder> {
    private boolean isRevealEnabled = true;
    private SpotlightView spotLight;
    private Context context;
    private List<CategoryItem> categoryItemList;
    private ItemInterface itemInterface;
    private static final String INTRO_CARD = "fab_intro";
    private static final String INTRO_SWITCH = "switch_intro";
    private static final String INTRO_RESET = "reset_intro";
    private static final String INTRO_REPEAT = "repeat_intro";
    private static final String INTRO_CHANGE_POSITION = "change_position_intro";
    private static final String INTRO_SEQUENCE = "sequence_intro";
    public CategoryItemRecyclerAdapter(Context context, List<CategoryItem> categoryItemList,ItemInterface itemInterface) {
        this.context = context;
        this.categoryItemList = categoryItemList;
        this.itemInterface = itemInterface;
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
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemInterface.onclickmenu(position,holder.add);
            }
        });

        if (categoryItemList.get(position).isShowtootip())
        {
            itemInterface.onclickmenu(position,holder.add);
        }
        else
        {

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
        AppCompatButton add;

        public CategoryItemViewHolder(@NonNull View itemView) {
            super(itemView);

            itemImage = itemView.findViewById(R.id.item_image);
            add = itemView.findViewById(R.id.add);
            itemname = itemView.findViewById(R.id.itemname);
            acprice = itemView.findViewById(R.id.acprice);
            price = itemView.findViewById(R.id.price);
            discount = itemView.findViewById(R.id.discount);

        }
    }

}