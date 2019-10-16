package com.example.admin.health_app.Patient_Side_Scrren.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.health_app.Patient_Side_Scrren.Follow_Up_NewCase.Follow_Up_List;
import com.example.admin.health_app.Patient_Side_Scrren.Activity.Patient_View_Pharmacy_dispatch_Order;
import com.example.admin.health_app.Patient_Side_Scrren.Activity.View_Pharmacy_Order;
import com.example.admin.health_app.R;

import java.util.ArrayList;

public class View_Pharmacy_List_Order_Adapter extends RecyclerView.Adapter<View_Pharmacy_List_Order_Adapter.ViewHolder> {
    private Context context;
    private ArrayList<String> doc_appoinList;


    public View_Pharmacy_List_Order_Adapter(Context context, ArrayList<String> data) {
        this.context = context;
        doc_appoinList = data;
        Log.e("data_adpater11111", "view_Seller" + data);
    }

    public View_Pharmacy_List_Order_Adapter(Context context, String data) {
        this.context = context;
        data = data;
        Log.e("data_adpater11111", "view_Seller" + data);

    }

    public View_Pharmacy_List_Order_Adapter(Follow_Up_List follow_up_list) {
        context = follow_up_list;
    }

    public View_Pharmacy_List_Order_Adapter(View_Pharmacy_Order view_pathology_order) {
        context = view_pathology_order;
    }

//    public Doctor_view_appointment_adapter(Context context, ArrayList<HashMap<String, String>> pat_appointmentList) {
//        this.context = context;
//        doc_appoinList = pat_appointmentList;
//
//        clickListener = (ClickListener) context;
//
//    }

    @Override
    public View_Pharmacy_List_Order_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.single_row_patient_view_pathology_order, parent, false);
        return new View_Pharmacy_List_Order_Adapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(View_Pharmacy_List_Order_Adapter.ViewHolder holder, final int position) {
//        holder.patientNameTV.setText(doc_appoinList.get(position));


        holder.viewDetailsTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
context.startActivity(new Intent(context,Patient_View_Pharmacy_dispatch_Order.class));
            }
        });

    }


    @Override
    public int getItemCount() {

        return 1;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView viewDetailsTV, patientNameTV_id;

        public ViewHolder(View itemView) {
            super(itemView);
            viewDetailsTV = (TextView) itemView.findViewById(R.id.viewDetailsTV);
            patientNameTV_id = (TextView) itemView.findViewById(R.id.patientNameTV_id);
        }
    }


}
