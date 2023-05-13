package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CabInvoiceTest {

    @Test
    public void givenDistanceAndTimeShouldReturnTotalFare(){
        CabInvoiceGenerator cabInvoiceGenerator = new CabInvoiceGenerator();

        double result = cabInvoiceGenerator.calculateFare(3.0,3);
        Assertions.assertEquals(33.0,result);
    }

    @Test
    public void givenDistanceAndTimeShouldReturnMinimumFare(){
        CabInvoiceGenerator cabInvoiceGenerator = new CabInvoiceGenerator();

        double result = cabInvoiceGenerator.calculateFare(0.2,1);
        Assertions.assertEquals(5.0,result);
    }

    @Test
    public void givenMultipleRidesShouldReturnTotalFare(){
        CabInvoiceGenerator cabInvoiceGenerator = new CabInvoiceGenerator();
        Ride[] rides = {
                new Ride(2.0,2), new Ride(3.0,1)
        };

        double result = cabInvoiceGenerator.calculateFare(rides);
        Assertions.assertEquals(53.0,result);
    }

    @Test
    public void givenMultipleRidesShouldReturnInvoice(){
        CabInvoiceGenerator cabInvoiceGenerator = new CabInvoiceGenerator();
        Ride[] rides = {
                new Ride(2.0,2),
                new Ride(3.0,1),
                new Ride(0.2,1)
        };

        Invoice invoice = cabInvoiceGenerator.generateInvoice(rides);
        Invoice expectedInvoice = new Invoice(58.0,3,58.0/3);
        Assertions.assertEquals(expectedInvoice,invoice);
    }

    @Test
    public void givenUserIdShouldReturnInvoice(){
        Ride[] rides1 = {
                new Ride(2.0,2),
                new Ride(3.0,3),
                new Ride(0.2,1)
        };

        Ride[] rides2 = {
                new Ride(3.0,3),
                new Ride(0.2,1)
        };

        CabInvoiceGenerator cabInvoiceGenerator = new CabInvoiceGenerator();
        CabInvoiceGenerator.rideRepo.add(new Customer(1,rides1));
        CabInvoiceGenerator.rideRepo.add(new Customer(2,rides2));


        Invoice actualInvoice = cabInvoiceGenerator.generateInvoiceByUserId(2);
        //Invoice expectedInvoice = new Invoice(60.0,3,60.0/3); //userid=1
        Invoice expectedInvoice = new Invoice(38.0,2,38.0/2); //userid=2
        Assertions.assertEquals(expectedInvoice,actualInvoice);
    }
}
