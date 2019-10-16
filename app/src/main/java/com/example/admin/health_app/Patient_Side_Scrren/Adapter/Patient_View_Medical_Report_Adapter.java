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

import com.example.admin.health_app.Model.Patient_View_Appointment_And_Medical_Report_Model;
import com.example.admin.health_app.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Patient_View_Medical_Report_Adapter extends RecyclerView.Adapter<Patient_View_Medical_Report_Adapter.ViewHolder> {
    private Context context;
    OnClickListerner_Adapter onClickListerner_adapter;
    private List<Patient_View_Appointment_And_Medical_Report_Model> view_medical_report_List;

    public Patient_View_Medical_Report_Adapter(Context context, List<Patient_View_Appointment_And_Medical_Report_Model> data) {
        this.context = context;
        view_medical_report_List = data;
        onClickListerner_adapter = (OnClickListerner_Adapter) context;
        Log.e("data_adpater11111", "view_Seller" + data);
    }


    @Override
    public Patient_View_Medical_Report_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.single_row_view_medical_report, parent, false);
        return new Patient_View_Medical_Report_Adapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Patient_View_Medical_Report_Adapter.ViewHolder holder, final int position) {
        holder.patientnameTV.setText(view_medical_report_List.get(position).getPatientName_Rpeort());
        holder.reportNameTV.setText(view_medical_report_List.get(position).getReport_Name());
        holder.view_report_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListerner_adapter.oOnclickdata(view_medical_report_List.get(position).getView_report_Image());
            }
        });


    }


    @Override
    public int getItemCount() {

        return view_medical_report_List.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView patientnameTV, reportNameTV, view_report_TV;

        public ViewHolder(View itemView) {
            super(itemView);
            patientnameTV = (TextView) itemView.findViewById(R.id.patientNameTV_id);
            reportNameTV = (TextView) itemView.findViewById(R.id.reportNameTV);
            view_report_TV = (TextView) itemView.findViewById(R.id.view_report_TV);

        }
    }

    public interface OnClickListerner_Adapter {
        public void oOnclickdata(String position);
    }

}



