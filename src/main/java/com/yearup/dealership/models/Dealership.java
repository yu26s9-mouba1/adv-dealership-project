package com.yearup.dealership.models;
import java.util.ArrayList;


/**
 * Dealership class to store all dealership infos
 */
public class Dealership {

    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory;


    //Constructor
    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>();
    }


    //Getters
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }


    // Calling vehicles from the arraylist
    public ArrayList<Vehicle> getAllVehicles() {
        return inventory;
    }

    //Calling vehicles by make or model
    public ArrayList<Vehicle> getVehiclesByMakeModel(String make, String model) {
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getMake().equalsIgnoreCase(make) && vehicle.getModel().equalsIgnoreCase(model)) {
                vehicles.add(vehicle);

            }
        }
        return vehicles;
    }


   //Calling vehicles by price
    public ArrayList<Vehicle> getVehiclesByPrice(double min, double max) {
        ArrayList<Vehicle> matchingVehicles = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getPrice() >= min && vehicle.getPrice() <= max) {
                matchingVehicles.add(vehicle);
            }
        }

        return matchingVehicles;

    }


    //Calling vehicles by year
    public ArrayList<Vehicle> getVehiclesByYear(int minYear, int maxYear) {
        ArrayList<Vehicle> matchingVehicles = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getYear() >= minYear && vehicle.getYear() <= maxYear) {
                matchingVehicles.add(vehicle);

            }
        }
        return matchingVehicles;
    }


    //Calling vehicles by Color
    public ArrayList<Vehicle> getVehiclesByColor(String color) {
        ArrayList<Vehicle> matchingVehicles = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getColor().equalsIgnoreCase(color)) {
                matchingVehicles.add(vehicle);
            }
        }
        return matchingVehicles;
    }


    //Calling vehicles by Odometer
    public ArrayList<Vehicle> getVehiclesByOdometer(int minOdometer, int maxOdometer) {
        ArrayList<Vehicle> matchingVehicles = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getOdometer() >= minOdometer){
                matchingVehicles.add(vehicle);
            }


        }
        return matchingVehicles;
    }


    //Calling vehicles by Type
    public ArrayList<Vehicle> getVehiclesByType(String type) {
        ArrayList<Vehicle> matchingVehicles = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if(vehicle.getVehicleType().equalsIgnoreCase(type)){
                matchingVehicles.add(vehicle);
            }
        }
        return matchingVehicles;
    }

    //Get Vehicle By Vin number
    public Vehicle getVehicleByVin(int vin) {
        for (Vehicle vehicle : inventory) {
            if (vehicle.getVin() == vin) {
                return vehicle;
            }
        }
        return null;
    }

    //Adds vehicles to the arrayList
    public void addVehicle(Vehicle vehicle) {
        inventory.add(vehicle);
    }

    // Removes vehicles
    public void removeVehicle(Vehicle vehicle) {
        inventory.remove(vehicle);
    }



}
