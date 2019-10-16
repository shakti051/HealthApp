package com.example.admin.health_app.Patient_Side_Scrren.Adapter;

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
 * Created by Admin on 6/5/2018.
 */

public class Patient_choose_Hospital_pick_adapter extends RecyclerView.Adapter<Patient_choose_Hospital_pick_adapter.ViewHolder> {
    private Context context;
    private ArrayList<String> doc_appoinList;
    private String[]name={"Max Hospital","Apollo Hospital"};
    private int[]images={R.drawable.max, R.drawable.apollo_image};
    private  OnclickListner_Adapter onclickListner_adapter;
    public Patient_choose_Hospital_pick_adapter(Context context, ArrayList<String> data) {
        this.context = context;
        doc_appoinList = data;
        Log.e("data_adpater11111", "view_Seller" + data);
    }

    public Patient_choose_Hospital_pick_adapter(Context context, String data) {
        this.context = context;
        data = data;
        onclickListner_adapter=(OnclickListner_Adapter)context;
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
    public Patient_choose_Hospital_pick_adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.single_row_choose_pathology, parent, false);
        return new Patient_choose_Hospital_pick_adapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Patient_choose_Hospital_pick_adapter.ViewHolder holder, final int position) {
//        holder.patientNameTV.setText(doc_appoinList.get(position));
        holder.profileIVID.setImageResource(images[position]);
        holder.LocatenameTV.setText(name[position]);
        holder.card_view_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onclickListner_adapter.onClick(position);
            }
        });
    }


    @Override
    public int getItemCount() {

        return name.length;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView LocatenameTV;
        private ImageView profileIVID;
        private CardView card_view_click;

        public ViewHolder(View itemView) {
            super(itemView);
            card_view_click=(CardView)itemView.findViewById(R.id.card_view_id1);
            profileIVID=(ImageView) itemView.findViewById(R.id.profileIVID);
            LocatenameTV=(TextView) itemView.findViewById(R.id.LocatenameTV);

        }
    }
    public interface OnclickListner_Adapter{
        public void onClick(int position);
    }

}

