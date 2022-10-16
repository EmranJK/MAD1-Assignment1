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