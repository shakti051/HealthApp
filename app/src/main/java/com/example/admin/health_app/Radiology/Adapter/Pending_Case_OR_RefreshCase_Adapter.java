package com.example.admin.health_app.Radiology.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.admin.health_app.Pathology.Activity.Cancel_by_pathology;
import com.example.admin.health_app.Pathology.Activity.Pending_Case_OR_refresh_case;
import com.example.admin.health_app.R;

import java.util.ArrayList;

public class Pending_Case_OR_RefreshCase_Adapter extends RecyclerView.Adapter<Pending_Case_OR_RefreshCase_Adapter.ViewHolder> {
    private Context context;
    private String[] data;
    private ArrayList<String> doc_appoinList;

    public Pending_Case_OR_RefreshCase_Adapter(Context context, String[] data) {
        this.context = context;
        this.data = data;
        Log.e("data_adpater11111", "view_Seller" + data);
    }

    public Pending_Case_OR_RefreshCase_Adapter(Context context, String data) {
        this.context = context;
        data = data;
        Log.e("data_adpater11111", "view_Seller" + data);
    }

    public Pending_Case_OR_RefreshCase_Adapter(Pending_Case_OR_refresh_case pending_case_or_refresh_case) {
        context = pending_case_or_refresh_case;
    }

//    public Doctor_view_appointment_adapter(Context context, ArrayList<HashMap<String, String>> pat_appointmentList) {
//        this.context = context;
//        doc_appoinList = pat_appointmentList;
//
//        clickListener = (ClickListener) context;
//
//    }

    @Override
    public Pending_Case_OR_RefreshCase_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.single_row_pending_case, parent, false);
        return new Pending_Case_OR_RefreshCase_Adapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Pending_Case_OR_RefreshCase_Adapter.ViewHolder holder, final int position) {
//        holder.patientNameTV.setText(doc_appoinList.get(position));

//        holder.testTV.setText(data[position]);
        holder.CancelTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, Cancel_by_pathology.class));
            }
        });

    }


    @Override
    public int getItemCount() {

        return 1;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView CancelTV, emp_tv, bookingDateTV, symptomTV, appointDateTV;
        Button notAvilableBTN, confirmBTN, rejectBTN;
        CardView card_view_click;

        public ViewHolder(View itemView) {
            super(itemView);
            card_view_click = (CardView) itemView.findViewById(R.id.card_view_click);
            CancelTV = (TextView) itemView.findViewById(R.id.CancelTV);

        }
    }


}

