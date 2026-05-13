package com.yearup.dealership.models;

public class SalesContract {

    private int salesTaxAmount;
    private double recordingFee;
    private double processingFee;
    private boolean isFinance;
    private double monthlyPayment;


    public SalesContract(int salesTaxAmount, double recordingFee, double processingFee, boolean isFinance, double monthlyPayment) {
        this.salesTaxAmount = salesTaxAmount;
        this.recordingFee = recordingFee;
        this.processingFee = processingFee;
        this.isFinance = isFinance;
        this.monthlyPayment = monthlyPayment;
        
    }
}
