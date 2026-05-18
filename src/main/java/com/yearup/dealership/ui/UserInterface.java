package com.yearup.dealership.ui;
import com.yearup.dealership.data.DealershipFileManager;
import com.yearup.dealership.data.ContractFileManager;
import com.yearup.dealership.models.Contract;
import com.yearup.dealership.models.Dealership;
import com.yearup.dealership.models.SalesContract;
import com.yearup.dealership.models.Vehicle;
import com.yearup.dealership.models.LeaseContract;

import java.util.ArrayList;

public class UserInterface {
    private Dealership dealership;


    public UserInterface() {

    }

    private void processContract() {

        int vin = Console.promptForInt("Please enter the vin");

        //Find vehicle by Vin
        Vehicle vehicle = dealership.getVehicleByVin(vin);

        if  (vehicle == null) {
            System.out.println("Vehicle Not Found");
            return;
        }

        System.out.println("1- Sale");
        System.out.println("2- Lease");

        int choice = Console.promptForInt("Choose Contract Type: ");

        //Prompting for customer info
        String date = Console.promptForString("Enter date: ");
        String name = Console.promptForString("Enter name: ");
        String email = Console.promptForString("Enter email: ");

        Contract contract = null;

        if (choice == 1) {
            boolean isFinance = Console.promptForString("Finance? Yes/No: ").equalsIgnoreCase("Yes");

            contract = new SalesContract(date, name, email, vehicle, isFinance);
        } else if (choice == 2) {
            contract = new LeaseContract(date, name, email, vehicle);

        }else{
            System.out.println("Invalid Choice");
            return;
        }

       //Saving the contract to the dealership data/file
        ContractFileManager contractFileManager = new ContractFileManager();
        contractFileManager.saveContract(contract);

        dealership.removeVehicle(vehicle);

        DealershipFileManager dealershipFileManager = new DealershipFileManager();
        dealershipFileManager.saveDealership(dealership);

        System.out.println("Contract saved successfully.");

    }





    /**
     * Loads files
     */
    private void init() {
        DealershipFileManager fileManager = new DealershipFileManager();
        this.dealership = fileManager.getDealership();

    }


    /**
     * Displays the main menu
     */
    public void display() {
        init();

        String commamd;
        do {
            String menu = """
                    1 - Find vehicles within price range
                    2 - Find vehicles by make/model
                    3 - Find vehicles by year range
                    4 - Find vehicles by color
                    5 - Find vehicles by mileage range
                    6 - Find vehicles by type
                    7 - List all vehicles
                    8 - Add vehicle
                    9 - Remove vehicle
                    10- Sell/Lease vehicle
                    99 - Quit
                    
                    
                    
                    """;

            System.out.println(menu);
            commamd = Console.promptForString("Enter Command: ").trim().toUpperCase();

            switch (commamd) {
                case "1":
                    processGetByPriceRequest();
                    break;
                case "2":
                    processGetByMakeModelRequest();
                    break;
                case "3":
                    processGetByYearRequest();
                    break;
                case "4":
                    processGetByColorRequest();
                    break;
                case "5":
                    processGetByOdometerRequest();
                    break;
                case "6":
                    processGetByVehicleTypeRequest();
                    break;
                case "7":
                    processAllVehiclesRequest();
                    break;
                case "8":
                    processAddVehicleRequest();
                    break;
                case "9":
                    processRemoveVehicleRequest();
                    break;
                case "10":
                    processContract();
                    break;

                case "99":
                    System.out.println("Goodbye!");
                    ;
                    break;
                default:
                    System.out.println("Invalid Command!");
                    break;

            }

        } while (!commamd.equals("99"));

    }


    /**
     *
     * @param displays all vehicles from thr dealership
     */
    private void displayVehicles(ArrayList<Vehicle> vehicles) {

        System.out.println("Vehicles:");
        if (vehicles == null || vehicles.isEmpty()) {
            System.out.println("No vehicles found!");
            return;
        }
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle);
        }


    }


    /**
     * Gets all vehicles from dealership
     */

    private void processAllVehiclesRequest() {
        ArrayList<Vehicle> vehicles = dealership.getAllVehicles();
        displayVehicles(vehicles);


    }


    /**
     * Gets all vehicles within the price range
     */

    private void processGetByPriceRequest() {

        double min = Console.promptForDouble("Enter minimum Price: ");
        double max = Console.promptForDouble("Enter maximum Price: ");

        ArrayList<Vehicle> vehicles = dealership.getVehiclesByPrice(min, max);

        displayVehicles(vehicles);
    }


    /**
     * Gets vehicles by their Make or Model ad display it
     */
    private void processGetByMakeModelRequest() {

        String make = Console.promptForString("Enter make: ");
        String model = Console.promptForString("Enter model: ");

        ArrayList<Vehicle> vehicles = dealership.getVehiclesByMakeModel(make, model);
        displayVehicles(vehicles);


    }


    /**
     * Gets vehicle by year as requested
     */
    private void processGetByYearRequest() {

        int minYear = Console.promptForInt("Enter min Year: ");
        int maxYear = Console.promptForInt("Enter max Year: ");
        ArrayList<Vehicle> vehicles = dealership.getVehiclesByYear(minYear, maxYear);
        displayVehicles(vehicles);

    }


    /**
     * Gets vehicle their color as requested
     */
    private void processGetByColorRequest() {

        String color = Console.promptForString("Enter color: ");
        ArrayList<Vehicle> vehicles = dealership.getVehiclesByColor(color);
        displayVehicles(vehicles);


    }


    /**
     * Gets vehicles by their odometer number
     */
    private void processGetByOdometerRequest() {

        int minOdometer = Console.promptForInt("Enter minimum Odometer: ");
        int maxOdometer = Console.promptForInt("Enter max Odometer: ");
        ArrayList<Vehicle> vehicles = dealership.getVehiclesByOdometer(minOdometer, maxOdometer);
        displayVehicles(vehicles);

    }


    /**
     * Gets vehicle by their types
     */
    private void processGetByVehicleTypeRequest() {

        String vehicleType = Console.promptForString("Enter Vehicle type: ");
        ArrayList<Vehicle> vehicles = dealership.getVehiclesByType(vehicleType);
        displayVehicles(vehicles);


    }


    /**
     * Adds vehicle to the dealership
     */
    private void processAddVehicleRequest() {

        int vin = Console.promptForInt("Enter Vin: ");
        int year = Console.promptForInt("Enter Year: ");
        String make = Console.promptForString("Enter make: ");
        String model = Console.promptForString("Enter model: ");
        String vehicleType = Console.promptForString("Enter Vehicle type: ");
        String color = Console.promptForString("Enter color: ");
        int odometer = Console.promptForInt("Enter Odometer: ");
        double price = Console.promptForDouble("Enter price: ");


        Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
        dealership.addVehicle(vehicle);
        System.out.println("Vehicle added successfully!");

        //Saves new added vehicle to the file
        DealershipFileManager fileManager = new DealershipFileManager();
        fileManager.saveDealership(dealership);


    }


    /**
     * Removes vehicle from the dealership
     */
    private void processRemoveVehicleRequest() {
        int vin = Console.promptForInt("Enter Vin To Remove: ");

        Vehicle vehicleToRemove = null;

        for (Vehicle vehicle : dealership.getAllVehicles()) {
            if (vehicle.getVin() == vin) {
                vehicleToRemove = vehicle;
                break;
            }
        }

        if (vehicleToRemove != null) {
            dealership.removeVehicle(vehicleToRemove);
            System.out.println("Vehicle " + vehicleToRemove.getVin() + " has been removed!");

            //Updates the file after vehicle have been removed
            DealershipFileManager fileManager = new DealershipFileManager();
            fileManager.saveDealership(dealership);

        } else {
            System.out.println("Vehicle not found!");
        }


    }




//    public void processSellLeaseVehicle() {
//
//        int Vin = Console.promptForInt("Enter Vin: ");
//        ArrayList<Vehicle> vehicles = dealership.getAllVehicles();
//        Vehicle vehicleToSell = null;
//        for (Vehicle vehicle : vehicles) {
//            if (vehicle.getVin() == Vin) {
//                vehicleToSell = vehicle;
//                break;
//
//            }
//        }
//
//
//        if (vehicleToSell != null) {
//            do {
//                String contract = ("""
//                        1- Sales Contract
//                        2- Lease COntract
//
//                        """);
//
//
//                System.out.println(contract);
//                String command = Console.promptForString("Enter Command: ");
//                switch (command) {
//                    case "1":
//                }
//            }
//        }
//
//
//    }



}






