package com.guts.dto;

//import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;


public class LoanDTO {


    @NotEmpty(message = "Mobile number cannot be empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Invalid mobile number")
    private String mobileNumber;


    @NotEmpty(message = "Loan number cannot be empty")
    @Pattern(regexp = "(^$|[0-9]{12})", message = "Invalid loan number")
    private String loanNumber;


    @NotEmpty(message = "Loan type cannot be empty")
    private String loanType;


    @Positive(message = "Total loan cannot be negative")
    private int totalLoan;


    @Positive(message = "Amount paid cannot be negative")
    private int amountPaid;


    @Positive(message = "Outstanding amount cannot be negative")
    private int outstandingAmount;

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getLoanNumber() {
        return loanNumber;
    }

    public void setLoanNumber(String loanNumber) {
        this.loanNumber = loanNumber;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public int getTotalLoan() {
        return totalLoan;
    }

    public void setTotalLoan(int totalLoan) {
        this.totalLoan = totalLoan;
    }

    public int getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(int amountPaid) {
        this.amountPaid = amountPaid;
    }

    public int getOutstandingAmount() {
        return outstandingAmount;
    }

    public void setOutstandingAmount(int outstandingAmount) {
        this.outstandingAmount = outstandingAmount;
    }

    @Override
    public String toString() {
        return "LoanDTO{" +
                "mobileNumber='" + mobileNumber + '\'' +
                ", loanNumber=" + loanNumber +
                ", loanType='" + loanType + '\'' +
                ", totalLoan=" + totalLoan +
                ", amountPaid=" + amountPaid +
                ", outstandingAmount=" + outstandingAmount +
                '}';
    }
}
