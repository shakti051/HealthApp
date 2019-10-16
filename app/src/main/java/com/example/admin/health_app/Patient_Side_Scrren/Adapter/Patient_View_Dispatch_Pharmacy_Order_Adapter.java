package com.example.admin.health_app.Patient_Side_Scrren.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.health_app.R;

import java.util.ArrayList;

public class Patient_View_Dispatch_Pharmacy_Order_Adapter extends RecyclerView.Adapter<Patient_View_Dispatch_Pharmacy_Order_Adapter.ViewHolder> {
    private Context context;
    private String data;
    private ArrayList<String> doc_appoinList;

    private String number[] = {"Telmekind AM Tab", "Crocin 100mg Tab"};

    public Patient_View_Dispatch_Pharmacy_Order_Adapter(Context context, ArrayList<String> data) {
        this.context = context;
        doc_appoinList = data;
        Log.e("data_adpater11111", "view_Seller" + data);
    }

    public Patient_View_Dispatch_Pharmacy_Order_Adapter(Context context, String data) {
        this.context = context;
        data = data;

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
    public Patient_View_Dispatch_Pharmacy_Order_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.single_row_patient_view_disptach_order, parent, false);
        return new Patient_View_Dispatch_Pharmacy_Order_Adapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Patient_View_Dispatch_Pharmacy_Order_Adapter.ViewHolder holder, final int position) {

        holder.medicineNameTV.setText(number[position]);


    }


    @Override
    public int getItemCount() {

        return number.length;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView medicineNameTV;


        public ViewHolder(View itemView) {
            super(itemView);
            medicineNameTV = (TextView) itemView.findViewById(R.id.medicineNameTV);



        }
    }


}



