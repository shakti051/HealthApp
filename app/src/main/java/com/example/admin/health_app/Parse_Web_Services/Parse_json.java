package com.example.admin.health_app.Parse_Web_Services;

import com.example.admin.health_app.Model.Doctor_Detail_List_Model;
import com.example.admin.health_app.Model.Patient_View_Appointment_And_Medical_Report_Model;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Parse_json {
    private List<Doctor_Detail_List_Model> doctorDetailLists = new ArrayList<>();
    private List<Patient_View_Appointment_And_Medical_Report_Model> viewAppointmentsList = new ArrayList<>();
    private List<Patient_View_Appointment_And_Medical_Report_Model> view_report_list = new ArrayList<>();
    private ArrayList<HashMap<String, String>> history_List = new ArrayList<>();

    public List<Doctor_Detail_List_Model> parse_Doc_Details(JSONArray jsonArray) {
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                Doctor_Detail_List_Model doctor_detail_listModel = new Doctor_Detail_List_Model();
                doctor_detail_listModel.setDocName(jsonArray.getJSONObject(i).getString("NAME"));
                doctor_detail_listModel.setDoc_Designation(jsonArray.getJSONObject(i).getString("DESIGNATION"));
                doctor_detail_listModel.setDoc_Experence(jsonArray.getJSONObject(i).getString("DOCTOREXPYRS"));
                doctor_detail_listModel.setDoc_Qualification(jsonArray.getJSONObject(i).getString("DOCTORQUALIFICATION"));
                doctor_detail_listModel.setDocfees(jsonArray.getJSONObject(i).getString("DOCTERFEE"));
                doctor_detail_listModel.setDoc_Pincode(jsonArray.getJSONObject(i).getString("PIN_CODE"));
                doctor_detail_listModel.setDocAddress(jsonArray.getJSONObject(i).getString("ADDRESS1"));
                doctor_detail_listModel.setDoc_Image_URl(jsonArray.getJSONObject(i).getString("IMAGE"));
                doctor_detail_listModel.setDoc_Id(jsonArray.getJSONObject(i).getString("USER_ID"));
                doctor_detail_listModel.setDoc_Lat(jsonArray.getJSONObject(i).getString("LATITUDE"));
                doctor_detail_listModel.setDoc_Long(jsonArray.getJSONObject(i).getString("LONGITUDE"));
                doctor_detail_listModel.setDoc_clinic_detail_id(jsonArray.getJSONObject(i).getString("CLINIC_DETAILS_KEY"));
                doctor_detail_listModel.setDoc_Specialist(jsonArray.getJSONObject(i).getString("CLINIC_SPECIALTY_KEY"));
                doctor_detail_listModel.setDoc_Gender(jsonArray.getJSONObject(i).getString("LONGITUDE"));
                doctorDetailLists.add(doctor_detail_listModel);
            }
        } catch (Exception e) {

        }
        return doctorDetailLists;
    }

    public ArrayList<HashMap<String, String>> parse_patient_view_History(JSONArray jsonArray) {
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("AP_ID", jsonArray.getJSONObject(i).getString("AP_ID"));
                hashMap.put("DOCTOR_CODE", jsonArray.getJSONObject(i).getString("DOCTOR_NAME"));
                hashMap.put("BOOKING_DATE", jsonArray.getJSONObject(i).getString("BOOKING_DATE"));
                hashMap.put("TIME_SLOT", jsonArray.getJSONObject(i).getString("TIME_SLOT"));
                hashMap.put("BOOK_STATUS", jsonArray.getJSONObject(i).getString("BOOK_STATUS"));
                hashMap.put("CURRENT_PROBLEM", jsonArray.getJSONObject(i).getString("CURRENT_PROBLEM"));
                hashMap.put("NO_OF_DAYS", jsonArray.getJSONObject(i).getString("NO_OF_DAYS"));
                hashMap.put("PATIENT_Name", jsonArray.getJSONObject(i).getString("PATIENT_NAME"));
                history_List.add(hashMap);
            }

        } catch (Exception e) {

        }

        return history_List;
    }

    public List<Patient_View_Appointment_And_Medical_Report_Model> parse_view_Appointment(JSONArray jsonArray) {
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                Patient_View_Appointment_And_Medical_Report_Model patient_view_appointment_AndMedicalReport_model = new Patient_View_Appointment_And_Medical_Report_Model();
                patient_view_appointment_AndMedicalReport_model.setAppointment_id(jsonArray.getJSONObject(i).getString("AP_ID"));
                patient_view_appointment_AndMedicalReport_model.setPatientName(jsonArray.getJSONObject(i).getString("PATIENT_ID"));
                patient_view_appointment_AndMedicalReport_model.setDoctorName(jsonArray.getJSONObject(i).getString("DOCTOR_CODE"));
                patient_view_appointment_AndMedicalReport_model.setBookingDate(jsonArray.getJSONObject(i).getString("BOOKING_DATE"));
                patient_view_appointment_AndMedicalReport_model.setBookingTime(jsonArray.getJSONObject(i).getString("TIME_SLOT"));
                patient_view_appointment_AndMedicalReport_model.setBooking_Satus(jsonArray.getJSONObject(i).getString("BOOK_STATUS"));
                viewAppointmentsList.add(patient_view_appointment_AndMedicalReport_model);
            }

        } catch (Exception e) {

        }
        return viewAppointmentsList;
    }

    public List<Patient_View_Appointment_And_Medical_Report_Model> parse_view_Report(JSONArray jsonArray) {
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                Patient_View_Appointment_And_Medical_Report_Model patient_view_appointment_AndMedicalReport_model = new Patient_View_Appointment_And_Medical_Report_Model();
                patient_view_appointment_AndMedicalReport_model.setPatientName_Rpeort(jsonArray.getJSONObject(i).getString("PAT_MED_MEMBER_KEY"));
                patient_view_appointment_AndMedicalReport_model.setView_report_Image(jsonArray.getJSONObject(i).getString("IMAGE"));
                patient_view_appointment_AndMedicalReport_model.setReport_Name(jsonArray.getJSONObject(i).getString("PAT_MED_NAME"));

                view_report_list.add(patient_view_appointment_AndMedicalReport_model);
            }

        } catch (Exception e) {

        }
        return view_report_list;
    }
}
