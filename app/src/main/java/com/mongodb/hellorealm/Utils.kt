package com.mongodb.hellorealm

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment

object Utils{


    fun isValidPass(password: String): Boolean {
        val passwordRegex = Regex("^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#\$%^&+=!])(?=\\S+$).{7,}$")
        return passwordRegex.matches(password)
    }









}

