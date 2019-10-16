package com.example.admin.health_app.Radiology.Adapter;

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

public class View_History_Adapter extends RecyclerView.Adapter<View_History_Adapter.ViewHolder> {
    private Context context;
    private String data;
    OnClickListerner_Adapter onClickListerner_adapter;
    private ArrayList<String> doc_appoinList,appointmentId;
    public View_History_Adapter(Context context, ArrayList<String> staticAppointmentId, ArrayList<String> data) {
        this.context = context;
        doc_appoinList = data;
        appointmentId=staticAppointmentId;
        onClickListerner_adapter = (OnClickListerner_Adapter) context;
        Log.e("data_adpater11111", "view_Seller" + data);
    }

    public View_History_Adapter(Context context, String data) {
        this.context = context;
        data = data;
        onClickListerner_adapter = (OnClickListerner_Adapter) context;

        Log.e("data_adpater11111", "view_Seller" + data);
    }
    public void updateList(ArrayList<String> carModelPosition, String name) {

        doc_appoinList = carModelPosition;
        notifyDataSetChanged();
    }
//    public Doctor_view_appointment_adapter(Context context, ArrayList<HashMap<String, String>> pat_appointmentList) {
//        this.context = context;
//        doc_appoinList = pat_appointmentList;
//
//        clickListener = (ClickListener) context;
//
//    }

    @Override
    public View_History_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.single_row_pathology_view_history, parent, false);
        return new View_History_Adapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(View_History_Adapter.ViewHolder holder, final int position) {
        holder.patientnameTV.setText(doc_appoinList.get(position));
        holder.appointmentID_TV.setText(appointmentId.get(position));


    }


    @Override
    public int getItemCount() {

        return doc_appoinList.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView patientnameTV, appointmentID_TV, emergencyCall_TV, symptomTV, appointDateTV;
        private ImageView profileIVID;
        private CardView card_view_click;

        public ViewHolder(View itemView) {
            super(itemView);
            patientnameTV = (TextView) itemView.findViewById(R.id.patientnameTV);
            appointmentID_TV = (TextView) itemView.findViewById(R.id.appointmentID_TV);


        }
    }

    public interface OnClickListerner_Adapter {
        public void oOnclickdata(String position);
    }

}




