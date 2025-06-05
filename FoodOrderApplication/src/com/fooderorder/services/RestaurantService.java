package com.fooderorder.services;

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

        RestaurantDao restaurantDao = new RestaurantDao();
        try {
            restaurantDao.saveRestaurant(restaurant);
        } catch (SQLException e) {
            System.out.println("Error while adding the restaurant");
            throw new RuntimeException(e);
        }
        scanner.close();

    }

}
