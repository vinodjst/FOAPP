package com.fooderorder.app;

import com.fooderorder.config.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

public class CrudOperations {


    //save/update/delete/fetch

    //save some random data
    public void save() throws SQLException {

        Connection connection = DbConnection.getDbConnection();
        String sql ="INSERT INTO menu(item_id,item_name,item_price) values(?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,103);
            preparedStatement.setString(2,"CAKE");
            preparedStatement.setString(3,"200.00");

            int i = preparedStatement.executeUpdate();
            if(i>0){
                System.out.println("Data inserted successfully..");
            }else {
                System.out.println("Not inserted...");
            }

        } catch (SQLException e) {
            System.out.println("Error while inserting the data..");
            throw new RuntimeException(e);
        }finally {
            connection.close();
        }

    }




    public void update() throws SQLException {

        Connection connection = DbConnection.getDbConnection();
        String sql = "Update menu set item_name = ? where item_id=?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,"Choclate");
        preparedStatement.setInt(2,2);
        int i = preparedStatement.executeUpdate();

        if(i>0){
            System.out.println("Updated successully.....");
        }

    }

    public void delete(){

    }

    public void fetch(){

    }






    public static void main(String[] args) {

        CrudOperations crudOperations = new CrudOperations();
        try {
           // crudOperations.save();

            crudOperations.update();


        } catch (SQLException e) {
            System.out.println("Error while crud operations");
            throw new RuntimeException(e);
        }

    }


}
