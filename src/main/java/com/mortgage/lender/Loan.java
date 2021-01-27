package com.mortgage.lender;

public class Loan {
    public ApplicantLoanStatus applyLoan(Applicant applicant, double requestedAmount) {
        Lender lender = new Lender();

        ApplicantLoanStatus applicantLoanStatus = lender.qualifyLoans(applicant, requestedAmount);
        return applicantLoanStatus;
    }
}
