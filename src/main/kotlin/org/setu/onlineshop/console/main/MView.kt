package org.setu.onlineshop.console.main
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import javafx.scene.Parent
import javafx.scene.layout.HBox
import mu.KotlinLogging
import org.setu.onlineshop.console.models.ShopModel
import tornadofx.*
import tornadofx.Stylesheet.Companion.label
import tornadofx.Stylesheet.Companion.title
import kotlin.math.absoluteValue


class MView : View() {

    private val logger = KotlinLogging.logger {}

    var productGUI = ShopModel()
    val productsGUI = ArrayList<ShopModel>()
    var costGUI = 0.0

    var cartGUI = ArrayList<ShopModel>()

    var countGUI = 0

    fun dataGUI() {
        /**
         * This function gets all data from products and products_cart tables in database
         * It then loads the data in their relevant arrays (productsGUI and cartGUI)
         * It then updates the total cost (costGUI) based on the products in cartGUI
         */
        Database.get_products(productsGUI)
        Database.get_cart(cartGUI)
        for (i in cartGUI){
            costGUI+=i.price
        }
    }



    val add = SimpleIntegerProperty()
    val remove = SimpleIntegerProperty()
    //val predict_add = SimpleIntegerProperty()
   // val predict_remove = SimpleIntegerProperty()
    val search = SimpleStringProperty()
    val filter = SimpleIntegerProperty()
    val update1 = SimpleIntegerProperty()
    val update2 = SimpleIntegerProperty()
    val coupon = SimpleStringProperty()
    var output = SimpleStringProperty()
    var temp = ""

    //var a = 1


    override val root = form {
        /**
         * The dataGUI function is called to load data from products table in database to productsGUI array
         * it also loads data from cart_products table in database to cartGUI array
         */
    }
}