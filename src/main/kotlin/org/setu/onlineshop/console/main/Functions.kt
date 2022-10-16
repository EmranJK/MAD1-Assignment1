package org.setu.onlineshop.console.main

import org.setu.onlineshop.console.models.ShopModel


object Functions {
    /**
     * This object is where I store functions used for the CRUD functionality in the Kotlin app
     */

    @JvmStatic
    fun addingProducts(array1 : ArrayList<ShopModel>, array2 : ArrayList<ShopModel>, index : Int){
        /**
         * This function adds product by index from array1 (products) to array2 (cart)
         */
        array2 += array1[index-1]
    }


    fun removeProducts(array : ArrayList<ShopModel>, index : Int){
        /**
         * This function removes products from array (cart) by index
         */
        array.removeAt(index-1)
    }
}