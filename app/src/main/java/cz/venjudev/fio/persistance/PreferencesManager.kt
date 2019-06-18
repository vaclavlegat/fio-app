package cz.venjudev.fio.persistance

import android.content.Context
import android.content.SharedPreferences
import java.util.*

class PreferencesManager(context: Context) {

    private val PREF_FILE_NAME = "fio_app_pref_file"

    private val LAST_API_CALL = "LAST_API_CALL"

    private val pref: SharedPreferences

    init {
        pref = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)
    }

    fun setString(key: String, value: String) {
        pref.edit().putString(key, value).apply()
    }

    fun getString(key: String, default: String? = null): String? {
        return pref.getString(key, default)
    }

    fun setDate(key: String, value: Date) {
        pref.edit().putLong(key, value.time).apply()
    }

    fun getDate(key: String): Date? {
        val time = pref.getLong(key, 0)

        if (time == 0L) {
            return null
        }
        return Date(time)
    }

    fun setLastApiCall(value: Date) {
        setDate(LAST_API_CALL, value)
    }

    fun getLastApiCall(): Date? {
        return getDate(LAST_API_CALL)
    }

}
