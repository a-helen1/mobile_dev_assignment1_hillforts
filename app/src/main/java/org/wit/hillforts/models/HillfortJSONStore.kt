package org.wit.hillforts.models

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import org.wit.hillforts.helpers.exists
import org.wit.hillforts.helpers.read
import org.wit.hillforts.helpers.write
import kotlin.random.Random

val JSON_FILE= "hillforts.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<HillfortModel>>() {}.type

fun generateRandomId(): Long {
    return Random.nextLong()
}

class HillfortJSONStore : HillfortStore, AnkoLogger {

    val context: Context
    var hillforts = mutableListOf<HillfortModel>()

    constructor(context: Context) {
        this.context =context
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): List<HillfortModel> {
        return hillforts
    }

    override fun create(hillfort: HillfortModel) {
        hillfort.id = generateRandomId()
        hillforts.add(hillfort)
        serialize()
    }

    override fun update(hillfort: HillfortModel) {
        var foundHillfort: HillfortModel? = hillforts.find { p -> p.id == hillfort.id}
        if (foundHillfort != null) {
            foundHillfort.title = hillfort.title
            foundHillfort.description = hillfort.description
            foundHillfort.image1 = hillfort.image1
            foundHillfort.image2 = hillfort.image2
            foundHillfort.lat = hillfort.lat
            foundHillfort.lng = hillfort.lng
            foundHillfort.zoom = hillfort.zoom
            foundHillfort.visited = hillfort.visited
            serialize()
        }
    }

    override fun delete(hillfort: HillfortModel) {
        hillforts.remove(hillfort)
        serialize()
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(hillforts, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        hillforts = Gson().fromJson(jsonString, listType)
    }
}