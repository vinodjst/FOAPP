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

            case 2-> {
                System.out.println("You are Customer");
                restaurantService.getAllRestaurants();
                System.out.println("Please enter your restaurant id");
                int restId = scanner.nextInt();
                restaurantService.fetchAllMenusByRestaurantId(restId);

            }
            default -> System.out.println("Invalid option");
        }

        scanner.close();

    }
}