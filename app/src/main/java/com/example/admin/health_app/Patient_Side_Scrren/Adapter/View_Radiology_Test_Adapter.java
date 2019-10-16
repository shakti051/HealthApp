package com.example.admin.health_app.Patient_Side_Scrren.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.health_app.R;

import java.util.ArrayList;

public class View_Radiology_Test_Adapter extends RecyclerView.Adapter<View_Radiology_Test_Adapter.ViewHolder> {
    private Context context;
    private String data;
    private ArrayList<String> doc_appoinList;

    private String number[] = {"UltraSound Test", "X-Ray"};

    public View_Radiology_Test_Adapter(Context context, ArrayList<String> data) {
        this.context = context;
        doc_appoinList = data;
        Log.e("data_adpater11111", "view_Seller" + data);
    }

    public View_Radiology_Test_Adapter(Context context, String data) {
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
    public View_Radiology_Test_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.single_row_pick_up_or_self, parent, false);
        return new View_Radiology_Test_Adapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(View_Radiology_Test_Adapter.ViewHolder holder, final int position) {

        holder.checkBoxValue.setText(number[position]);


    }


    @Override
    public int getItemCount() {

        return number.length;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CheckBox checkBoxValue;

        public ViewHolder(View itemView) {
            super(itemView);
            checkBoxValue = (CheckBox) itemView.findViewById(R.id.checkBoxValue);

        }
    }


}


