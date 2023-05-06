package dev.nhason.nivhasonfinalproject.data.models

import android.content.Context
import android.content.Context.MODE_PRIVATE

const val FILE_NAME = "user.terms.permission"
const val FILE_NAME_FIRST_TIME = "user.terms.permission.first.time"

fun Context.saveFirstTime(boolean: Boolean){
    getSharedPreferences(FILE_NAME,MODE_PRIVATE)
        .edit()
        .putBoolean(FILE_NAME_FIRST_TIME,boolean)
        .apply()
}

val Context.isFirstTime: Boolean
    get() =
        getSharedPreferences(FILE_NAME, MODE_PRIVATE)
            .getBoolean(FILE_NAME_FIRST_TIME, true)
