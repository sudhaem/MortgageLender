package com.mortgage.lender;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Lender {
    private double availableFunds;
    private double pendingFunds;
    private Map<String, ApplicantLoanStatus> allLoans = new HashMap();

    public Lender(double availableFunds) {
        this.availableFunds = availableFunds;
    }
    public Lender() {}
    public double getAvailableFunds() {
        return this.availableFunds;
    }

    public void depositFunds(double amount) {
        this.availableFunds+= amount;
    }

    public ApplicantLoanStatus qualifyLoans(Loan loan) {
        ApplicantLoanStatus applicantLoanStatus = new ApplicantLoanStatus();
        if((loan.getApplicant().getDti() < 36) &&(loan.getApplicant().getScore() > 620)) {
            if(loan.getApplicant().getSavings() >= loan.getRequestedAmount() * 0.25) {
                applicantLoanStatus.setStatus("Qualified");
                applicantLoanStatus.setLoanAmount(loan.getRequestedAmount());
                applicantLoanStatus.setQualification("Qualified");
            }
            else {
                applicantLoanStatus.setStatus("Qualified");
                applicantLoanStatus.setLoanAmount(loan.getApplicant().getSavings() * 4);
                applicantLoanStatus.setQualification("Partially Qualified");
            }
        }
        else {
            applicantLoanStatus.setStatus("Denied");
            applicantLoanStatus.setLoanAmount(0);
            applicantLoanStatus.setQualification("Not Qualified");
        }

        allLoans.put(applicantLoanStatus.getStatus(), applicantLoanStatus);
        return applicantLoanStatus;
    }

    public ApplicantLoanStatus processLoan(ApplicantLoanStatus applicantLoanStatus) {
        if(applicantLoanStatus.getStatus().equals("Qualified")) {
            if(availableFunds < applicantLoanStatus.getLoanAmount()) {
                applicantLoanStatus.setStatus("On Hold");
            }
            else {
                applicantLoanStatus.setStatus("Approved");
                this.availableFunds -= applicantLoanStatus.getLoanAmount();
                this.pendingFunds +=applicantLoanStatus.getLoanAmount();
                
            }
        }
        else {
            throw new RuntimeException("Do not proceed");
        }
        return applicantLoanStatus;
    }

    public double getPendingFunds() {
        return this.pendingFunds;
    }

    public void processResponseForApprovedLoan(boolean decision, ApplicantLoanStatus applicantLoanStatus) {
        if(decision){
            this.pendingFunds-=applicantLoanStatus.getLoanAmount();
            applicantLoanStatus.setStatus("accepted");
        }else
        {
            this.availableFunds+=applicantLoanStatus.getLoanAmount();
            this.pendingFunds-=applicantLoanStatus.getLoanAmount();
            applicantLoanStatus.setStatus("rejected");
        }
    }

    public Map<String, ApplicantLoanStatus> getLoans() {
        return allLoans;
    }

    public void checkExpiredLoans(ApplicantLoanStatus loanStatus) {
    }
}
