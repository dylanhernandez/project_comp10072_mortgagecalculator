/**
 * STATEMENT OF AUTHORSHIP:
 * I Dylan Hernandez, 000307857 certify that this material is my original work. 
 * No other person's work has been used without due knowledge. 
 * I have not made my work available to anyone else.
 */
package com.mohawkcollege.dh;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 *<pre>This is a Javabean class that will calculate the the interest and payments of a mortgage that will be used on a web page</pre>
 * @author Dylan Hernandez - 000307857
 */
@Named(value = "mortgage")
@RequestScoped
public class Mortgage {
    
    @NotNull
    private double interestRate;
    
    @Min(5)
    @Max(30)
    @NotNull
    private int amortizationPeriod;
    
    @Min(10000)
    @Max(1000000)
    @NotNull
    private double amount;
    private double extraAnnualPaymentAmount;
    private double effectiveSemiAnnualMortgageRate;
    private double calculatedMonthlyMortgageRate;
    private ArrayList<Payment> payments;
    
    /**
     * Constructor
     */
    public Mortgage() { //Defaults
        setInitializeValues();
    }
    
    /**
     * Sets the core values for mortgage payments
     * This method is called either in the constructor or as a reset
     */
    public void setInitializeValues()
    {
        interestRate = 6.0;
        amount = 200000;
        amortizationPeriod = 25; 
        extraAnnualPaymentAmount = 0;
    }
    
    /**
     * Returns the interest rate based on the property value
     * @return interestRate
     */
    public double getInterestRate() {
        return interestRate;
    }

    /**
     * Sets the property value for the interest rate
     * @param interestRate
     */
    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    /**
     * Returns the amortization years based on the property value
     * @return amortizationPeriod
     */
    public int getAmortizationPeriod() {
        return amortizationPeriod;
    }

    /**
     * Sets the property value for the amortization period in years
     * @param amortizationPeriod
     */
    public void setAmortizationPeriod(int amortizationPeriod) {
        this.amortizationPeriod = amortizationPeriod;
    }

    /**
     * Returns the balance amount based on the property value
     * @return amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Sets the property value for the amount of the mortgage starting balance
     * @param amount
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Returns the extra payment amount based on the property value
     * @return extraAnnualPaymentAmount
     */
    public double getExtraAnnualPaymentAmount() {
        return extraAnnualPaymentAmount;
    }

    /**
     * Sets the property value for the extra annual payment amount
     * @param extraAnnualPaymentAmount
     */
    public void setExtraAnnualPaymentAmount(double extraAnnualPaymentAmount) {
        this.extraAnnualPaymentAmount = extraAnnualPaymentAmount;
    }

    /**
     * Returns the calculated monthly mortgage rate based on the semi-annual rate
     * @return calculatedMonthlyMortgageRate
     */
    public double getCalculatedMonthlyMortgageRate() {
        calculatedMonthlyMortgageRate = (Math.pow(Math.pow((1.0+((interestRate/100)/2.0)),2), (1.0/12.0))-1)*100;
        return calculatedMonthlyMortgageRate;
    }

    /**
     * Returns the annual mortgage rate based on the semi-annual compounding
     * @return effectiveSemiAnnualMortgageRate
     */
    public double getEffectiveSemiAnnualMortgageRate() {
        effectiveSemiAnnualMortgageRate = (Math.pow(1+((interestRate/100)/2), 2)-1)*100;
        return effectiveSemiAnnualMortgageRate;
    }

    /**
     * Returns the calculated monthly payment
     * @return paymentAmount
     */
    public double getCalulcatedPayment()
    {
        double periodicMonthlyRate = this.getCalculatedMonthlyMortgageRate();
        double paymentAmount = ((this.getAmount() * periodicMonthlyRate)/100)/(1-Math.pow(1+(periodicMonthlyRate/100),-(this.getAmortizationPeriod()*12)));
        return paymentAmount;
    }
    
    /*public ArrayList<Payment> getPayments()
    {
        this.payments = this.getPayments();
        return this.payments;
    }*/
    
    /**
     * Returns the total amount of interest paid
     * @return totalInterestPaid
     */
    public double getTotalInterest()
    {
        this.payments = this.getPayments();
        if(this.payments != null)
        {
            double totalInterestPaid = 0;
            for(Payment p : this.payments)
            {
                totalInterestPaid += p.getInterest();
            }
            return totalInterestPaid;
        }
        return 0;
    }
    
    /**
     * Returns the total amount of principal that was paid
     * @return totalAmountPaid
     */
    public double getTotalAmount()
    {
        this.payments = this.getPayments();
        if(this.payments != null)
        {
            double totalAmountPaid = 0;
            for(Payment p : this.payments)
            {
                totalAmountPaid += p.getPrincipal();
            }
            return totalAmountPaid;
        }
        return 0;
    }
    
    /**
     * Constructs an enumerable object list containing all payments for mortgage
     * @return returnList
     */
    public ArrayList<Payment> getPayments()
    {
        ArrayList<Payment> returnList = new ArrayList<>();
        Calendar paymentDate = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("MMM 01, yyyy");
        int amortizationMonths = this.amortizationPeriod;
        double paymentBalance = this.amount;
        double periodicMonthlyRate = this.getCalculatedMonthlyMortgageRate();
        double paymentAmount = this.getCalulcatedPayment();
        double paymentAmountRounded = (double) Math.round(paymentAmount * 100) / 100;
        double paymentInterest = 0.0;
        double paymentExtra = 0;
        double paymentPrincipal = 0.0;
        
        Payment newPayment = new Payment(paymentDate,paymentAmountRounded,paymentExtra,paymentPrincipal,paymentInterest,paymentBalance);
        for(int x = 0; x < (amortizationMonths*12); x++)
        {
            if(newPayment.getBalance() <= 0)
            {
                continue;
            }
            paymentDate = newPayment.getDate();
            int currentMonth = paymentDate.get(Calendar.MONTH);
            int currentYear = paymentDate.get(Calendar.YEAR);
            if(currentMonth >= 11)
            {
                currentMonth = 0;
                currentYear++;
                paymentDate.set(Calendar.YEAR, currentYear);
                paymentDate.set(Calendar.MONTH, currentMonth);
            }
            else
            {
                currentMonth++;
                paymentDate.set(Calendar.MONTH, currentMonth);
            }
            paymentAmount = newPayment.getPayment();
            paymentExtra = (currentMonth == 8) ? this.extraAnnualPaymentAmount : 0;
            paymentInterest = (periodicMonthlyRate/100) * newPayment.getBalance();
            paymentPrincipal = paymentAmount - paymentInterest;
            paymentBalance = newPayment.getBalance() - paymentPrincipal;
            if(currentMonth == 8)
            {
               paymentBalance = paymentBalance - paymentExtra;
            }
            newPayment.setDateString(formatter.format(paymentDate.getTime()));
            newPayment = new Payment(paymentDate,paymentAmount,paymentExtra,paymentPrincipal,paymentInterest,paymentBalance);
            returnList.add(newPayment);
        }
        return returnList;
    }
}
