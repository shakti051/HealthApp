package com.example.admin.health_app.Doctor_Side.Adapter;

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
 * Created by Ankit on 1/10/2018.
 */

public class Doctor_add_Medicine_Adapter extends RecyclerView.Adapter<Doctor_add_Medicine_Adapter.ViewHolder> {
    private Context context;
    String data;
    ArrayList<HashMap<String, String>> hospitaVisitlist = new ArrayList<>();

    public Doctor_add_Medicine_Adapter(Context context, ArrayList<HashMap<String, String>> data) {
        this.context = context;
        this.hospitaVisitlist = data;
        Log.e("data_adpater11111", "view_Seller" + data);
    }

    public Doctor_add_Medicine_Adapter(Context context, String data) {
        this.context = context;
        this.data = data;
        Log.e("data_adpater11111", "view_Seller" + data);
    }

    @Override
    public Doctor_add_Medicine_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.single_row_add_medicine, parent, false);
        return new Doctor_add_Medicine_Adapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Doctor_add_Medicine_Adapter.ViewHolder holder, int position) {


    }


    @Override
    public int getItemCount() {
        return 1;

    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);


        }
    }

}
