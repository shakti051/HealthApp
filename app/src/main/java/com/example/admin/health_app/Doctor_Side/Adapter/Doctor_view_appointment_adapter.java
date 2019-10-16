package com.example.admin.health_app.Doctor_Side.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.admin.health_app.Common_package.OTPAutoVerificationActivity;
import com.example.admin.health_app.Doctor_Side.Activity.Doctor_Generate_Prescription;
import com.example.admin.health_app.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by AshuRajput on 2/6/2018.
 */

public class Doctor_view_appointment_adapter extends RecyclerView.Adapter<Doctor_view_appointment_adapter.ViewHolder> {
    private Context context;
    private ClickListener clickListener;
    private ArrayList<String> doc_appoinList;
    private ArrayList<String> patientTimeList;

    private int[] images_array = {R.drawable.person_image1, R.drawable.jamesperson, R.drawable.peson1, R.drawable.girl2};

    public Doctor_view_appointment_adapter(Context context, ArrayList<String> data, ArrayList<String> patientTimeList) {
        this.context = context;
        doc_appoinList = data;
        this.patientTimeList = patientTimeList;
        clickListener = (ClickListener) context;
        Log.e("data_adpater11111", "view_Seller" + data + patientTimeList);
    }

//    public Doctor_view_appointment_adapter(Context context, ArrayList<HashMap<String, String>> pat_appointmentList) {
//        this.context = context;
//        doc_appoinList = pat_appointmentList;
//
//        clickListener = (ClickListener) context;
//
//    }

    @Override
    public Doctor_view_appointment_adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.single_row_doctor_appointment, parent, false);
        return new Doctor_view_appointment_adapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Doctor_view_appointment_adapter.ViewHolder holder, final int position) {
        Integer pos = position;

        holder.patientNameTV.setText(doc_appoinList.get(position));
        holder.time.setText(patientTimeList.get(position));
        holder.doctor.setImageResource(images_array[position]);
//        if (pos == 1 ) {
//            holder.videoCallIV.setImageResource(R.drawable.doctor_icon);
//        } else {
//            holder.videoCallIV.setImageResource(R.drawable.video_call);
//        }
//        holder.card_view_click.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                clickListener.getDataClick(position);
//            }
//        });
        holder.proceedTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, Doctor_Generate_Prescription.class));
            }
        });
        holder.verifyTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, OTPAutoVerificationActivity.class));
            }
        });

    }


    @Override
    public int getItemCount() {

        return doc_appoinList.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView patientNameTV, time, proceedTV, verifyTV;
        CardView card_view_click;
        ImageView doctor;

        public ViewHolder(View itemView) {
            super(itemView);
            patientNameTV = (TextView) itemView.findViewById(R.id.patientNameTV_id);


            card_view_click = (CardView) itemView.findViewById(R.id.card_view_click);
            doctor = (ImageView) itemView.findViewById(R.id.patientIV);
            time = (TextView) itemView.findViewById(R.id.time);
            verifyTV = (TextView) itemView.findViewById(R.id.verifyTV);
            proceedTV = (TextView) itemView.findViewById(R.id.proceedTV);

        }
    }

    public interface ClickListener {
        public void getDataClick(int position);
    }

}


