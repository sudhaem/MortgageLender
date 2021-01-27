package com.mortgage.lender;

public class Lender {
    private double availableFunds;
    private double pendingFunds;

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

    public ApplicantLoanStatus qualifyLoans(Applicant applicant, double requestedAmount) {
        ApplicantLoanStatus applicantLoanStatus = new ApplicantLoanStatus();
        if((applicant.getDti() < 36) &&(applicant.getScore() > 620)) {
            if(applicant.getSavings() >= requestedAmount * 0.25) {
                applicantLoanStatus.setStatus("Qualified");
                applicantLoanStatus.setLoanAmount(requestedAmount);
                applicantLoanStatus.setQualification("Qualified");
            }
            else {
                applicantLoanStatus.setStatus("Qualified");
                applicantLoanStatus.setLoanAmount(applicant.getSavings() * 4);
                applicantLoanStatus.setQualification("Partially Qualified");
            }
        }
        else {
            applicantLoanStatus.setStatus("Denied");
            applicantLoanStatus.setLoanAmount(0);
            applicantLoanStatus.setQualification("Not Qualified");
        }


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
}
