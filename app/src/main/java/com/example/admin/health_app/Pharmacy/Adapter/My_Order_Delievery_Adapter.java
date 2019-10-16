package com.example.admin.health_app.Pharmacy.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.health_app.R;

import java.util.ArrayList;

/**
 * Created by Admin on 5/24/2018.
 */


public class My_Order_Delievery_Adapter extends RecyclerView.Adapter<My_Order_Delievery_Adapter.ViewHolder> {
    private Context context;
    private String data;
    private ArrayList<String> doc_appoinList;
    private String number[] = {"Telmekind AM Tab", "Crocin 100mg Tab"};

    public My_Order_Delievery_Adapter(Context context, ArrayList<String> data) {
        this.context = context;
        doc_appoinList = data;
        Log.e("data_adpater11111", "view_Seller" + data);
    }

    public My_Order_Delievery_Adapter(Context context, String data) {
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
    public My_Order_Delievery_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.single_row_my_order_delievery_medicine, parent, false);
        return new My_Order_Delievery_Adapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(My_Order_Delievery_Adapter.ViewHolder holder, final int position) {

        holder.medicineNameTV.setText(number[position]);

    }


    @Override
    public int getItemCount() {

        return number.length;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView medicineNameTV, dayTV, emergencyCall_TV, symptomTV, appointDateTV;
        private ImageView profileIVID;
        private CardView card_view_click;

        public ViewHolder(View itemView) {
            super(itemView);
            card_view_click = (CardView) itemView.findViewById(R.id.card_view_id1);
            medicineNameTV = (TextView) itemView.findViewById(R.id.medicineNameTV);


        }
    }


}


