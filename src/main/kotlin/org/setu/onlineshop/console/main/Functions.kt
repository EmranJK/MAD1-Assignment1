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


    fun listingCartOrProducts(array : ArrayList<ShopModel>) : String{
        /**
         * This function lists all products in an array
         */

        var temp = ""
        for (i in 0..array.size-1){
            temp+=(array[i].product_name + " " + "($" + array[i].price.toString() + ") " + array[i].id + " " + array[i].category + " (" + array[i].brand+")\n")
        }
        return temp
    }



    fun searchingProducts(array : ArrayList<ShopModel>, name: String) : String{
        /**
         * This function searches for a product by name in an array
         * The search is not case-sensitive
         * The user can also provide 1 or more chars that the product name starts with instead of typing the whole name
         */
        for (i in 0..array.size-1){
            //if (array[i].product_name == name){
            if (array[i].product_name.startsWith(name, ignoreCase = true)){
                return ("FOUND IT: " + (i+1).toString() + ". " + array[i].product_name + " " + "( $ " + array[i].price.toString() + " )" + " " + array[i].id.toString() + " " + array[i].category + " " + array[i].brand)
            }
        }
        return ("Didn't Find It")
    }

    fun filterProducts(array : ArrayList<ShopModel>, max : Int) : String{
        /**
         * This function filters products in an array by max price
         * given a max price the function will output all products that have a price less than or equal to the max price provided
         */

        var temp = ""
        for (i in 0..array.size-1){
            if (array[i].price <= max){
                //println(a[i] + " " + b[i].toString())
                temp+=((i+1).toString()+". " + array[i].product_name + " " + "( $" + array[i].price.toString()+" )" + " " +  array[i].id.toString() + " " + array[i].category + " (" + array[i].brand) + ")\n"
            }
        }
        return temp
    }


    fun updateCart(array1 : ArrayList<ShopModel>, array2 : ArrayList<ShopModel>, index1: Int, index2: Int){
        /**
         * This function will replace a product from array2 by its index1 with a product from array1 by its index2
         */
        array2[index1 - 1] = array1[index2 - 1]
    }
}