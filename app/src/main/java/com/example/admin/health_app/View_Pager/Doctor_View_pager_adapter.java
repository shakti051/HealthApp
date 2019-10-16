package com.example.admin.health_app.View_Pager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.health_app.Model.Doctor_Detail_List_Model;
import com.example.admin.health_app.Patient_Side_Scrren.Follow_Up_NewCase.New_Case;
import com.example.admin.health_app.R;

import java.util.List;

/**
 * Created by Admin on 5/18/2018.
 */

public class Doctor_View_pager_adapter extends PagerAdapter {
    Context mContext;
    LayoutInflater mLayoutInflater;
    private String name;
    private List<Doctor_Detail_List_Model>doctorDetailLists;

    public Doctor_View_pager_adapter(Context context, int[] images_array,String name) {
        mContext = context;
        this.name = name;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public Doctor_View_pager_adapter(New_Case new_case, List<Doctor_Detail_List_Model> doctor_detail_listListModel) {
        mContext = new_case;
        doctorDetailLists= doctor_detail_listListModel;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return doctorDetailLists.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.single_doctor_view_pager, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.iamge_id);
        Glide.with(mContext)
                .load(doctorDetailLists.get(position).getDoc_Image_URl())
                .placeholder(mContext.getResources().getDrawable(R.drawable.doctor))
                .into(imageView);
        TextView name1 = (TextView) itemView.findViewById(R.id.doctorNmaeTVID);
        TextView genderTVID = (TextView) itemView.findViewById(R.id.genderTVID);
        TextView specialityTVFID = (TextView) itemView.findViewById(R.id.specialityTVFID);
        TextView locationTVID = (TextView) itemView.findViewById(R.id.locationTVID);
        TextView experience_TV = (TextView) itemView.findViewById(R.id.experience_TV);
        name1.setText(doctorDetailLists.get(position).getDocName());
        specialityTVFID.setText(doctorDetailLists.get(position).getDoc_Specialist());
        locationTVID.setText(doctorDetailLists.get(position).getDocAddress());
        experience_TV.setText(doctorDetailLists.get(position).getDoc_Experence()+"years");
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

}
