package com.yearup.dealership.models;

public class SalesContract extends Contract {

    private final double salesAmount = 0.05;
    private final double recordingFee = 100;
    private final boolean isFinance;


    public SalesContract(String dateOfContract, String customerName, String customerEmail, Vehicle vehicle, double totalPrice, double monthlyPayment, boolean isFinance) {
        super(dateOfContract, customerName, customerEmail, vehicle);
        this.isFinance = isFinance;
    }

//    , totalPrice, monthlyPayment

    public double getSalesAmount() {
        return salesAmount;
    }

    public double getRecordingFee() {
        return recordingFee;
    }

    public boolean isFinance() {
        return isFinance;
    }






    //Override methods
    @Override
    public double getTotalPrice() {
        double vehiclePrice =getVehicleSold().getPrice();
        double salesTax = vehiclePrice * salesAmount;


        //Calculating the processing fees based on contract type
        double processingFee;
            if(vehiclePrice < 1000) {
                processingFee = 295;
            } else
                processingFee = 495;

        return vehiclePrice + salesTax + recordingFee + processingFee;


    }

    @Override
    public double getMonthlyPayment() {
        //If not financing, no monthly fee
        if (!isFinance) {
            return 0;
        }

        //Total Amount Being Financed
        double loanAmount = getTotalPrice();
        double vehiclePrice =getVehicleSold().getPrice();

        double annualInterestRate;
        int loanTerm;

        //Loan Terms based on price
        if (vehiclePrice < 1000) {
            annualInterestRate = 0.0525;
            loanTerm = 24;
        } else {
            annualInterestRate = 0.0425;
            loanTerm = 48;
        }

        //Converting annual rate to monthly
        double monthlyInterestRate = annualInterestRate / 12;

        //Monthly payment
        return (loanAmount * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, loanTerm));

    }
}
