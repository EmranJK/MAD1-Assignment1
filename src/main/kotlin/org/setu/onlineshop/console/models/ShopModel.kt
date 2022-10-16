package org.setu.onlineshop.console.models

data class ShopModel(
    var product_name: String = "",
    var price: Double = 0.0,
    var id: Int = 0,
    var category: String = "",
    var brand: String = ""
)

