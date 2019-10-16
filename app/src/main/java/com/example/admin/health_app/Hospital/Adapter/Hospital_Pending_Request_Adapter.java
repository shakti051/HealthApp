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

import com.example.admin.health_app.Pharmacy.Adapter.Order_Adapter;
import com.example.admin.health_app.R;

import java.util.ArrayList;

/**
 * Created by Admin on 5/28/2018.
 */

public class Hospital_Pending_Request_Adapter extends RecyclerView.Adapter<Hospital_Pending_Request_Adapter.ViewHolder> {
    private Context context;
    private String data;
   OnClickListerner_Adapter onClickListerner_adapte;
    private ArrayList<String> doc_appoinList;

    private int[] images_array = {R.drawable.person_image1, R.drawable.jamesperson, R.drawable.girl2};
    private String name[] = {"Ankit Saxena", "Prem Garg", "Neha Sharma"};
    private String referBY[] = {"Dr.Satish Yadav", "Dr.Ramesh Kumar", "Dr.Ritesh"};

    public Hospital_Pending_Request_Adapter(Context context, ArrayList<String> data) {
        this.context = context;
        doc_appoinList = data;
        onClickListerner_adapte = (OnClickListerner_Adapter) context;
        Log.e("data_adpater11111", "view_Seller" + data);
    }

    public Hospital_Pending_Request_Adapter(Context context, String data) {
        this.context = context;
        this.data = data;
        onClickListerner_adapte = (OnClickListerner_Adapter) context;

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
    public Hospital_Pending_Request_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.single_row_hospital_pending_request, parent, false);
        return new Hospital_Pending_Request_Adapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Hospital_Pending_Request_Adapter.ViewHolder holder, final int position) {
//        holder.patientNameTV.setText(doc_appoinList.get(position));
        Integer pos = position;
        holder.card_view_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListerner_adapte.oOnclickdata(position);
            }
        });
        holder.patientnameTV.setText(name[position]);
        holder.referNameTV.setText(referBY[position]);

    }


    @Override
    public int getItemCount() {

        return images_array.length;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView patientnameTV, referNameTV, emergencyCall_TV, symptomTV, appointDateTV;
        private ImageView profileIV, delieverIV;
        private CardView card_view_click;

        public ViewHolder(View itemView) {
            super(itemView);
            card_view_click = (CardView) itemView.findViewById(R.id.card_view_click);

            referNameTV = (TextView) itemView.findViewById(R.id.referNameTV);
            profileIV = (ImageView) itemView.findViewById(R.id.profileIV);
            delieverIV = (ImageView) itemView.findViewById(R.id.delieverIV);
            patientnameTV = (TextView) itemView.findViewById(R.id.patientnameTV);

        }
    }

    public interface OnClickListerner_Adapter {
        public void oOnclickdata(int position);
    }

}




