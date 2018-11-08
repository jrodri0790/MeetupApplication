package uio.androidbootcamp.meetupapplication.utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder

object GsonCreator {

    fun create(): Gson {
        return GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create()
    }

}