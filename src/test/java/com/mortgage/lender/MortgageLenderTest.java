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
}

