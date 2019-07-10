package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.models.utils.Utils
import java.util.*

data class User (
    val id:String,
    var firstName:String?=null,
    var lastName:String?=null,
    var avatar:String?,
    var rating:Int = 0,
    var respect:Int = 0,
    val lastVisit:Date? = null,
    val isOnline:Boolean = false
    ){
    constructor(id: String,firstName: String?, lastName: String?) : this (
        id = id,
        firstName = firstName,
        lastName = lastName,
        avatar = null
    )
    constructor(id: String) : this (id, "John", "Doe ")

    init {

        println("It's Alive!!!\n " +
                "${ "his name is ${firstName?:firstName} ${lastName?:lastName}!!!"}\n")

    }

    fun printMe() = println("""
    id: $id,
    firstName: $firstName ,
    lastName: $lastName,
    avatar: $avatar,
    rating: $rating,
    respect: $respect,
    lastVisit:$lastVisit ,
    isOnline: $isOnline
    """.trimIndent())

    companion object Factory {
      private  var lastId : Int = -1
       fun makeUser (fullName:String?) : User {
        lastId++

        val (firstName , lastName) = Utils.parsetFullName(fullName)


        return User (id = "$lastId", firstName = firstName, lastName = lastName)
    }
}
}
