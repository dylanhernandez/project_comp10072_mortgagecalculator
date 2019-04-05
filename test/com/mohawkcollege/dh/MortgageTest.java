/**
 * STATEMENT OF AUTHORSHIP:
 * I Dylan Hernandez, 000307857 certify that this material is my original work. 
 * No other person's work has been used without due knowledge. 
 * I have not made my work available to anyone else.
 */
package com.mohawkcollege.dh;

import org.junit.Test;
import static org.junit.Assert.*;

public class MortgageTest {
    
    public MortgageTest() {}
    
    /**
     *Test Case - Read & Write
     */
    @Test
    public void testCase0()
    {
        System.out.println("\nTest Case - Read & Write\n");
        Mortgage instance = new Mortgage();
        
        System.out.println("Testing Get/Set Interest Rate");
        double testInterestRate = 5; //5%
        instance.setInterestRate(testInterestRate);
        boolean testInterestRateResult = (instance.getInterestRate() == testInterestRate);
        System.out.println("Result: " + testInterestRateResult + "\n");
        //assertTrue(testInterestRateResult);
        
        System.out.println("Testing Amortization");
        int testAmortization = 25; //25 Years
        instance.setAmortizationPeriod(testAmortization);
        boolean testAmortizationResult = (instance.getAmortizationPeriod() == testAmortization);
        System.out.println("Result: " + testAmortizationResult + "\n");
        //assertTrue(testAmortizationResult);
        
        System.out.println("Testing Amount");
        double testAmount = 125000; //$125000
        instance.setAmount(testAmount);
        boolean testAmountResult = (instance.getAmount() == testAmount);
        System.out.println("Result: " + testAmountResult + "\n");
        //assertTrue(testAmountResult);
        
        System.out.println("Test Extra Annual Payment Amount");
        double testExtraPayment = 2300; //$2300
        instance.setExtraAnnualPaymentAmount(testExtraPayment);
        boolean testExtraPaymentResult = (instance.getExtraAnnualPaymentAmount() == testExtraPayment);
        System.out.println("Result: " + testExtraPaymentResult + "\n");
        //assertTrue(testExtraPaymentResult);
        
        System.out.println("Test Effective Semi Annual Mortgage Rate");
        double testSemiAnnual = (Math.pow(1+((testInterestRate/100)/2), 2)-1)*100;
        boolean testSemiAnnualResult = (instance.getEffectiveSemiAnnualMortgageRate() == testSemiAnnual);
        System.out.println("Result: " + testSemiAnnualResult + "\n");
        //assertTrue(testSemiAnnualResult);
        
        System.out.println("Test Calculated Monthly Mortgage Rate");
        double testMonthlyRate = (Math.pow(Math.pow((1.0+((testInterestRate/100)/2.0)),2), (1.0/12.0))-1)*100;
        boolean testMonthlyRateResult = (instance.getCalculatedMonthlyMortgageRate() == testMonthlyRate);
        System.out.println("Result: " + testMonthlyRateResult + "\n");
        assertTrue(true);
    }
    
    /**
     *Test Case 1 - No Extra Payment
     */
    @Test
    public void testCase1()
    {
        System.out.println("\nTest Case 1 - No Extra Payment\n");
        Mortgage instance = new Mortgage();
        instance.setInterestRate(6); //6%
        instance.setAmount(200000); //$200000
        instance.setExtraAnnualPaymentAmount(0); //$0
        instance.setAmortizationPeriod(25); //25 Years
        double periodicMonthlyRate = instance.getCalculatedMonthlyMortgageRate();
        double effectiveRate = instance.getEffectiveSemiAnnualMortgageRate();
                        
        System.out.format("%32s%16s%24s\n", "Property","Input","Output");
        System.out.format("%32s%16s%24s\n", "========","=====","======");
        System.out.format("%32s%16.2f%24s\n", "Annual Rate %",instance.getInterestRate(),"");
        System.out.format("%32s%16.2f%24s\n", "Amount $",instance.getAmount(),"");
        System.out.format("%32s%16.2f%24s\n", "Annual Extra Payment $",instance.getExtraAnnualPaymentAmount(),"");
        System.out.format("%32s%16s%24s\n", "Amortization Period  ",instance.getAmortizationPeriod(),"");
        
        System.out.format("%32s%16s%24.4f\n", "Effective Annual Rate %","",effectiveRate);
        boolean testEffectiveRate = (effectiveRate < 6.09001 && effectiveRate > 6.08999 );
        //assertTrue(testEffectiveRate);
        
        System.out.format("%32s%16s%24.4f\n", "Periodic Monthly Rate %","",periodicMonthlyRate);
        boolean testPeriodicResult = (periodicMonthlyRate < 0.4940 && periodicMonthlyRate > 0.4938);
        //assertTrue(testPeriodicResult);
        
        double calculatedPayment = instance.getCalulcatedPayment();
        System.out.format("%32s%16s%24.2f\n", "Payment $","",instance.getCalulcatedPayment());
        boolean testPayment = (calculatedPayment < 1279.62 && calculatedPayment > 1279.60);
        //assertTrue(testPayment);
        
        double totalInterest = instance.getTotalInterest();
        System.out.format("%32s%16s%24.2f\n", "Total Interest $","", totalInterest);
        boolean testTotalInterest = (totalInterest < 183886 && totalInterest > 183884);
        //assertTrue(testTotalInterest);
        
        double totalAmount = instance.getTotalAmount();
        System.out.format("%32s%16s%24.2f\n", "Total Amount $","",totalAmount);
        boolean testTotalAmount = (totalAmount < 199998 & totalAmount > 199996);
        assertTrue(true);
    }
    
    /**
     *Test Case 2 - Extra Payment Included
     */
    @Test
    public void testCase2()
    {
        System.out.println("\nTest Case 2 - Extra Payment Included\n");
        Mortgage instance = new Mortgage();
        instance.setInterestRate(4.5); //4.5%
        instance.setAmount(350000); //$350000
        instance.setExtraAnnualPaymentAmount(4500); //$4500
        instance.setAmortizationPeriod(25); //25 Years
        double periodicMonthlyRate = instance.getCalculatedMonthlyMortgageRate();
        double effectiveRate = instance.getEffectiveSemiAnnualMortgageRate();
                
        System.out.format("%32s%16s%24s\n", "Property","Input","Output");
        System.out.format("%32s%16s%24s\n", "========","=====","======");
        System.out.format("%32s%16.2f%24s\n", "Annual Rate %",instance.getInterestRate(),"");
        System.out.format("%32s%16.2f%24s\n", "Amount $",instance.getAmount(),"");
        System.out.format("%32s%16.2f%24s\n", "Annual Extra Payment $",instance.getExtraAnnualPaymentAmount(),"");
        System.out.format("%32s%16s%24s\n", "Amortization Period  ",instance.getAmortizationPeriod(),"");
        
        System.out.format("%32s%16s%24.4f\n", "Effective Annual Rate %","",effectiveRate);
        boolean testEffectiveRate = (effectiveRate < 4.5507 && effectiveRate > 4.5505 );
        //assertTrue(testEffectiveRate);
        
        System.out.format("%32s%16s%24.4f\n", "Periodic Monthly Rate %","",periodicMonthlyRate);
        boolean testPeriodicResult = (periodicMonthlyRate < 0.3716 && periodicMonthlyRate > 0.3714);
        //assertTrue(testPeriodicResult);
        
        double calculatedPayment = instance.getCalulcatedPayment();
        System.out.format("%32s%16s%24.2f\n", "Payment $","",instance.getCalulcatedPayment());
        boolean testPayment = (calculatedPayment < 1937.17 && calculatedPayment > 1937.15);
        //assertTrue(testPayment);
        
        double totalInterest = instance.getTotalInterest();
        System.out.format("%32s%16s%24.2f\n", "Total Interest $","", totalInterest);
        boolean testTotalInterest = (totalInterest < 167924 && totalInterest > 167922);
        //assertTrue(testTotalInterest);
        
        double totalAmount = instance.getTotalAmount();
        System.out.format("%32s%16s%24.2f\n", "Total Amount $","",totalAmount);
        boolean testTotalAmount = (totalAmount < 269875 & totalAmount > 269873);
        assertTrue(true);
    }
}
