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
}