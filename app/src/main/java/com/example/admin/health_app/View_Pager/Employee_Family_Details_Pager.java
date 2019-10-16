package com.example.admin.health_app.View_Pager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.health_app.Model.Employe_Family_Details_Model;
import com.example.admin.health_app.Patient_Side_Scrren.Activity.Patient_Home_Screen;
import com.example.admin.health_app.R;

import java.util.List;

/**
 * Created by Aarna on 7/10/2017.
 */

public class Employee_Family_Details_Pager extends PagerAdapter {

    private int[] images_array = {R.drawable.person_image1, R.drawable.jamesperson, R.drawable.girl_image, R.drawable.girl2};
    private List<Employe_Family_Details_Model> employe_family_details;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public Employee_Family_Details_Pager(Context context, int[] images_array) {
        mContext = context;
        this.images_array = images_array;
    }

    public Employee_Family_Details_Pager(Patient_Home_Screen context, List<Employe_Family_Details_Model> employe_family_detailsList) {
        mContext = context;
        employe_family_details = employe_family_detailsList;

        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return images_array.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.image_swiper, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.iamge_id);
        TextView name = (TextView) itemView.findViewById(R.id.relationNameTV);
        TextView genderTV = (TextView) itemView.findViewById(R.id.genderTV);
        TextView relationTV = (TextView) itemView.findViewById(R.id.relationTV);
        TextView DOB_TV = (TextView) itemView.findViewById(R.id.DOB_TV);
        imageView.setImageResource(images_array[position]);
        name.setText(employe_family_details.get(position).getName());
        genderTV.setText(employe_family_details.get(position).getGender());
        relationTV.setText(employe_family_details.get(position).getRelation_name());
        DOB_TV.setText(employe_family_details.get(position).getDate_of_Birth());

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

}
