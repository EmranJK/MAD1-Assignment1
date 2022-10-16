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


    fun addGUI(input : Int){
        /**
         * This function calls the addingProducts function from the Functions object
         * It supplies the function with the required parameters for adding a product
         * It then updates the total cost (costGUI)
         * It then clears the cart_products table and refills it with the updated data from the cartGUI array
         */

        if (input in 1..productsGUI.size){

            Functions.addingProducts(productsGUI, cartGUI, input)

            costGUI += productsGUI[input-1].price

            Database.clear_cart()
            Database.add_cart(cartGUI)

        }
        else{
            println("No such item")
        }
    }


    fun removeGUI(input : Int){

        /**
         * This function calls the removeProducts function from the Functions object
         * It supplies the function with the required parameters for removing a product from cart
         * It then updates the total cost (costGUI)
         * It then clears the cart_products table and refills it with the updated data from the cartGUI array
         */

        if (input in 1..cartGUI.size){

            costGUI -= cartGUI[input-1].price
            Functions.removeProducts(cartGUI, input)


            Database.clear_cart()
            Database.add_cart(cartGUI)

        }

        else{
            println("No such item")
        }

    }


    fun ListProductsGUI() : String{

        /**
         * This function lists all the products in the productsGUI array
         * The array is already automatically filled with the relevant data from the database before the listing happens
         * The filling happens when the dataGUI function is called
         * So technically it is listing data from the database in an indirect way
         */


        return Functions.listingCartOrProducts(productsGUI)

    }



    fun ListCartGUI() : String{

        /**
         * This function lists all the products in the cartGUI array
         * The array is already automatically filled with the relevant data from the database before the listing happens
         * The filling happens when the dataGUI function is called
         * So technically it is listing data from the database in an indirect way
         */


        return Functions.listingCartOrProducts(cartGUI)

    }



    fun PredictAddGUI() : String{

        /**
         * This function lists all the products in the productsGUI array with the total price after adding any one of them
         * The array is already automatically filled with the relevant data from the database before the listing happens
         * The filling happens when the dataGUI function is called
         * So technically it is listing data from the database in an indirect way
         */

        var longy = ""

        for(i in 0..productsGUI.size-1) {
            longy += ((i+1).toString()+". " + productsGUI[i].product_name + " " + "($" + productsGUI[i].price.toString()+")" + " " +  productsGUI[i].id.toString() + " " + productsGUI[i].category + " (" + productsGUI[i].brand + ") (TOTAL COST AFTER ADDING THIS PRODUCT: $" + (costGUI + productsGUI[i].price).toString() + ")")  + "\n"
        }
        return longy

    }




    fun PredictRemoveGUI() : String{

        /**
         * This function lists all the products in the cartGUI array with the total price after removing any one of them
         * The array is already automatically filled with the relevant data from the database before the listing happens
         * The filling happens when the dataGUI function is called
         * So technically it is listing data from the database in an indirect way
         */

        var longy = ""


        for(i in 0..cartGUI.size-1){
            longy += ((i+1).toString()+". " + cartGUI[i].product_name + " " + "($" + cartGUI[i].price.toString()+")" + " " +  cartGUI[i].id.toString()  + " " + cartGUI[i].category + " (" + cartGUI[i].brand + ") (TOTAL COST AFTER REMOVING THIS PRODUCT: $" + (costGUI - cartGUI[i].price).toString() + ")") + "\n"
        }
        return longy


    }



    fun SearchGUI(input: String) : String{
        /**
         * This function will call the searchingProducts function from the Functions object to search for products in productsGUI array
         */

        var longy = ""
        longy = Functions.searchingProducts(productsGUI, input)

        return longy


    }



    fun UpdateGUI(input1: Int, input2 :Int){

        /**
         * This function will call the updateCart function from Functions object to replace a product from cartGUI by its index with a product from productsGUI by its index
         * The cart_products table in database will then be cleared and refilled with updated data
         */

        costGUI -= cartGUI[input1 - 1].price
        Functions.updateCart(productsGUI, cartGUI, input1, input2)
        costGUI += productsGUI[input2-1].price
        Database.clear_cart()
        Database.add_cart(cartGUI)
    }





    fun FilterGUI(max: Int) : String{
        /**
         * This function will filter all products in productsGUI array by max price
         */


        return Functions.filterProducts(productsGUI, max)

    }


    fun couponGUI(input : String){
        /**
         * This function calls the discountGUI function to make discounts on prices
         * If coupon tenny is used there will be a 10% discount
         * If coupon fifty is used there will be a 50% discount
         * Only one coupon can be used while the app is running
         * The coupon can also be used only one time while the app is running
         * To use a different coupon or to use the same one twice you must rerun the app
         * Once the coupon is applied all the products prices in productsGUI and cartGUI will be deducted
         * Once the app close or crash, all products in productsGUI will get their original prices back but products in cartGUI will maintain their new deducted prices
         * When using a coupon again after rerunning the app, all products in productsGUI will have there prices deducted but only products in cartGUI that still have their original prices will have their prices deducted
         * I.E. Coupon won't be applied on any products in cartGUI that had their prices deducted already
         * The cart_products table in database will then be cleared and refilled with updated data
         */

        if (input == "tenny" && countGUI == 0){
            countGUI+=1
            discountGUI(0.10)
        }
        else if (input == "fifty" && countGUI == 0){
            countGUI+=1
            discountGUI(0.50)
            println("Congrats!! Now you have a 50% discount on all products")
            println("")
        }
        else{
            countGUI+=1
            println("Coupon Invalid, try again later")
            println("")
        }

        Database.clear_cart()
        Database.add_cart(cartGUI)
    }


    fun discountGUI(num : Double){
        /**
         * This function does the deduction process for the couponGUI function
         */
        costGUI = 0.0

        for (i in productsGUI){

            i.price -= (i.price * num)
        }
        for (i in cartGUI){
            costGUI += i.price
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
        dataGUI()
        logger.info { "Launching OnlineShop Console App" }

        fieldset {
            /**
             * In this fieldset, a textfield is created to hold the index of product
             * Then a button is created to take the index from the textfield and calls the addGUI function
             * It then outputs the total cost in the output text area
             */
            field("Add product by index") {
                textfield(add)
            }


            button("Add") {
                action {
                    if (add.value-1 > productsGUI.size-1 || add.value <= 0 ) {
                        output.value = "No such item" + "\n" + ("TOTAL COST: $" + (costGUI.toString()))
                    }
                    else{
                        addGUI(add.value)
                        output.value = ("TOTAL COST: $" + (costGUI.toString()))

                    }
                }
            }
        }




        fieldset {
            /**
             * In this fieldset, a textfield is created to hold the index of product
             * Then a button is created to take the index from the textfield and calls the removeGUI function
             * It then outputs the total cost in the output text area
             */
            field("Remove Product From Cart By Index") {
                textfield(remove)
            }


            button("Remove") {
                action {

                    if (remove.value-1 > cartGUI.size-1 || remove.value <= 0 ) {
                        output.value = "No such item" + "\n" + ("TOTAL COST: $" + (costGUI.toString()))
                    }
                    else{
                        removeGUI(remove.value)
                        output.value = ("TOTAL COST: $" + (costGUI.toString()))
                    }
                }
            }
        }



        fieldset {
            /**
             * In this fieldset, a button is created to output the value of the function ListProductsGUI() in the output textarea
             */

            button("List Products In Shop") {
                action {

                    output.value = ListProductsGUI()

                }
            }
        }



        fieldset {
            /**
             * In this fieldset, a button is created to output the value of the function ListCartGUI() in the output textarea
             * It also outputs the total cost underneath
             */
            button("List Products In Cart") {
                action {

                    output.value = ListCartGUI() + "\n" + ("TOTAL COST: $" + (costGUI.toString()))
                }
            }
        }


        fieldset {

            /**
             * In this fieldset, a button is created to output the value of the function PredictAddGUI() in the output textarea
             * It also outputs the total cost underneath
             */

            button("Predict Total Cost After Adding Products") {
                action {

                    output.value = PredictAddGUI() + "\n" + ("TOTAL COST: $" + (costGUI.toString()))

                }
            }
        }


        fieldset {

            /**
             * In this fieldset, a button is created to output the value of the function PredictRemoveGUI() in the output textarea
             * It also outputs the total cost underneath
             */

            button("Predict Total Cost After Removing Products") {
                action {
                    output.value = PredictRemoveGUI() + "\n" + ("TOTAL COST: $" + (costGUI.toString()))

                }
            }
        }

        


    }
}