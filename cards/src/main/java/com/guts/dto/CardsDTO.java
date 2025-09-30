package com.guts.dto;

import jakarta.validation.constraints.*;
//import lombok.Data;

//@Data
public class CardsDTO {

    @NotEmpty(message = "Mobile Number is required")
    @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile Number must be 10 digits")
    private String mobileNumber;

    @NotEmpty(message = "Card Number is required")
    @Pattern(regexp="(^$|[0-9]{12})",message = "Card Number must be 12 digits")
    private String cardNumber;

    @NotEmpty(message = "Card Type is required")
    private String cardType;

    @Min(value = 1000, message = "Total Limit should be at least 1000")
    private int totalLimit;

    @PositiveOrZero(message = "Amount Used should be positive or zero")
    private int amountUsed;

    @PositiveOrZero(message = "Amount Used should be positive or zero")
    private int availableLimit;

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public int getTotalLimit() {
        return totalLimit;
    }

    public void setTotalLimit(int totalLimit) {
        this.totalLimit = totalLimit;
    }

    public int getAmountUsed() {
        return amountUsed;
    }

    public void setAmountUsed(int amountUsed) {
        this.amountUsed = amountUsed;
    }

    public int getAvailableLimit() {
        return availableLimit;
    }

    public void setAvailableLimit(int availableLimit) {
        this.availableLimit = availableLimit;
    }

    @Override
    public String toString() {
        return "CardsDTO{" +
                "mobileNumber='" + mobileNumber + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", cardType='" + cardType + '\'' +
                ", totalLimit=" + totalLimit +
                ", amountUsed=" + amountUsed +
                ", availableLimit=" + availableLimit +
                '}';
    }
}
