package org.setu.onlineshop.console.main

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.setu.onlineshop.console.models.ShopModel

internal class MainKtTest {
    var productTest = ShopModel()
    val productsTest = ArrayList<ShopModel>()
    val cartTest = ArrayList<ShopModel>()

    fun data() {
        /**
         * Dummy data
         */
        productsTest.add(ShopModel("a", 10.00, 23,"Food", "A"))
        productsTest.add(ShopModel("b", 20.00, 65,"Drink", "B"))
        productsTest.add(ShopModel("c", 35.00, 12,"Drink", "C"))
        productsTest.add(ShopModel("d", 45.00, 9,"Food", "D"))
    }

    @BeforeEach
    internal fun setUp() {
        /**
         * data() function will be called everytime the tests are generated
         */
        data()
    }

    @Test
    fun add() {
        /**
         * This function tests the addingProducts function from the Functions class
         */

        Functions.addingProducts(productsTest, cartTest, 1)
        // println(cartTest[0].product_name)
        assertEquals("a", cartTest[0].product_name)
    }

    @Test
    fun remove() {
        /**
         * This function tests the removeProducts function from the Functions class
         */
        Functions.addingProducts(productsTest, cartTest, 1)
        Functions.addingProducts(productsTest, cartTest, 2)
        Functions.removeProducts(cartTest, 1)
        assertEquals(1, cartTest.size)
        assertEquals(65, cartTest[0].id)
    }

    @Test
    fun list() {
        /**
         * This function tests the listingCartOrProducts function from the Functions class
         */
        Functions.addingProducts(productsTest, cartTest, 1)
        //Functions.addingProducts(productsTest, cartTest, 2)
        var testy = (productsTest[1-1].product_name + " " + "($" + productsTest[1-1].price.toString() + ") "+ productsTest[1-1].id + " " + productsTest[1-1].category + " (" + productsTest[1-1].brand+")\n")
        assertEquals(testy, Functions.listingCartOrProducts(cartTest))
    }

    @Test
    fun search() {
        /**
         * This function tests the searchingProducts function from the Functions class
         */
        Functions.addingProducts(productsTest, cartTest, 1)
        Functions.addingProducts(productsTest, cartTest, 2)
        Functions.addingProducts(productsTest, cartTest, 4)
        var testy = ("FOUND IT: 1. " + cartTest[0].product_name + " " + "( $ " + cartTest[0].price.toString() + " )"  + " "  + cartTest[0].id.toString() + " "  + cartTest[0].category + " "  + cartTest[0].brand)
        //println(Functions.searchingCart(cartTest, 23))

        assertEquals(testy, Functions.searchingProducts(productsTest, "A"))
    }

}