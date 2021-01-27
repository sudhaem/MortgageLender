package com.mortgage.lender;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MortgageLenderTest {
    Lender lender;
    @BeforeEach
    public  void setup() {
         lender = new Lender(100.00);
    }
  /**
   * As a lender, I want to be able to check my available funds, so that I know how much money I can offer for loans.
   *
   * When I check my available funds
   * Then I should see how much funds I currently have
   */

  @Test
  public void lender_Available_Funds() {

     assertEquals(100.00, lender.getAvailableFunds());
  }

    /**
     * As a lender, I want to add money to my available funds, so that I can offer loans to potential home buyers.
     *
     * Given I have <current_amount> available funds
     * When I add <deposit_amount>
     * Then my available funds should be <total>
     */
    @Test
    public void lender_Deposit_Funds() {
      assertEquals(100.00, lender.getAvailableFunds());
      lender.depositFunds(50.00);
      assertEquals(150.00, lender.getAvailableFunds());

    }

    /**
     * Rule: To qualify for the full amount, candidates must have debt-to-income (DTI) less than 36%, credit score above 620
     * and savings worth 25% of requested loan amount.
     *
     * Rule: To partially qualify, candidates must still meet the same dti and credit score thresholds.
     * The loan amount for partial qualified applications is four times the applicant's savings.
     *
     * Given a loan applicant with <dti>, <credit_score>, and <savings>
     * When they apply for a loan with <requested_amount>
     * Then their qualification is <qualification>
     * And their loan amount is <loan_amount>
     * And their loan status is <status>
     */
    @Test
    public void applicant_Loan_Qualification() {
        Loan loan = new Loan();
        Applicant applicant = new Applicant(21, 700,100000.00 );
        ApplicantLoanStatus applicantLoanStatus = applicant.applyLoan(250000);
        //ApplicantLoanStatus applicantLoanStatus = lender.qualifyLoans(applicant, 250000);
        assertEquals("Qualified", applicantLoanStatus.getQualification());
        assertEquals(250000 , applicantLoanStatus.getLoanAmount());
        assertEquals("Qualified", applicantLoanStatus.getStatus());

        Applicant applicant2 = new Applicant(37, 700,100000.00 );
        ApplicantLoanStatus applicantLoanStatus2 = applicant2.applyLoan(250000);
        assertEquals("Not Qualified", applicantLoanStatus2.getQualification());
        assertEquals(0 , applicantLoanStatus2.getLoanAmount());
        assertEquals("Denied", applicantLoanStatus2.getStatus());

        Applicant applicant3 = new Applicant(30, 600,100000.00 );
        ApplicantLoanStatus applicantLoanStatus3 = applicant3.applyLoan(250000);
        assertEquals("Not Qualified", applicantLoanStatus3.getQualification());
        assertEquals(0 , applicantLoanStatus3.getLoanAmount());
        assertEquals("Denied", applicantLoanStatus3.getStatus());

        Applicant applicant4 = new Applicant(30, 700,50000.00 );
        ApplicantLoanStatus applicantLoanStatus4 = applicant4.applyLoan(250000);
        assertEquals("Partially Qualified", applicantLoanStatus4.getQualification());
        assertEquals(200000 , applicantLoanStatus4.getLoanAmount());
        assertEquals("Qualified", applicantLoanStatus4.getStatus());


    }
}

