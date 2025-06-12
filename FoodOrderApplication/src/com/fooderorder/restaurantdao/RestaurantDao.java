package com.fooderorder.restaurantdao;

import com.fooderorder.app.Menu;
import com.fooderorder.app.Restaurant;
import com.fooderorder.config.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RestaurantDao {

    /*
     * uses to add restaurants
     * void no return expected
     * Restaurant as parameter
     *
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

    public boolean fetchRestaurantById(int restId) throws SQLException {

        Connection connection = DbConnection.getDbConnection();
        String sql ="Select * from restaurant where rest_id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,restId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                System.out.println("RestId found " + resultSet.getInt(1)+" : RestaurantName : "+resultSet.getString(2));
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error while adding Menu,Please try again later.");
            throw new RuntimeException(e);
        }finally {
            connection.close();
        }
      return false;

    }


        // ... existing methods ...

        public List<Restaurant> fetchAllRestaurants() throws SQLException {
            List<Restaurant> restaurantList = new ArrayList<>();
            Connection connection = DbConnection.getDbConnection();
            String sql = "SELECT * FROM restaurant";

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Restaurant restaurant = new Restaurant();
                    restaurant.setRestId(resultSet.getInt("rest_id"));
                    restaurant.setRestName(resultSet.getString("rest_name"));
                    restaurant.setRestAddress(resultSet.getString("rest_address"));
                    restaurant.setRestType(resultSet.getString("rest_type"));

                    restaurantList.add(restaurant);
                }

            } catch (SQLException e) {
                System.out.println("Error while fetching restaurants.");
                throw new RuntimeException(e);
            } finally {
                connection.close();
            }

            return restaurantList;
        }

        public List<Menu> fetchAllMenusByRestaurantId(int restId) throws SQLException {
            List<Menu> menuList = new ArrayList<>();
            Connection connection = DbConnection.getDbConnection();
            String sql = "SELECT * FROM menu WHERE rest_id = ?";

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, restId);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Menu menu = new Menu();
                    menu.setItemId(resultSet.getInt("item_id"));
                    menu.setItemName(resultSet.getString("item_name"));
                    menu.setItemPrice(resultSet.getDouble("item_price"));
                    menu.setRestId(resultSet.getInt("rest_id"));

                    menuList.add(menu);
                }

            } catch (SQLException e) {
                System.out.println("Error while fetching menu items.");
                throw new RuntimeException(e);
            } finally {
                connection.close();
            }

            return menuList;
        }
    }


