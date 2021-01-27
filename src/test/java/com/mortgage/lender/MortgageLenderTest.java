package com.mortgage.lender;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    /**
     * As a lender, I want to only approve loans when I have available funds, so that I don't go bankrupt.
     *
     * Given I have <available_funds> in available funds
     * When I process a qualified loan
     * Then the loan status is set to <status>
     *     When I process a not qualified loan
     * Then I should see a warning to not proceed
     */
    @Test
    public void processLoan() {
        Lender lender = new Lender(100000);
        assertEquals(100000, lender.getAvailableFunds());
        Applicant applicant = new Applicant(21, 700,100000.00 );
        //Applicant applies loan and lender qualifies loan
        ApplicantLoanStatus applicantLoanStatus = applicant.applyLoan(125000);
        ApplicantLoanStatus loanStatus = lender.processLoan(applicantLoanStatus);
        assertEquals("On Hold", loanStatus.getStatus());

        Lender lender2 = new Lender(125000);
        assertEquals(125000, lender2.getAvailableFunds());
        Applicant applicant2 = new Applicant(21, 700,100000.00 );
        //Applicant applies loan and lender qualifies loan
        ApplicantLoanStatus applicantLoanStatus2 = applicant2.applyLoan(125000);
        ApplicantLoanStatus loanStatus2 = lender2.processLoan(applicantLoanStatus2);
        assertEquals("Approved", loanStatus2.getStatus());

        Lender lender3 = new Lender(125000);
        assertEquals(125000, lender3.getAvailableFunds());
        Applicant applicant3 = new Applicant(39, 700,100000.00 );
        //Applicant applies loan and lender qualifies loan
        ApplicantLoanStatus applicantLoanStatus3 = applicant3.applyLoan(125000);

        RuntimeException exception = assertThrows(RuntimeException.class ,()->lender3.processLoan(applicantLoanStatus3));
        assertEquals("Do not proceed", exception.getMessage());



    }

}

