package com.mortgage.lender;

import java.text.SimpleDateFormat;

public class ApplicantLoanStatus {
    private double loanAmount;
    private String status;
    private String qualification;


    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public void setDate(String s) {

    }
}
