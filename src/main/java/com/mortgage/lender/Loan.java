package com.mortgage.lender;

public class Loan {
    private double requestedAmount;
    private Applicant applicant;

    public Loan(double requestedAmount, Applicant applicant) {
        this.requestedAmount = requestedAmount;
        this.applicant = applicant;
    }

    public double getRequestedAmount() {
        return requestedAmount;
    }

    public void setRequestedAmount(double requestedAmount) {
        this.requestedAmount = requestedAmount;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

//    public ApplicantLoanStatus applyLoan(Applicant applicant, double requestedAmount) {
//        Lender lender = new Lender();
//
//        ApplicantLoanStatus applicantLoanStatus = lender.qualifyLoans(applicant, requestedAmount);
//        return applicantLoanStatus;
//    }
}
