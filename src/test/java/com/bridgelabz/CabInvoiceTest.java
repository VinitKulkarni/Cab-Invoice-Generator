package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CabInvoiceTest {

    @Test
    public void givenNormalRideCategoryShouldReturnNormalRideTotalFare(){
        CabInvoiceGenerator cabInvoiceGenerator = new CabInvoiceGenerator();

        String typeOfUser = "NORMAL";
        RideCategory rideCategory = RideCategory.valueOf(typeOfUser);

        double result = cabInvoiceGenerator.calculateFare(3.0,3,rideCategory);
        Assertions.assertEquals(33.0,result);
    }

    @Test
    public void givenPremiumRideCategoryShouldReturnPremiumRideTotalFare(){
        CabInvoiceGenerator cabInvoiceGenerator = new CabInvoiceGenerator();

        String typeOfUser = "PREMIUM";
        RideCategory rideCategory = RideCategory.valueOf(typeOfUser);

        double result = cabInvoiceGenerator.calculateFare(3.0,3,rideCategory);
        Assertions.assertEquals(51.0,result);
    }

    @Test
    public void givenNormalRideCategoryShouldReturnNormalRideMinimumFare(){
        CabInvoiceGenerator cabInvoiceGenerator = new CabInvoiceGenerator();

        String typeOfUser = "NORMAL";
        RideCategory rideCategory = RideCategory.valueOf(typeOfUser);

        double result = cabInvoiceGenerator.calculateFare(0.2,1,rideCategory);
        Assertions.assertEquals(10.0,result);
    }

    @Test
    public void givenPremiumRideCategoryShouldReturnPremiumRideMinimumFare(){
        CabInvoiceGenerator cabInvoiceGenerator = new CabInvoiceGenerator();

        String typeOfUser = "PREMIUM";
        RideCategory rideCategory = RideCategory.valueOf(typeOfUser);

        double result = cabInvoiceGenerator.calculateFare(0.2,1,rideCategory);
        Assertions.assertEquals(15.0,result);
    }

    @Test
    public void givenMultipleRidesShouldReturnTotalFare(){
        CabInvoiceGenerator cabInvoiceGenerator = new CabInvoiceGenerator();

        String typeOfUser = "PREMIUM";
        RideCategory rideCategory = RideCategory.valueOf(typeOfUser);

        Ride[] rides = {
                new Ride(2.0,2,rideCategory), new Ride(3.0,1,rideCategory)
        };

        double result = cabInvoiceGenerator.calculateFare(rides);
        Assertions.assertEquals(81.0,result);
    }

    @Test
    public void givenMultipleRidesShouldReturnInvoice(){
        CabInvoiceGenerator cabInvoiceGenerator = new CabInvoiceGenerator();

        String typeOfUser = "NORMAL";
        RideCategory rideCategory = RideCategory.valueOf(typeOfUser);

        Ride[] rides = {
                new Ride(2.0,2,rideCategory),
                new Ride(3.0,1,rideCategory),
                new Ride(0.2,1,rideCategory)
        };

        Invoice invoice = cabInvoiceGenerator.generateInvoice(rides);

        /*//to check what values are getting
        System.out.println("ride type:" + invoice.rideCategory);
        System.out.println("total fare:" + invoice.getTotalFare());
        System.out.println("no of rides:" + invoice.getNumberOfRides());
        System.out.println("avg:" + invoice.getAverage());*/

        Invoice expectedInvoice = new Invoice(63.0,3,63.0/3,rideCategory);
        Assertions.assertEquals(expectedInvoice,invoice);
    }

    @Test
    public void givenUserIdShouldReturnInvoice(){
        String typeOfUser1 = "NORMAL";
        RideCategory normalRideCategory = RideCategory.valueOf(typeOfUser1);

        String typeOfUser2 = "PREMIUM";
        RideCategory premiumRideCategory = RideCategory.valueOf(typeOfUser2);

        Ride[] rides1 = {
                new Ride(2.0,2,normalRideCategory),
                new Ride(3.0,3,normalRideCategory),
                new Ride(0.2,1,normalRideCategory)
        };

        Ride[] rides2 = {
                new Ride(3.0,3,premiumRideCategory),
                new Ride(0.2,1,premiumRideCategory)
        };

        CabInvoiceGenerator cabInvoiceGenerator = new CabInvoiceGenerator();
        CabInvoiceGenerator.rideRepo.add(new Customer(1,rides1));
        CabInvoiceGenerator.rideRepo.add(new Customer(2,rides2));


        Invoice actualInvoice = cabInvoiceGenerator.generateInvoiceByUserId(2);

        /*//to check what values are getting
        System.out.println("ride type:" + actualInvoice.rideCategory);
        System.out.println("total fare:" + actualInvoice.getTotalFare());
        System.out.println("no of rides:" + actualInvoice.getNumberOfRides());
        System.out.println("avg:" + actualInvoice.getAverage());*/


        //Invoice expectedInvoice = new Invoice(65.0,3,65.0/3,normalRideCategory); //userid=1 & ride=normal
        Invoice expectedInvoice = new Invoice(66.0,2,66.0/2,premiumRideCategory); //userid=2 & ride=premium
        Assertions.assertEquals(expectedInvoice,actualInvoice);
    }
}
