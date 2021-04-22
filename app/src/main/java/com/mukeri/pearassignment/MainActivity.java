package com.mukeri.pearassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mukeri.pearassignment.Interface.CategoryInterface;
import com.mukeri.pearassignment.Interface.MenuInterface;
import com.mukeri.pearassignment.Utils.AppConstant;
import com.mukeri.pearassignment.adapter.CouponAdapter;
import com.mukeri.pearassignment.adapter.MainRecyclerAdapter;
import com.mukeri.pearassignment.adapter.MenuAdapter;
import com.mukeri.pearassignment.model.AllCategory;
import com.mukeri.pearassignment.model.CardItem;
import com.mukeri.pearassignment.model.CategoryItem;
import com.mukeri.pearassignment.model.CouponModel;
import com.mukeri.pearassignment.model.Menumodel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements MenuInterface, CategoryInterface {
    private RequestQueue requestQueue;
    ProgressDialog progressDialog;
    Dialog dialog;
    Button menu;
    RecyclerView mainCategoryRecycler;
    MainRecyclerAdapter mainRecyclerAdapter;
    List<Menumodel> menumodels;
    List<CouponModel> couponModels;
    RecyclerView menurecyclerview;
    MenuAdapter menuAdapter;
    List<AllCategory> allCategoryList;
   // List<CategoryItem> categoryItemList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitleColor(R.color.black);
        progressDialog= new ProgressDialog(this);
        menumodels= new ArrayList<>();
        allCategoryList=new ArrayList<>();
       // categoryItemList = new ArrayList<>();
        menu= findViewById(R.id.menu);
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_droppoint);
        Window window = dialog.getWindow();
        menurecyclerview=dialog.findViewById(R.id.menurecyclerview);
        LinearLayoutManager linearLayoutManager;
        linearLayoutManager= new LinearLayoutManager(MainActivity.this);
        menurecyclerview.setLayoutManager(linearLayoutManager);
        menuAdapter = new MenuAdapter(MainActivity.this,menumodels,this);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                WindowManager.LayoutParams wlp = window.getAttributes();
                wlp.gravity = Gravity.BOTTOM|Gravity.RIGHT;
                wlp.y = 200;
                wlp.x = 20;
                dialog.show();
            }
        });

        GetCouponlist();
    }


    private void setMainCategoryRecycler(List<AllCategory> allCategoryList){

        mainCategoryRecycler = findViewById(R.id.main_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mainCategoryRecycler.setLayoutManager(layoutManager);
        mainRecyclerAdapter = new MainRecyclerAdapter(this, allCategoryList,this);
        mainCategoryRecycler.setAdapter(mainRecyclerAdapter);

    }





    public void GetData()
    {
        progressDialog.setTitle("Please wait..");
        progressDialog.setMessage("Loading..");
        progressDialog.show();
        StringRequest stringRequest= new StringRequest(Request.Method.GET, AppConstant.GET_RESTAURANT_MENU,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response)
                    {
                        JSONObject obj = null;
                        progressDialog.dismiss();
                        try {
                            obj = new JSONObject(response);
                           // List<AllCategory> allCategoryList = new ArrayList<>();
                            JSONArray jsonDevices = obj.getJSONArray("Categories");
                            setTitle(obj.getString("Name"));
                            for (int i = 0; i < jsonDevices.length(); i++)
                            {
                                JSONObject d = jsonDevices.getJSONObject(i);
                                List<CategoryItem> categoryItemList= new ArrayList<>();
                                JSONArray items = d.getJSONArray("Items");
                                for (int j = 0; j<items.length(); j++)
                                {

                                    JSONObject item = items.getJSONObject(j);
                                    CategoryItem categoryItem= new CategoryItem();
                                    categoryItem.setImageUrl(R.drawable.sampledish);
                                    categoryItem.setItemname(item.getString("Name"));
                                    if(item.getInt("AcPrice")>item.getInt("Price"))
                                    {
                                        int disc=item.getInt("AcPrice")-item.getInt("Price");
                                        int dc=((disc*100)/item.getInt("AcPrice"));
                                        categoryItem.setDiscount(String.valueOf(dc)+"% Off");
                                    }
                                    else
                                    {
                                        categoryItem.setDiscount(String.valueOf(0));
                                    }


                                    categoryItem.setPrice("₹"+String.valueOf(item.getInt("Price")));
                                    categoryItem.setACprice("₹"+String.valueOf(item.getInt("AcPrice")));
                                    categoryItem.setItemId(item.getString("_id"));
                                    categoryItemList.add(categoryItem);
                                   // categoryItemList.add(new CategoryItem(1,item.getString("Name"),R.drawable.sampledish));
                                }
                                Menumodel menumodel= new Menumodel();
                                menumodel.setCategoryTitle(d.getString("Category_name"));
                                menumodel.setTotalitem(String.valueOf(categoryItemList.size()));
                                menumodels.add(menumodel);
                                menurecyclerview.setAdapter(menuAdapter);
                                menuAdapter.notifyDataSetChanged();
                                allCategoryList.add(new AllCategory(d.getString("Category_name"),false, categoryItemList,"menu"));
                            }
                            setMainCategoryRecycler(allCategoryList);
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                if (error instanceof TimeoutError) {
                    Toast.makeText(getApplicationContext(),
                            "Server Timeout",
                            Toast.LENGTH_LONG).show();
                }
                else if (error instanceof NoConnectionError)
                {
                    //TODO
                    Toast.makeText(getApplicationContext(),
                            "No Internet Connection",
                            Toast.LENGTH_LONG).show();
                }

                else if (error instanceof AuthFailureError)
                {
                    //TODO
                    Toast.makeText(getApplicationContext(),
                            "Auth Failure",
                            Toast.LENGTH_LONG).show();
                }
                else if (error instanceof ServerError)
                {
                    //TODO
                    Toast.makeText(getApplicationContext(),
                            "Server Error",
                            Toast.LENGTH_LONG).show();

                }
                else if (error instanceof NetworkError)
                {
                    //TODO
                    Toast.makeText(getApplicationContext(),
                            "Poor Internet Connection",
                            Toast.LENGTH_LONG).show();
                }
                else if (error instanceof ParseError)
                {
                    //TODO

                    Toast.makeText(getApplicationContext(),
                            "Parse Error",
                            Toast.LENGTH_LONG).show();
                }
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return super.getParams();
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }


    public void GetCouponlist()
    {
        progressDialog.setTitle("Please wait..");
        progressDialog.setMessage("Loading..");
        progressDialog.show();
        String data = "{\"restaurant_category\":\"NPD\",\"user_id\":\"gXOMAgulWnR2D5o52vgUVB5R1A63\"}";
        final String savedata= data;
        String URL=AppConstant.GET_COUPON_LIST;

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONArray jsonarray = new JSONArray(response);
                    List<CouponModel> couponModels = new ArrayList<>();
                    for(int i=0; i < jsonarray.length(); i++) {
                        JSONObject jsonobject = jsonarray.getJSONObject(i);
                        CouponModel couponModel= new CouponModel();
                        couponModel.setDescription(jsonobject.getString("description"));
                        couponModel.setSubdescription(jsonobject.getString("sub_description"));
                        couponModel.setCouponid(jsonobject.getString("_id"));
                        couponModels.add(couponModel);
                    }
                    allCategoryList.add(new AllCategory(couponModels,"coupon"));
                    GetData();
                }  catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return savedata == null ? null : savedata.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    //Log.v("Unsupported Encoding while trying to get the bytes", data);
                    return null;
                }
            }

        };
        requestQueue.add(stringRequest);
    }



    @Override
    public void onclickmenu(int position) {
        mainCategoryRecycler.smoothScrollToPosition(position+1);
        dialog.dismiss();
    }

    @Override
    public void onclickmenu(int position, boolean collapse) {
        allCategoryList.get(position).setIscollapsed(collapse);
        mainRecyclerAdapter.notifyDataSetChanged();
    }
}