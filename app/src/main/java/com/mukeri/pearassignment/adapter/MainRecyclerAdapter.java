package com.mukeri.pearassignment.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mukeri.pearassignment.Interface.CategoryInterface;
import com.mukeri.pearassignment.R;
import com.mukeri.pearassignment.model.AllCategory;
import com.mukeri.pearassignment.model.CategoryItem;
import com.mukeri.pearassignment.model.CouponModel;

import java.util.List;

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.MainViewHolder> {
    public static  final int MENU = 0;
    public static  final int COUPON = 1;
    private Context context;
    private List<AllCategory> allCategoryList;

    CategoryInterface categoryInterface;

    public MainRecyclerAdapter(Context context, List<AllCategory> allCategoryList,CategoryInterface categoryInterface) {
        this.context = context;
        this.allCategoryList = allCategoryList;
        this.categoryInterface = categoryInterface;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainViewHolder(LayoutInflater.from(context).inflate(R.layout.main_recycler_row_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {


        if(allCategoryList.get(position).getLayouttype().equals("menu"))
        {
            holder.couponsec.setVisibility(View.GONE);
            holder.categoryTitle.setText(allCategoryList.get(position).getCategoryTitle());

            setCatItemRecycler(holder.itemRecycler, allCategoryList.get(position).getCategoryItemList());
            if(allCategoryList.get(position).isIscollapsed())
            {
                holder.compress.setImageResource(R.drawable.down);
                holder.itemRecycler.setVisibility(View.GONE);
            }
            else
            {
                holder.compress.setImageResource(R.drawable.up);
                holder.itemRecycler.setVisibility(View.VISIBLE);
            }

            holder.compress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(allCategoryList.get(position).isIscollapsed())
                    {
                        categoryInterface.onclickmenu(position,false);
//                        allCategoryList.get(position).setIscollapsed(false);
//                        notifyDataSetChanged();
                        //holder.itemRecycler.setVisibility(View.GONE);
                    }
                    else
                    {
                        categoryInterface.onclickmenu(position,true);
//                        allCategoryList.get(position).setIscollapsed(true);
//                        notifyDataSetChanged();
                       // holder.itemRecycler.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
        else
        {
            holder.couponsec.setVisibility(View.VISIBLE);
            holder.categoryTitle.setVisibility(View.GONE);
            holder.compress.setVisibility(View.GONE);
            setCouponRecycler(holder.coupon_recycler, allCategoryList.get(position).getCouponModels());
        }





    }

    @Override
    public int getItemCount() {
        return allCategoryList.size();
    }

    public static final class MainViewHolder extends RecyclerView.ViewHolder{


        TextView categoryTitle;
        RecyclerView itemRecycler;
        RecyclerView coupon_recycler;
        ImageView compress;
        LinearLayout couponsec;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);

            compress = itemView.findViewById(R.id.compress);
            categoryTitle = itemView.findViewById(R.id.cat_title);
            itemRecycler = itemView.findViewById(R.id.item_recycler);
            coupon_recycler = itemView.findViewById(R.id.coupon_recycler);
            couponsec = itemView.findViewById(R.id.couponsec);

        }
    }

    private void setCatItemRecycler(RecyclerView recyclerView, List<CategoryItem> categoryItemList){
        CategoryItemRecyclerAdapter itemRecyclerAdapter = new CategoryItemRecyclerAdapter(context, categoryItemList);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(itemRecyclerAdapter);

    }

    private void setCouponRecycler(RecyclerView recyclerView, List<CouponModel> couponModels){
        CouponAdapter couponAdapter = new CouponAdapter(context, couponModels);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(couponAdapter);

    }
}
