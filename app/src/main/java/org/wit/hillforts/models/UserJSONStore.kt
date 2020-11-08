package org.wit.hillforts.models

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.hillforts.helpers.exists
import org.wit.hillforts.helpers.read
import org.wit.hillforts.helpers.write
import kotlin.random.Random

val JSON_USERS_FILE = "users.json"
val gsonUserBuilder = GsonBuilder().setPrettyPrinting().create()
val userListType = object : TypeToken<java.util.ArrayList<UserModel>>() {}.type

fun generateRandomUserId(): Long {
    return Random.nextLong()
}

class UserJSONStore  : UserStore, AnkoLogger {

    val context: Context
    var users = mutableListOf<UserModel>()

    constructor(context: Context) {
        this.context = context
        if (exists(context, JSON_USERS_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): List<UserModel> {
        return users
    }

    override fun create(user: UserModel) {
        user.id = generateRandomUserId()
        users.add(user)
        serialize()
        info("done adding user")
    }

    override fun update(hillfort: HillfortModel) {
        TODO("Not yet implemented")
    }

    override fun delete(hillfort: HillfortModel) {
        TODO("Not yet implemented")
    }

    private fun serialize(){
        val jsonString = gsonUserBuilder.toJson(users, userListType)
        write(context, JSON_USERS_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_USERS_FILE)
        users = Gson().fromJson(jsonString, userListType)
    }
}