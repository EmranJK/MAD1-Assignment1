package org.setu.onlineshop.console.main

import org.setu.onlineshop.console.models.ShopModel
import java.sql.*;


/**
 * This object is where I store all the functions used for database implementation
 */


object Database {
    @JvmStatic
    fun connect(){
        try{
            //Class.forName("com.mysql.cj.jdbc.Driver");
            val con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kotlin", "root", "")
            println("OK")
            val st = con.createStatement()
            val rs = st.executeQuery("select * from products")
            while(rs.next()){
                println(rs.getString("id"))
            }
        }
        catch (e: SQLException){
            e.printStackTrace()
        }
    }

    fun get_products(array : ArrayList<ShopModel>){
        /**
         * This function is used to get all the products from the product table
         * It then stores them all in the array taken in the parameter
         */

        try{
            //Class.forName("com.mysql.cj.jdbc.Driver");
            val con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kotlin", "root", "")
            println("OK")
            val st = con.createStatement()
            val rs = st.executeQuery("select * from products")
            while(rs.next()){
                array.add(ShopModel(rs.getString("name"), rs.getDouble("price"), rs.getInt("id"), rs.getString("category"), rs.getString("brand")))
                //println(rs.getString("id"))
            }
        }
        catch (e: SQLException){
            e.printStackTrace()
        }
    }


    fun get_cart(array : ArrayList<ShopModel>){
        /**
         * This function is used to get all the products from the cart_product table
         * It then stores them all in the array taken in the parameter
         */
        try{
            //Class.forName("com.mysql.cj.jdbc.Driver");
            val con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kotlin", "root", "")
            println("OK")
            val st = con.createStatement()
            val rs = st.executeQuery("select * from cart_products")
            while(rs.next()){
                array.add(ShopModel(rs.getString("name"), rs.getDouble("price"), rs.getInt("id"), rs.getString("category"), rs.getString("brand")))
                //println(rs.getString("id"))
            }
        }
        catch (e: SQLException){
            e.printStackTrace()
        }
    }


}