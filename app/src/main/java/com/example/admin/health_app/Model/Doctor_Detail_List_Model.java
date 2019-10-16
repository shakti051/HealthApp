package com.example.admin.health_app.Model;

import java.io.Serializable;

public class Doctor_Detail_List_Model implements Serializable {
    private String docName;
    private String doc_Specialist;
    private String doc_JobType;
    private String doc_Experence;
    private String doc_Pincode;
    private String docfees;
    private String docAddress;
    private String doc_Id,doc_Lat,doc_Long;
    private String doc_Designation;

    public String getDoc_clinic_detail_id() {
        return doc_clinic_detail_id;
    }

    public void setDoc_clinic_detail_id(String doc_clinic_detail_id) {
        this.doc_clinic_detail_id = doc_clinic_detail_id;
    }

    private String doc_clinic_detail_id;

    public String getDoc_Image_URl() {
        return doc_Image_URl;
    }

    public void setDoc_Image_URl(String doc_Image_URl) {
        this.doc_Image_URl = doc_Image_URl;
    }

    private String doc_Image_URl;


    public String getDoc_Gender() {
        return doc_Gender;
    }

    public void setDoc_Gender(String doc_Gender) {
        this.doc_Gender = doc_Gender;
    }

    private String doc_Gender;
    public String getDoc_Id() {
        return doc_Id;
    }

    public void setDoc_Id(String doc_Id) {
        this.doc_Id = doc_Id;
    }

    public String getDoc_Lat() {
        return doc_Lat;
    }

    public void setDoc_Lat(String doc_Lat) {
        this.doc_Lat = doc_Lat;
    }

    public String getDoc_Long() {
        return doc_Long;
    }

    public void setDoc_Long(String doc_Long) {
        this.doc_Long = doc_Long;
    }


    public String getDoc_Qualification() {
        return doc_Qualification;
    }

    public void setDoc_Qualification(String doc_Qualification) {
        this.doc_Qualification = doc_Qualification;
    }

    private String doc_Qualification;
    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDoc_Specialist() {
        return doc_Specialist;
    }

    public void setDoc_Specialist(String doc_Specialist) {
        this.doc_Specialist = doc_Specialist;
    }

    public String getDoc_JobType() {
        return doc_JobType;
    }

    public void setDoc_JobType(String doc_JobType) {
        this.doc_JobType = doc_JobType;
    }

    public String getDoc_Experence() {
        return doc_Experence;
    }

    public void setDoc_Experence(String doc_Experence) {
        this.doc_Experence = doc_Experence;
    }

    public String getDoc_Pincode() {
        return doc_Pincode;
    }

    public void setDoc_Pincode(String doc_Pincode) {
        this.doc_Pincode = doc_Pincode;
    }

    public String getDocfees() {
        return docfees;
    }

    public void setDocfees(String docfees) {
        this.docfees = docfees;
    }

    public String getDocAddress() {
        return docAddress;
    }

    public void setDocAddress(String docAddress) {
        this.docAddress = docAddress;
    }



    public String getDoc_Designation() {
        return doc_Designation;
    }

    public void setDoc_Designation(String doc_Designation) {
        this.doc_Designation = doc_Designation;
    }


}
