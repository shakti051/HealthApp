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

import com.example.admin.health_app.Patient_Side_Scrren.Activity.Patient_View_Dispatch_Radiology_Order;
import com.example.admin.health_app.Patient_Side_Scrren.Activity.Patient_View_Pathology_Dispatch_order;
import com.example.admin.health_app.R;

import java.util.ArrayList;

public class Patient_View_radiology_Adapter extends RecyclerView.Adapter<Patient_View_radiology_Adapter.ViewHolder> {
    private Context context;
    private String data;
    private ArrayList<String> doc_appoinList;

    private int[] images_array = {R.drawable.apollo_image};

    public Patient_View_radiology_Adapter(Context context, ArrayList<String> data) {
        this.context = context;
        doc_appoinList = data;
        Log.e("data_adpater11111", "view_Seller" + data);
    }

    public Patient_View_radiology_Adapter(Context context, String data) {
        this.context = context;
        this.data = data;
        Log.e("data_adpater11111", "view_Seller" + data);

    }


//    public Doctor_view_appointment_adapter(Context context, ArrayList<HashMap<String, String>> pat_appointmentList) {
//        this.context = context;
//        doc_appoinList = pat_appointmentList;
//
//        clickListener = (ClickListener) context;
//
//    }

    @Override
    public Patient_View_radiology_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.single_row_patient_view_pathology_order, parent, false);
        return new Patient_View_radiology_Adapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Patient_View_radiology_Adapter.ViewHolder holder, final int position) {

        holder.patientNameTV_id.setText(data);
        holder.patientIV.setImageResource(images_array[position]);
        holder.viewDetailsTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, Patient_View_Dispatch_Radiology_Order.class));
            }
        });


    }


    @Override
    public int getItemCount() {

        return 1;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView viewDetailsTV, patientNameTV_id;
        private ImageView patientIV;

        public ViewHolder(View itemView) {
            super(itemView);
            viewDetailsTV = (TextView) itemView.findViewById(R.id.viewDetailsTV);
            patientNameTV_id = (TextView) itemView.findViewById(R.id.patientNameTV_id);
            patientIV = (ImageView) itemView.findViewById(R.id.patientIV);
        }
    }


}


