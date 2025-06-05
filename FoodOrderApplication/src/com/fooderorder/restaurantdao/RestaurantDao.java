package com.fooderorder.restaurantdao;

import com.fooderorder.app.Restaurant;
import com.fooderorder.config.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RestaurantDao {

    /*
     * uses to add restaurants
     * void no return expected
     * Restaurant as parameter
     * */
    public void saveRestaurant(Restaurant restaurant) throws SQLException {

        Connection connection = DbConnection.getDbConnection();
        String sql = "INSERT into restaurant(rest_name,rest_address,rest_type) values(?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,restaurant.getRestName());
            preparedStatement.setString(2,restaurant.getRestAddress());
            preparedStatement.setString(3,restaurant.getRestType());

            int i = preparedStatement.executeUpdate();

            if(i>0){
                System.out.println("Restaurant added...");
            }

        } catch (SQLException e) {
            System.out.println("Error while adding restaurant,Please try again later.");
            throw new RuntimeException(e);
        }finally {
            connection.close();
        }


    }

    public void updateRestaurant(){

    }

}
