package com.fooderorder.services;

import com.fooderorder.app.Menu;
import com.fooderorder.app.Restaurant;
import com.fooderorder.config.DbConnection;
import com.fooderorder.restaurantdao.RestaurantDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RestaurantService {

//logic
    RestaurantDao restaurantDao = new RestaurantDao();

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

        int rest_id = 0;
        try {
            rest_id = restaurantDao.saveRestaurant(restaurant);
            //collections
            List<Menu> menuList = addMenuItems(rest_id);
            for (Menu menu : menuList) {
                restaurantDao.saveMenu(menu);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        scanner.close();

    }


    //Adding the menu items
    private List<Menu> addMenuItems(int restaurantId){

        List<Menu> listOfMenu = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            //adding menu
            System.out.println(" \n \n Please add your menu :");
            System.out.println("Enter itemName :");
            String itemName = scanner.next();
            System.out.println("Enter itemPrice :");
            double itemPrice = scanner.nextDouble();
            Menu menu = new Menu();
            menu.setItemName(itemName);
            menu.setItemPrice(itemPrice);
            menu.setRestId(restaurantId);
            listOfMenu.add(menu);
            System.out.println("1.Stop adding  2.Add new item");
            int i = scanner.nextInt();
            if(i==1){
                break;
            }
        }

        return listOfMenu;
    }

    //based on the rest id we will save the menu
    public void addMenuByRestaurant(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your restuarant id");
        int id = scanner.nextInt();

        try {
            boolean check = restaurantDao.fetchRestaurantById(id);
            if (check){
                System.out.println("Restaurant available you can continue with adding menus");
                List<Menu> menuList = addMenuItems(id);
                for (Menu menu : menuList) {
                    restaurantDao.saveMenu(menu);
                }
            }else {
                System.out.println("No data found with this id , pleas provide valid id");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //validate
        //check in the db if there entered value is available or not
        //if yes we can continue OW we need throw error invalid Id or id not found


    }



}
