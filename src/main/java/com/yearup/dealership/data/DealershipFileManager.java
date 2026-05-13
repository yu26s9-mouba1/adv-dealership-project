package com.yearup.dealership.data;
import com.yearup.dealership.models.Dealership;
import com.yearup.dealership.models.Vehicle;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;





/**
 * This class manages and stores all the data(files)
 */
public class DealershipFileManager {

    private static final String PRODUCT_FILE = "data/inventory.csv";


    // Reads the inventory file, creates and add dealership and vehicle objects
    public Dealership getDealership() {
        Dealership dealership = null;

        try {

            BufferedReader br = new BufferedReader(new FileReader(PRODUCT_FILE));

            //Reads first line
            String line = br.readLine();

            if (line != null) {
                String[] dealershipInfo = line.split("\\|");

                String name = dealershipInfo[0];
                String address = dealershipInfo[1];
                String phone = dealershipInfo[2];

                dealership = new Dealership(name, address, phone);

            }


            //Reads remaining lines
            while ((line = br.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] vehicleInfo = line.split("\\|");

                int vin = Integer.parseInt(vehicleInfo[0]);
                int year = Integer.parseInt(vehicleInfo[1]);
                String make = vehicleInfo[2];
                String model = vehicleInfo[3];
                String vehicleType = vehicleInfo[4];
                String color = vehicleInfo[5];
                int odometer = Integer.parseInt(vehicleInfo[6]);
                double price = Double.parseDouble(vehicleInfo[7]);

                Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);

                dealership.addVehicle(vehicle);
            }

            br.close();

        } catch (IOException e) {
            System.out.println("Error reading file " + e.getMessage());

        }

        return dealership;

    }


    public void saveDealership(Dealership dealership) {

        try {
            // Create BufferedWriter to overwrite inventory.csv
            BufferedWriter bw = new BufferedWriter(new FileWriter(PRODUCT_FILE));

            // Write vehicle information first line\tittle:
            bw.write(
                    dealership.getName() + "|" +
                            dealership.getAddress() + "|" +
                            dealership.getPhone()
            );


            bw.newLine();

            //Loops through every vehicle in inventory
            for (Vehicle vehicle : dealership.getAllVehicles()) {

                // Writes each vehicle in pipe format
                bw.write(
                        vehicle.getVin() + "|" +
                                vehicle.getYear() + "|" +
                                vehicle.getMake() + "|" +
                                vehicle.getModel() + "|" +
                                vehicle.getVehicleType() + "|" +
                                vehicle.getColor() + "|" +
                                vehicle.getOdometer() + "|" +
                                vehicle.getPrice()
                );


                bw.newLine();
            }

            bw.close();

            System.out.println("Dealership saved successfully!");

        } catch (IOException e) {
            System.out.println("Error saving dealership: " + e.getMessage());
        }
    }


}






















