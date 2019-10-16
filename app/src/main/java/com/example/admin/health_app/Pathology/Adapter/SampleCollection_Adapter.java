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

import com.example.admin.health_app.Pathology.Activity.SampleCollection;
import com.example.admin.health_app.R;

import java.util.ArrayList;

public class SampleCollection_Adapter extends RecyclerView.Adapter<SampleCollection_Adapter.ViewHolder> {
    private Context context;
    private String data;
    OnClickListerner_Adapter onClickListerner_adapter;
    private ArrayList<String> doc_appoinList, appointmentid;
    ArrayList<String> filteredList = new ArrayList<>();

    public SampleCollection_Adapter(Context context, ArrayList<String> staticAppointmentId, ArrayList<String> data) {
        this.context = context;
        doc_appoinList = data;
        appointmentid = staticAppointmentId;
        onClickListerner_adapter = (OnClickListerner_Adapter) context;
        Log.e("data_adpater11111", "view_Seller" + doc_appoinList);
    }

    public SampleCollection_Adapter(Context context, String data) {
        this.context = context;
        data = data;
        onClickListerner_adapter = (OnClickListerner_Adapter) context;

        Log.e("data_adpater11111", "view_Seller" + data);
    }

    public SampleCollection_Adapter(SampleCollection sampleCollection) {
        context = sampleCollection;
    }

    //    public Doctor_view_appointment_adapter(Context context, ArrayList<HashMap<String, String>> pat_appointmentList) {
//        this.context = context;
//        doc_appoinList = pat_appointmentList;
//
//        clickListener = (ClickListener) context;
//
//    }
    public void updateList(ArrayList<String> carModelPosition, String name) {

        doc_appoinList = carModelPosition;
        notifyDataSetChanged();
    }

    @Override
    public SampleCollection_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.single_row_sample_collection, parent, false);
        return new SampleCollection_Adapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SampleCollection_Adapter.ViewHolder holder, final int position) {
        holder.patientNameTV_id.setText(doc_appoinList.get(position));
        holder.appointmentID.setText(appointmentid.get(position));

        holder.card_view_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListerner_adapter.oOnclickdata(position);
            }
        });
    }


    @Override
    public int getItemCount() {

        return doc_appoinList.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView patientNameTV_id, appointmentID;
        CardView card_view_click;

        public ViewHolder(View itemView) {
            super(itemView);
            card_view_click = (CardView) itemView.findViewById(R.id.card_view_click);
            patientNameTV_id = (TextView) itemView.findViewById(R.id.patientNameTV_id);
            appointmentID = (TextView) itemView.findViewById(R.id.appointmentID);

        }
    }

    public interface OnClickListerner_Adapter {
        public void oOnclickdata(int position);
    }

}


