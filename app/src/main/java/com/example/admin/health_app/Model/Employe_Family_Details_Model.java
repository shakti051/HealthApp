package com.example.admin.health_app.Model;

import java.io.Serializable;

public class Employe_Family_Details_Model implements Serializable {
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    private String gender;
    public String getRelation_key() {
        return relation_key;
    }

    public void setRelation_key(String relation_key) {
        this.relation_key = relation_key;
    }

    public String getRelation_name() {
        return relation_name;
    }

    public void setRelation_name(String relation_name) {
        this.relation_name = relation_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMember_key() {
        return member_key;
    }

    public void setMember_key(String member_key) {
        this.member_key = member_key;
    }

    public String getDate_of_Birth() {
        return date_of_Birth;
    }

    public void setDate_of_Birth(String date_of_Birth) {
        this.date_of_Birth = date_of_Birth;
    }

    public String getAadhaarNo() {
        return aadhaarNo;
    }

    public void setAadhaarNo(String aadhaarNo) {
        this.aadhaarNo = aadhaarNo;
    }

    public String getPan_Number() {
        return pan_Number;
    }

    public void setPan_Number(String pan_Number) {
        this.pan_Number = pan_Number;
    }

    private String relation_key, relation_name, name, member_key, date_of_Birth, aadhaarNo, pan_Number;
}
