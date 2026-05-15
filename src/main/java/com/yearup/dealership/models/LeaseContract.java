package com.yearup.dealership.models;

public class LeaseContract extends Contract {

    final private double endingValueRate = 0.50;
    final private double leaseFeeRate = 0.07;



    //Constructor
    public LeaseContract(String dateOfContract,
                         String customerName,
                         String customerEmail,
                         Vehicle vehicleSold) {

        super(dateOfContract, customerName, customerEmail, vehicleSold);
    }

    //Getters
    public double getEndingValueRate() {
        return endingValueRate;
    }
    public double getLeaseFeeRate() {
        return leaseFeeRate;
    }



    //Calculating the endingValue based on the original price
    public double getEndingValue(){
        return getVehicleSold().getPrice() * endingValueRate;
    }
    //Calculating the lease fee based on the original price
    public double getLeaseFee(){
        return getVehicleSold().getPrice() * leaseFeeRate;
    }

    //Override methods
    @Override
    public double getTotalPrice() {
       double vehiclePrice = getVehicleSold().getPrice();
       return  vehiclePrice - getEndingValue() + getLeaseFee();
    }

    @Override
    public double getMonthlyPayment() {
        double leaseAmount = getTotalPrice();
        double monthlyInterest = 0.04 / 12;

        return (leaseAmount * monthlyInterest) / (1 - Math.pow(1 + monthlyInterest, - 36));


    }

}