package com.mukeri.pearassignment.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mukeri.pearassignment.Interface.MenuInterface;
import com.mukeri.pearassignment.R;
import com.mukeri.pearassignment.model.CouponModel;
import com.mukeri.pearassignment.model.Menumodel;

import java.util.List;

public class CouponAdapter extends RecyclerView.Adapter<CouponAdapter.MainViewHolder> {

    private Context context;
    private List<CouponModel> allCategoryList;

    public CouponAdapter(Context context, List<CouponModel> allCategoryList) {
        this.context = context;
        this.allCategoryList = allCategoryList;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainViewHolder(LayoutInflater.from(context).inflate(R.layout.single_coupon, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {

        holder.subdescription.setText(allCategoryList.get(position).getSubdescription());
        holder.description.setText(allCategoryList.get(position).getDescription());

    }

    @Override
    public int getItemCount() {
        return allCategoryList.size();
    }

    public static final class MainViewHolder extends RecyclerView.ViewHolder{

        TextView description;
        TextView subdescription;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);

            description = itemView.findViewById(R.id.description);
            subdescription = itemView.findViewById(R.id.subdescription);

        }
    }
}
