package com.fooderorder;

import com.fooderorder.services.RestaurantService;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //Execution starts here....
        System.out.println("                                    FOOD ORDER APPLICATION");
        System.out.println("1. Vendor 2. Customer");

        RestaurantService restaurantService = new RestaurantService();
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();

        switch (option){
            case 1 -> {
                System.out.println("Your are Vendor 1.Add restaurant 2.Add menu");
                System.out.println("Enter your choice 1 or 2 :");
                int choose = scanner.nextInt();
                if(choose == 1){
                    restaurantService.addRestaurant();
                }else {
                    restaurantService.addMenuByRestaurant();
                }

            }

            case 2-> System.out.println("You are Customer"); //view all restaurants  -->allow customer to choose one -> fetch all menu items form menu table based on the rest id
            default -> System.out.println("Invalid option");
        }

        scanner.close();

    }
}