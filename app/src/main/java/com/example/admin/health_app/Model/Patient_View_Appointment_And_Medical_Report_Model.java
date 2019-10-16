package com.example.admin.health_app.Model;

public class Patient_View_Appointment_And_Medical_Report_Model {
    private String patientName_Rpeort;
    private String view_report_Image;

    public String getPatientName_Rpeort() {
        return patientName_Rpeort;
    }

    public void setPatientName_Rpeort(String patientName_Rpeort) {
        this.patientName_Rpeort = patientName_Rpeort;
    }

    public String getView_report_Image() {
        return view_report_Image;
    }

    public void setView_report_Image(String view_report_Image) {
        this.view_report_Image = view_report_Image;
    }

    public String getReport_Name() {
        return report_Name;
    }

    public void setReport_Name(String report_Name) {
        this.report_Name = report_Name;
    }

    private String report_Name;
    public String getAppointment_id() {
        return Appointment_id;
    }

    public void setAppointment_id(String appointment_id) {
        Appointment_id = appointment_id;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(String bookingTime) {
        this.bookingTime = bookingTime;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDoctorName() {
        return DoctorName;
    }

    public void setDoctorName(String doctorName) {
        DoctorName = doctorName;
    }

    public String getBooking_Satus() {
        return booking_Satus;
    }

    public void setBooking_Satus(String booking_Satus) {
        this.booking_Satus = booking_Satus;
    }

    private String Appointment_id,bookingDate,bookingTime,patientName,DoctorName,booking_Satus;
}
