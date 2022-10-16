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

}