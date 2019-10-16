package com.example.admin.health_app.Pathology.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.health_app.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Admin on 5/21/2018.
 */


public class Pathology_Order_Test_Adapter extends RecyclerView.Adapter<Pathology_Order_Test_Adapter.ViewHolder> {
    private Context context;
    String[] data;
    ArrayList<HashMap<String, String>> hospitaVisitlist = new ArrayList<>();

    public Pathology_Order_Test_Adapter(Context context, ArrayList<HashMap<String, String>> data) {
        this.context = context;
        this.hospitaVisitlist = data;
        Log.e("data_adpater11111", "view_Seller" + data);
    }

    public Pathology_Order_Test_Adapter(Context context, String[] data) {
        this.context = context;
        this.data = data;
        Log.e("data_adpater11111", "view_Seller" + data);
    }

    @Override
    public Pathology_Order_Test_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.single_row_pathology_order_test, parent, false);
        return new Pathology_Order_Test_Adapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Pathology_Order_Test_Adapter.ViewHolder holder, int position) {
holder.testTV.setText(data[position]);

    }


    @Override
    public int getItemCount() {
        return data.length;

    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView testTV;

        public ViewHolder(View itemView) {
            super(itemView);
            testTV=(TextView)itemView.findViewById(R.id.testTV);

        }
    }

}

