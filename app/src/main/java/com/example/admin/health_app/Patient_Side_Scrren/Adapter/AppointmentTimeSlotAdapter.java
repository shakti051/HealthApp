package com.example.admin.health_app.Patient_Side_Scrren.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.health_app.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Database on 8/3/2016.
 */
public class AppointmentTimeSlotAdapter extends RecyclerView.Adapter<AppointmentTimeSlotAdapter.ViewHolder> {
    public Context context;
    ViewHolder viewHolder;
    int selected_position = 0;
    OnTimeSlotClickListener mClickListener;
    ArrayList<String> listValue;
    String currentDateTimeString;
//    Context context;

    String[] timeSlotValue = {"11:30\nam  " ,"11:40\nam  " ,"11:50\nam", "12:00\npm", "12:10\npm", "12:20\npm"};
    public AppointmentTimeSlotAdapter(Context context)
    {
        this.context = context;
        mClickListener = (OnTimeSlotClickListener)context;
    }

    public AppointmentTimeSlotAdapter(ArrayList<String> listValue, Context context) {
        Log.e("Paked","list... "+listValue);
        this.listValue = listValue;
        this.context = context;
        mClickListener = (OnTimeSlotClickListener)context;

        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");

        currentDateTimeString = df.getTimeInstance().format(new Date());
        Log.e("CURRENTTIME","currenttime22222... "+currentDateTimeString);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder  {

        public TextView timeSlotTv;
        public LinearLayout timeSlotSingleItemLayout;
        public ViewHolder(View view) {
            super(view);
            timeSlotTv = (TextView) view.findViewById(R.id.timeSlotTv);
            timeSlotSingleItemLayout = (LinearLayout)view.findViewById(R.id.timeSlotSingleItemLayoutID);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {



        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_time_slot, null);
        viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position )
    {

        holder.timeSlotTv.setText(listValue.get(position));
        if(selected_position == position){
            holder.timeSlotTv.setBackgroundColor(ContextCompat.getColor(context, R.color.red));
            holder.timeSlotTv.setTextColor(Color.WHITE);
        }else{
            holder.timeSlotTv.setBackgroundColor(Color.TRANSPARENT);
            holder.timeSlotTv.setTextColor(Color.BLACK);

        }

        holder.timeSlotTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if(selected_position == position){

                    holder.timeSlotTv.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
                }else{
                    holder.timeSlotTv.setBackgroundColor(Color.TRANSPARENT);
                }*/

                notifyItemChanged(selected_position);
                selected_position = position;
                notifyItemChanged(selected_position);
                mClickListener.onClick(listValue.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listValue.size();
    }

    public interface OnTimeSlotClickListener
    {
        public void onClick(String time);
    }
}
