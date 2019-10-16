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

import com.example.admin.health_app.Patient_Side_Scrren.Activity.Follow_Up_Book_Appointment;
import com.example.admin.health_app.Patient_Side_Scrren.Follow_Up_NewCase.Follow_Up_List;
import com.example.admin.health_app.R;

import java.util.ArrayList;

/**
 * Created by Admin on 5/23/2018.
 */


public class Follow_Up_Adapter extends RecyclerView.Adapter<Follow_Up_Adapter.ViewHolder> {
    private Context context;
    private ArrayList<String> doc_appoinList;


    public Follow_Up_Adapter(Context context, ArrayList<String> data) {
        this.context = context;
        doc_appoinList = data;
        Log.e("data_adpater11111", "view_Seller" + data);
    }

    public Follow_Up_Adapter(Context context, String data) {
        this.context = context;
        data = data;
        Log.e("data_adpater11111", "view_Seller" + data);
    }

    public Follow_Up_Adapter(Follow_Up_List follow_up_list) {
        context=follow_up_list;
    }

//    public Doctor_view_appointment_adapter(Context context, ArrayList<HashMap<String, String>> pat_appointmentList) {
//        this.context = context;
//        doc_appoinList = pat_appointmentList;
//
//        clickListener = (ClickListener) context;
//
//    }

    @Override
    public Follow_Up_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.single_row_follow_up, parent, false);
        return new Follow_Up_Adapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Follow_Up_Adapter.ViewHolder holder, final int position) {


holder.bookTV.setText("Book");
holder.bookTV.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        context.startActivity(new Intent(context,Follow_Up_Book_Appointment.class));
    }
});
    }


    @Override
    public int getItemCount() {

        return 1;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView bookTV;


        public ViewHolder(View itemView) {
            super(itemView);
            bookTV=(TextView)itemView.findViewById(R.id.bookTV);
        }
    }



}


