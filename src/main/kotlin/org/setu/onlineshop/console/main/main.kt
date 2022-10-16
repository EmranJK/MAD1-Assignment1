package org.setu.onlineshop.console.main

import mu.KotlinLogging
import org.setu.onlineshop.console.models.ShopModel
import tornadofx.launch
import kotlin.math.roundToInt

private val logger = KotlinLogging.logger {}


fun main(args: Array<String>){
    /**
     * The app is launched here
     */
    launch<MApp>(args)
}


