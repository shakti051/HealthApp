package com.example.admin.health_app.Pathology.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.health_app.R;

import java.util.ArrayList;
import java.util.HashMap;

public class Pathology_upload_documents_Adapter extends RecyclerView.Adapter<Pathology_upload_documents_Adapter.ViewHolder> {
    private Context context;
    String[] data;
    ArrayList<HashMap<String, String>> hospitaVisitlist = new ArrayList<>();
    private String test[]={"Blood Test","X-Ray"};

    public Pathology_upload_documents_Adapter(Context context) {
        this.context = context;
        Log.e("data_adpater11111", "view_Seller" + data);
    }

    public Pathology_upload_documents_Adapter(Context context, String[] data) {
        this.context = context;
        this.data = data;
        Log.e("data_adpater11111", "view_Seller" + data);
    }

    @Override
    public Pathology_upload_documents_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.single_row_upload_document_by_pathology, parent, false);
        return new Pathology_upload_documents_Adapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Pathology_upload_documents_Adapter.ViewHolder holder, int position) {
        holder.testNameTV.setText(test[position]);

    }


    @Override
    public int getItemCount() {
        return test.length;

    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView testNameTV;

        public ViewHolder(View itemView) {
            super(itemView);
            testNameTV=(TextView)itemView.findViewById(R.id.testNameTV);
        }
    }

}


