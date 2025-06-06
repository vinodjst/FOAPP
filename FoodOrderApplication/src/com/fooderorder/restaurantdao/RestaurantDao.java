package com.fooderorder.restaurantdao;

import com.fooderorder.app.Menu;
import com.fooderorder.app.Restaurant;
import com.fooderorder.config.DbConnection;

import java.sql.*;

public class RestaurantDao {

    /*
     * uses to add restaurants
     * void no return expected
     * Restaurant as parameter
     * */
    public int saveRestaurant(Restaurant restaurant) throws SQLException {

        int result =0;
        Connection connection = DbConnection.getDbConnection();
        String sql = "INSERT into restaurant(rest_name,rest_address,rest_type) values(?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,restaurant.getRestName());
            preparedStatement.setString(2,restaurant.getRestAddress());
            preparedStatement.setString(3,restaurant.getRestType());

            int i = preparedStatement.executeUpdate();

            if(i>0){
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if(generatedKeys.next()){
                    System.out.println("restaurant inserted id " + generatedKeys.getInt(1));
                    result = generatedKeys.getInt(1);
                }
                System.out.println("Restaurant added...");
            }

        } catch (SQLException e) {
            System.out.println("Error while adding restaurant,Please try again later.");
            throw new RuntimeException(e);
        }finally {
            connection.close();
        }

        return result;
    }


    public void saveMenu(Menu menu) throws SQLException {

        Connection connection = DbConnection.getDbConnection();
        String sql = "INSERT into menu(item_name,item_price,rest_id) values(?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,menu.getItemName());
            preparedStatement.setDouble(2,menu.getItemPrice());
            preparedStatement.setInt(3,menu.getRestId());

            int i = preparedStatement.executeUpdate();

            if(i>0){
                System.out.println("Menu added...");
            }

        } catch (SQLException e) {
            System.out.println("Error while adding Menu,Please try again later.");
            throw new RuntimeException(e);
        }finally {
            connection.close();
        }


    }

    public void updateRestaurant(){

    }

}
