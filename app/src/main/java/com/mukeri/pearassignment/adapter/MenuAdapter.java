package com.mukeri.pearassignment.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mukeri.pearassignment.Interface.MenuInterface;
import com.mukeri.pearassignment.R;
import com.mukeri.pearassignment.model.AllCategory;
import com.mukeri.pearassignment.model.CategoryItem;
import com.mukeri.pearassignment.model.Menumodel;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MainViewHolder> {

    private Context context;
    private List<Menumodel> allCategoryList;

    private MenuInterface menuInterface;

    public MenuAdapter(Context context, List<Menumodel> allCategoryList,MenuInterface menuInterface) {
        this.context = context;
        this.allCategoryList = allCategoryList;
        this.menuInterface=menuInterface;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainViewHolder(LayoutInflater.from(context).inflate(R.layout.single_menu, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {

        holder.categoryTitle.setText(allCategoryList.get(position).getCategoryTitle());
        holder.totalitem.setText(allCategoryList.get(position).getTotalitem());

        holder.categoryTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuInterface.onclickmenu(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return allCategoryList.size();
    }

    public static final class MainViewHolder extends RecyclerView.ViewHolder{

        TextView categoryTitle;
        TextView totalitem;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryTitle = itemView.findViewById(R.id.menuname);
            totalitem = itemView.findViewById(R.id.totalitem);

        }
    }
}
