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
import java.util.HashMap;

public class Consultation_History_Adapter extends RecyclerView.Adapter<Consultation_History_Adapter.ViewHolder> {
    private Context context;
    OnClickListerner_Adapter onClickListerner_adapter;
    private ArrayList<HashMap<String,String>>historyList;


    public Consultation_History_Adapter(Context context, ArrayList<HashMap<String, String>> data) {
        this.context = context;
        historyList = data;
        onClickListerner_adapter = (OnClickListerner_Adapter) context;
        Log.e("data_adpater11111", "view_Seller" + data);
    }

    public Consultation_History_Adapter(Context context, String data) {
        this.context = context;
        data = data;
        onClickListerner_adapter = (OnClickListerner_Adapter) context;

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
    public Consultation_History_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.single_row_emergency_services, parent, false);
        return new Consultation_History_Adapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Consultation_History_Adapter.ViewHolder holder, final int position) {
        holder.patientnameTV.setText(historyList.get(position).get("PATIENT_Name"));
        holder.appointmentID_TV.setText(historyList.get(position).get("AP_ID"));
        holder.bookingDate_TV.setText(historyList.get(position).get("BOOKING_DATE"));
        holder.time_TV.setText(historyList.get(position).get("TIME_SLOT"));
        if(historyList.get(position).get("BOOK_STATUS").equals("H")){

            holder.status_TV.setText("Meeting Done");
        }
       else if(historyList.get(position).get("BOOK_STATUS").equals("N")){

            holder.status_TV.setText("Pending");
        }
        else if(historyList.get(position).get("BOOK_STATUS").equals("R")){

            holder.status_TV.setText("Rejected");
        }
        else if(historyList.get(position).get("BOOK_STATUS").equals("S")){

            holder.status_TV.setText("Rescheduled by Doctor");
        }

    }


    @Override
    public int getItemCount() {

        return historyList.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView patientnameTV, appointmentID_TV, bookingDate_TV, time_TV, status_TV;

        public ViewHolder(View itemView) {
            super(itemView);
            patientnameTV=(TextView)itemView.findViewById(R.id.patientnameTV);
            appointmentID_TV=(TextView)itemView.findViewById(R.id.appointmentID_TV);
            bookingDate_TV=(TextView)itemView.findViewById(R.id.bookingDate_TV);
            time_TV=(TextView)itemView.findViewById(R.id.time_TV);
            status_TV=(TextView)itemView.findViewById(R.id.status_TV);

        }
    }

    public interface OnClickListerner_Adapter {
        public void oOnclickdata(String position);
    }

}



