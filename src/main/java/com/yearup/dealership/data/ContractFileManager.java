package com.yearup.dealership.data;
import com.yearup.dealership.models.Contract;
import com.yearup.dealership.models.LeaseContract;
import com.yearup.dealership.models.SalesContract;
import com.yearup.dealership.models.Vehicle;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileWriter;


public class ContractFileManager {

    private static final String CONTRACTS_FILE = "data/contracts.csv";


    public void saveContract(Contract contract) {

        try {

            FileWriter file = new FileWriter(CONTRACTS_FILE, true);
            BufferedWriter bw = new BufferedWriter(file);
            Vehicle vehicle = contract.getVehicleSold();


            if (contract instanceof SalesContract){
                SalesContract sales = (SalesContract) contract;

                bw.write(
                        "SALE|" + contract.getDateOfContract() +
                                "|" + contract.getCustomerName() +
                                "|" + contract.getCustomerEmail() +
                                "|" + vehicle.getVin() + "|" + vehicle.getYear() +
                                "|" + vehicle.getMake() + "|" + vehicle.getModel() +
                                "|" + vehicle.getVehicleType() + "|" + vehicle.getColor() +
                                "|" + vehicle.getOdometer() + "|" + vehicle.getPrice() +
                                "|" + sales.getSalesAmount() + "|" + sales.getRecordingFee() +
                                "|" + sales.getProcessingFee() + "|" + sales.getTotalPrice() +
                                "|" + sales.isFinance() + "|" + "|" + sales.getMonthlyPayment()
                );
                bw.newLine();

            } else if (contract instanceof LeaseContract){
                LeaseContract lease = (LeaseContract) contract;

                bw.write(
                        "LEASE|" + contract.getDateOfContract() +
                                "|" + contract.getCustomerName() +
                                "|" + contract.getCustomerEmail() +
                                "|" + vehicle.getVin() + "|" + vehicle.getYear() +
                                "|" + vehicle.getMake() + "|" + vehicle.getModel() +
                                "|" + vehicle.getVehicleType() + "|" + vehicle.getColor() +
                                "|" + vehicle.getOdometer() + "|" + vehicle.getPrice() +
                                "|" + lease.getEndingValue() + "|" + lease.getLeaseFee() +
                                "|" + lease.getTotalPrice() +  "|" + lease.getMonthlyPayment()
                );



            }
            bw.close();

        } catch (IOException e) {
            System.out.println("Error saving contract" + e.getMessage());


        }


    }



}
