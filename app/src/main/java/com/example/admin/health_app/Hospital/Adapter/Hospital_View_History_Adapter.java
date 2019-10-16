package com.example.admin.health_app.Hospital.Adapter;

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
 * Created by Admin on 5/28/2018.
 */

public class Hospital_View_History_Adapter extends RecyclerView.Adapter<Hospital_View_History_Adapter.ViewHolder> {
    private Context context;
    private String data;
    private ArrayList<String> doc_appoinList;

    private int[] images_array = {R.drawable.person_image1, R.drawable.jamesperson, R.drawable.girl2};
    private String name[] = {"Ankit Saxena", "Prem Garg", "Neha Sharma"};
    private String referBY[] = {"Dr.Satish Yadav", "Dr.Ramesh Kumar", "Dr.Ritesh"};
    private String date[] = {"25", "27", "30"};

    public Hospital_View_History_Adapter(Context context, ArrayList<String> data) {
        this.context = context;
        doc_appoinList = data;
        Log.e("data_adpater11111", "view_Seller" + data);
    }

    public Hospital_View_History_Adapter(Context context, String data) {
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
    public Hospital_View_History_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.single_row_patient_status, parent, false);
        return new Hospital_View_History_Adapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Hospital_View_History_Adapter.ViewHolder holder, final int position) {
//        holder.patientNameTV.setText(doc_appoinList.get(position));
        Integer pos = position;
        holder.patientnameTV.setText(name[position]);
        holder.doctorNameTV.setText(referBY[position]);
        holder.dateTV.setText(date[position]);


    }


    @Override
    public int getItemCount() {

        return name.length;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView patientnameTV, doctorNameTV, dateTV, symptomTV, appointDateTV;
        private ImageView profileIV, delieverIV;
        private CardView card_view_click;

        public ViewHolder(View itemView) {
            super(itemView);
            patientnameTV=(TextView)itemView.findViewById(R.id.patientname_id);
            doctorNameTV=(TextView)itemView.findViewById(R.id.doctorName_id);
            dateTV=(TextView)itemView.findViewById(R.id.dateTV);

        }
    }

}




