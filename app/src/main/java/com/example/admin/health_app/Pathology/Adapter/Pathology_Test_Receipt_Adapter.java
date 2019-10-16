package com.example.admin.health_app.Pathology.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.admin.health_app.R;

import java.util.ArrayList;

/**
 * Created by Admin on 5/21/2018.
 */

public class Pathology_Test_Receipt_Adapter extends RecyclerView.Adapter<Pathology_Test_Receipt_Adapter.ViewHolder> {
    private Context context;
    private String[] data;

    public Pathology_Test_Receipt_Adapter(Context context, String[] data) {
        this.context = context;
        this.data = data;
        Log.e("data_adpater11111", "view_Seller" + data);
    }

    public Pathology_Test_Receipt_Adapter(Context context, String data) {
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
    public Pathology_Test_Receipt_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.single_row_pathology_test_receipt, parent, false);
        return new Pathology_Test_Receipt_Adapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Pathology_Test_Receipt_Adapter.ViewHolder holder, final int position) {
//        holder.patientNameTV.setText(doc_appoinList.get(position));

        holder.testTV.setText(data[position]);
    }


    @Override
    public int getItemCount() {

        return data.length;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView testTV;
        CardView card_view_click;

        public ViewHolder(View itemView) {
            super(itemView);
            card_view_click = (CardView) itemView.findViewById(R.id.card_view_click);
            testTV = (TextView) itemView.findViewById(R.id.testTV);

        }
    }


}

