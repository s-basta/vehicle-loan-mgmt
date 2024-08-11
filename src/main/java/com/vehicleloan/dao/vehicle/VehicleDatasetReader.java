package com.vehicleloan.dao.vehicle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class VehicleDatasetReader {

	 public static void main(String[] args) {
	        String csvFile = "C:\\Users\\Sarrah\\Desktop\\Training Essentials\\VehicleLoanProject\\All_cars_dataset_cleaned_csv.csv"; // give local filesystem absolute path
	        String line;
	        String cvsSplitBy = ",";

	        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
	            // Skip header
	            br.readLine();
	            
	            VehicleDAOImplementation vehicleDao = new VehicleDAOImplementation();
	            int vehicleId = 1; // Starting vehicle ID

	            while ((line = br.readLine()) != null) {
	                // Split line by comma
	                String[] values = line.split(cvsSplitBy);
	                
	                // Extract fields
	                String name = values[0];
	                String predictedPriceStr = values[1];
	                
	                // Extract vehicleMake and vehicleModel
	                String[] nameParts = name.split(" ", 2);
	                if (nameParts.length < 2) {
	                    continue; // Skip if name does not have at least 2 parts
	                }
	                String vehicleMake = nameParts[0];
	                String vehicleModel = nameParts[1];
	                
	                if(vehicleMake.contains("Rolls")) {
	                	vehicleMake="Rolls Royce";
	                	vehicleModel=vehicleModel.split(" ",2)[1];
	                }
	                

	                // Convert predictedPrice to Double
	                Double exShowroomPrice = convertToDouble(predictedPriceStr);


	                System.out.println("Vehicle Make : "+vehicleMake + " VehicleModel : "+vehicleModel + " Price: "+ exShowroomPrice +" \n");
	                // Create Vehicle object
	                Vehicle vehicle = new Vehicle(vehicleId++, vehicleMake, vehicleModel, exShowroomPrice);

	                // Insert into database
	                vehicleDao.create(vehicle);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	// Method to convert the price string into a Double
	    private static Double convertToDouble(String priceStr) {
	        // Remove commas and convert to Double
//	        priceStr = priceStr.replace(",", "").trim();
	        try {
	            return Double.parseDouble(priceStr);
	        } catch (NumberFormatException e) {
//	        	System.out.println("PRICESTRINGG "+priceStr);
	            // Handle parse errors, return null or default value if needed
	            System.err.println("Error parsing price: " + priceStr);
	            return null;
	        }
	    }
}
