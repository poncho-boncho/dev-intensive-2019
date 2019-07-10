package ru.skillbranch.devintensive.models.utils

object Utils {
    fun parsetFullName(fullName:String?): Pair<String?, String?> {



        var parts : List<String>? = fullName?.split( " ")
        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)

        if (fullName?.trimIndent()=="")
            return null to null


        return firstName to lastName
    }
}