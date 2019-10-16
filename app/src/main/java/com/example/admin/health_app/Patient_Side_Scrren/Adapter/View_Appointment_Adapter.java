package com.example.admin.health_app.Patient_Side_Scrren.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.health_app.Common_package.Dialog_popup;
import com.example.admin.health_app.Model.Patient_View_Appointment_And_Medical_Report_Model;
import com.example.admin.health_app.R;

import java.util.List;

/**
 * Created by Admin on 5/23/2018.
 */


public class View_Appointment_Adapter extends RecyclerView.Adapter<View_Appointment_Adapter.ViewHolder> {
    private Context context;
    private String data;
    private List<Patient_View_Appointment_And_Medical_Report_Model> view_appointment_modelList;
    private String name[] = {"Satish Yadav", "Dr Ritesh Sharma", "Dr Neha"};

    public View_Appointment_Adapter(Context context, List<Patient_View_Appointment_And_Medical_Report_Model> data) {
        this.context = context;
        view_appointment_modelList = data;
        Log.e("data_adpater11111", "view_Seller" + data);
    }

    public View_Appointment_Adapter(Context context, String data) {
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
    public View_Appointment_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.single_row_consult_history, parent, false);
        return new View_Appointment_Adapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(View_Appointment_Adapter.ViewHolder holder, final int position) {
//        holder.patientNameTV.setText(doc_appoinList.get(position));


        holder.patientnameTV.setText(view_appointment_modelList.get(position).getPatientName());
        holder.doctorName_TV.setText(view_appointment_modelList.get(position).getDoctorName());
        holder.appointment_Time_TV.setText(view_appointment_modelList.get(position).getBookingTime());
        holder.appointment_date_TV.setText(view_appointment_modelList.get(position).getBookingDate());
        holder.appointmentID_TV.setText(view_appointment_modelList.get(position).getAppointment_id());
        holder.cancelBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog_popup dialog_popup = new Dialog_popup(context, "");
                dialog_popup.setCanceledOnTouchOutside(false);
                dialog_popup.show();
            }
        });
        if (view_appointment_modelList.get(position).getBooking_Satus().equals("Y")) {
            holder.booking_status_TV.setText("Pending");
        }


    }


    @Override
    public int getItemCount() {

        return view_appointment_modelList.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView patientnameTV, cancelBTN, appointmentID_TV, doctorName_TV, appointment_Time_TV,
                appointment_date_TV, booking_status_TV;

        public ViewHolder(View itemView) {
            super(itemView);

            appointmentID_TV = (TextView) itemView.findViewById(R.id.appointmentID_TV);
            appointment_Time_TV = (TextView) itemView.findViewById(R.id.appointment_Time_TV);
            patientnameTV = (TextView) itemView.findViewById(R.id.patientnameTV);
            doctorName_TV = (TextView) itemView.findViewById(R.id.doctorName_TV);
            appointment_date_TV = (TextView) itemView.findViewById(R.id.appointment_date_TV);
            booking_status_TV = (TextView) itemView.findViewById(R.id.booking_status_TV);
            cancelBTN = (TextView) itemView.findViewById(R.id.cancelBTN);

        }
    }


}



