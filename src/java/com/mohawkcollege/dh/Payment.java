/**
 * STATEMENT OF AUTHORSHIP:
 * I Dylan Hernandez, 000307857 certify that this material is my original work. 
 * No other person's work has been used without due knowledge. 
 * I have not made my work available to anyone else.
 */
package com.mohawkcollege.dh;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * <pre>This is a normal Java class that holds information for a single mortage payment</pre>
 * @author Dylan Hernandez - 000307857
 */
public class Payment 
{
    private Calendar date;
    private double payment;
    private double extraAnnualPayment;
    private double principal;
    private double interest;
    private double balance;
    private String dateString;
    
    /**
     * Constructor
     * @param paramDate
     * @param paramPayment
     * @param paramExtraAnnualPayment
     * @param paramPrincipal
     * @param paramInterest
     * @param paramBalance
     */
    public Payment(Calendar paramDate,double paramPayment,double paramExtraAnnualPayment,double paramPrincipal,double paramInterest,double paramBalance)
    {
        date = paramDate;
        payment = paramPayment;
        extraAnnualPayment = paramExtraAnnualPayment;
        principal = paramPrincipal;
        interest = paramInterest;
        balance = paramBalance;
    }
    
    /**
     * Returns the date of the payment
     * @return date
     */
    public Calendar getDate()
    {        
        return date;
    }
    
    public String getDateString()
    {
        return dateString;
    }
    
    public void setDateString(String value)
    {
        dateString = value;
    }
        
    /**
     * Returns the payment amount
     * @return payment
     */
    public double getPayment()
    {
        return payment;
    }
    
    /**
     * Returns the extra annual payment, used in annual calculations
     * @return extraAnnualPayment
     */
    public double getExtraAnnualPayment()
    {
        return extraAnnualPayment;
    }
    
    /**
     * Returns the principal on the current payment
     * @return principal
     */
    public double getPrincipal()
    {
        return principal;
    }
    
    /**
     * Returns the interest on the current payment
     * @return interest
     */
    public double getInterest()
    {
        return interest;
    }
    
    /**
     * Returns the current balance of the mortgage
     * @return balance
     */
    public double getBalance()
    {
        return balance;
    }
}
