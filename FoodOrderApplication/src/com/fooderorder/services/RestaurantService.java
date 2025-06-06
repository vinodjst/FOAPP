package com.fooderorder.services;

import com.fooderorder.app.Menu;
import com.fooderorder.app.Restaurant;
import com.fooderorder.config.DbConnection;
import com.fooderorder.restaurantdao.RestaurantDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class RestaurantService {

//logic

    public void addRestaurant(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Name :");
        String name = scanner.next();
        System.out.println("Enter Address :");
        String address = scanner.next();
        System.out.println("Enter Type(veg/non-veg) :");
        String type = scanner.next();


        Restaurant restaurant = new Restaurant();
        restaurant.setRestName(name);
        restaurant.setRestAddress(address);
        restaurant.setRestType(type);

        //adding menu
        System.out.println(" \n \n Please add your menu :");
        System.out.println("Enter itemName :");
        String itemName = scanner.next();

        System.out.println("Enter itemPrice :");
        double itemPrice = scanner.nextDouble();

        Menu menu = new Menu();
        menu.setItemName(itemName);
        menu.setItemPrice(itemPrice);

        RestaurantDao restaurantDao = new RestaurantDao();

        try {
            int rest_id = restaurantDao.saveRestaurant(restaurant);
            menu.setRestId(rest_id);
            restaurantDao.saveMenu(menu);
        } catch (SQLException e) {
            System.out.println("Error while adding the restaurant");
            throw new RuntimeException(e);
        }
        scanner.close();

    }

}
