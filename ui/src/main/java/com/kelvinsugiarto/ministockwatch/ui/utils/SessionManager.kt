package com.kelvinsugiarto.ministockwatch.ui.utils
import com.kelvinsugiarto.ministockwatch.ui.utils.Constants.EMAIL
import com.kelvinsugiarto.ministockwatch.ui.utils.Constants.IS_USER_LOGIN


class SessionManager(private val prefs: SharedPreferenceHelper) {

    var email = ""
        get() = if (field.isNotEmpty()) field else prefs.getString(EMAIL)
        set(value) {
            field = value
            prefs.save(EMAIL, value)
        }

    fun startUserSession() {
        prefs.save(IS_USER_LOGIN, true)
    }

    fun isSessionActive(): Boolean {
        return prefs.getBoolean(IS_USER_LOGIN)
    }

    fun endUserSession() {
        prefs.save(IS_USER_LOGIN, false)
    }
}