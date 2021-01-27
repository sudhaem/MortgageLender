package com.mortgage.lender;

public class Applicant {
    private int dti;
    private int score;
    private double savings;
    private boolean decision;

    public Applicant(int dti, int score, double savings) {
        this.dti = dti;
        this.score = score;
        this.savings = savings;
    }

    public int getDti() {
        return dti;
    }

    public int getScore() {
        return score;
    }

    public double getSavings() {
        return savings;
    }

    public ApplicantLoanStatus applyLoan(double requestedAmount) {
        Loan loan = new Loan();
       return loan.applyLoan(this,requestedAmount);
    }


    public boolean getDecision() {
        return decision;
    }

    public void setDecision(boolean decision) {
        this.decision = decision;
    }
}
